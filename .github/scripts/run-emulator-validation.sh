#!/usr/bin/env bash
set -Eeuo pipefail

readonly package_manager_attempts=90
readonly package_manager_retry_seconds=2

dump_diagnostics() {
    adb devices -l || true
    adb shell getprop sys.boot_completed || true
    adb shell service list 2>/dev/null | grep -E "package|activity" || true
    adb logcat -b all -d -t 150 2>/dev/null || true
}

wait_for_package_manager() {
    adb wait-for-device

    for ((attempt = 1; attempt <= package_manager_attempts; attempt++)); do
        if adb shell service check package 2>/dev/null | grep -q "found"; then
            adb shell cmd package list packages >/dev/null
            return
        fi

        adb reconnect offline >/dev/null 2>&1 || true
        sleep "$package_manager_retry_seconds"
    done

    echo "Android package manager did not become ready after $((package_manager_attempts * package_manager_retry_seconds)) seconds." >&2
    dump_diagnostics
    exit 1
}

png_dimensions() {
    od -An -tu1 -j 16 -N 8 "$1" | awk '{
        width = $1 * 16777216 + $2 * 65536 + $3 * 256 + $4
        height = $5 * 16777216 + $6 * 65536 + $7 * 256 + $8
        print width "x" height
    }'
}

capture_screenshots() {
    adb shell wm size 1080x2400
    adb shell wm density 420
    adb shell settings put system font_scale 1.0
    adb shell input keyevent 82
    adb shell am force-stop dev.tauseef.composeuikit.sample
    adb logcat -c
    adb shell am start -W -n dev.tauseef.composeuikit.sample/.MainActivity
    sleep 3

    adb shell uiautomator dump /sdcard/composeuikit-screen-1.xml >/dev/null
    adb exec-out cat /sdcard/composeuikit-screen-1.xml > /tmp/composeuikit-screen-1.xml
    if ! grep -q "ComposeUIKit" /tmp/composeuikit-screen-1.xml || ! grep -q "Primary action" /tmp/composeuikit-screen-1.xml; then
        echo "Sample app content was not visible on the emulator." >&2
        adb logcat -d -t 150 2>/dev/null || true
        exit 1
    fi

    mkdir -p docs/assets/screenshots
    adb exec-out screencap -p > docs/assets/screenshots/component-overview.png

    adb shell input swipe 540 2050 540 600 450
    sleep 2
    adb shell uiautomator dump /sdcard/composeuikit-screen-2.xml >/dev/null
    adb exec-out cat /sdcard/composeuikit-screen-2.xml > /tmp/composeuikit-screen-2.xml
    if ! grep -q "Category" /tmp/composeuikit-screen-2.xml; then
        echo "Selection controls were not visible after scrolling the sample app." >&2
        exit 1
    fi
    adb exec-out screencap -p > docs/assets/screenshots/forms-and-selection.png

    for screenshot in docs/assets/screenshots/component-overview.png docs/assets/screenshots/forms-and-selection.png; do
        test -s "$screenshot"
        test "$(png_dimensions "$screenshot")" = "1080x2400"
    done

    if cmp -s docs/assets/screenshots/component-overview.png docs/assets/screenshots/forms-and-selection.png; then
        echo "Screenshot captures are identical; refusing to publish duplicates." >&2
        exit 1
    fi

    sha256sum docs/assets/screenshots/component-overview.png docs/assets/screenshots/forms-and-selection.png
}

wait_for_package_manager
./gradlew :sample:connectedDebugAndroidTest :sample:assembleDebug

if [ "${CAPTURE_SCREENSHOTS:-false}" = "true" ]; then
    capture_screenshots
fi
