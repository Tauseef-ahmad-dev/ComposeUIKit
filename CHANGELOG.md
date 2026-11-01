# Changelog

All notable project changes are documented here.

## Unreleased

- Expanded the Material 3 theme API with color scheme, typography, shape, and token overrides.
- Added loading-aware buttons and an accessible icon-only button.
- Improved text, password, and search field APIs.
- Added dropdown, date picker, filter chip, checkbox, and radio group components.
- Added dialogs, confirmation dialog, modal bottom sheet, snackbar host, loader, empty state, card, and divider.
- Rebuilt the sample app as an interactive component playground.
- Added light/dark Compose previews, token unit tests, and sample instrumentation tests.
- Added architecture, component, theming, accessibility, and publishing documentation.
- Configured a local Maven release publication with POM metadata and a sources JAR.
- Added a visual component-gallery preview to the README.
- Added the official Gradle 8.13 Wrapper with distribution checksum verification.
- Updated CI to build through the checked-in wrapper and validate its integrity.
- Added disabled and loading button states to the sample with UI-test coverage.
- Expanded sample instrumentation coverage for dropdowns, selection, dialogs, password visibility, search clearing, and bottom sheets.
- Added Kotlin binary API snapshots and a tagged GitHub prerelease workflow.
- Updated CI to run sample instrumentation tests on an Android emulator.
- Committed the Kotlin binary API baseline and enforced compatibility checks in CI.
- Improved dark-theme primary button text contrast and added a WCAG AA contrast regression test.
- Expanded emulator tests for accessibility descriptions, validation errors, and empty-state recovery actions.
- Configured main-only screenshot capture as a read-only artifact for manual review and commit.
- Fixed emulator validation so the emulator action invokes one Bash script rather than splitting shell control flow into individual POSIX shell commands.
- Added bounded Package Manager readiness diagnostics, KVM access setup, and PNG dimension/hash validation for generated emulator screenshots.
- Require the AAR, sources JAR, POM, and Gradle module metadata before attaching prerelease assets.

## 0.1.0 — Initial development scaffold

- Added Android library and sample application modules.
- Added Material 3 theme and design token foundation.
- Added initial button and text-input components.
- Added initial CI workflow and project documentation.
