# ComposeUIKit

<p align="center">
  <strong>A reusable Jetpack Compose component library for Android teams.</strong><br/>
  Kotlin · Jetpack Compose · Material 3
</p>

<p align="center">
  <a href="https://github.com/Tauseef-ahmad-dev/ComposeUIKit/actions/workflows/android-ci.yml"><img src="https://github.com/Tauseef-ahmad-dev/ComposeUIKit/actions/workflows/android-ci.yml/badge.svg" alt="Android CI"/></a>
  <img src="https://img.shields.io/badge/API-26%2B-3D8B72" alt="Minimum API 26"/>
  <img src="https://img.shields.io/badge/License-Apache--2.0-blue.svg" alt="Apache 2.0"/>
  <img src="https://img.shields.io/badge/Status-Early%20development-orange" alt="Early development"/>
</p>

ComposeUIKit is a small, reusable UI toolkit built on top of Jetpack Compose and Material 3. It provides consistent building blocks for forms, selection controls, feedback, dialogs, and common layouts.

The project is designed for real application teams: APIs are state-hoisted where practical, components expose modifiers and callbacks, and business rules stay in the consuming app.

> **Development status:** APIs are still evolving. The current `0.1.0` release is being prepared for Maven Central. See [Publishing](docs/publishing.md) for the verified coordinates and release process.

## Component showcase

The screenshots below are captured from the running ComposeUIKit sample app and show the library's interactive Material 3 components.

<p align="center">
  <img src="docs/assets/screenshots/component-playground-buttons-inputs.png" alt="ComposeUIKit buttons and text input components" width="32%"/>
  <img src="docs/assets/screenshots/component-playground-selection.png" alt="ComposeUIKit selection controls including dropdown, chips, checkbox, radio buttons and date picker" width="32%"/>
  <img src="docs/assets/screenshots/component-playground-bottom-sheet.png" alt="ComposeUIKit reusable modal bottom sheet" width="32%"/>
</p>

<p align="center">
  <em>Buttons and inputs · Selection controls · Feedback and bottom sheet</em>
</p>

## Highlights

- **Material 3 foundation** — familiar Compose behaviour with a cohesive default theme.
- **State-hoisted APIs** — app state and validation stay in the app.
- **Configurable theme** — override color scheme, typography, shapes, and spacing tokens.
- **Useful input components** — text, password, search, and dropdown fields.
- **Selection controls** — date picker, filter chips, checkbox, and radio group.
- **Feedback and surfaces** — dialogs, modal bottom sheet, loader, snackbar host, empty state, cards, and divider.
- **Interactive sample** — a small app that consumes the library module.
- **Consumer-ready dependency scopes** — public Compose and Material 3 API types are exported correctly from the Android library.
- **Local Maven publication** — generates an AAR, sources JAR, and POM for integration testing in another Android project.
- **Automated checks** — GitHub Actions builds the library and sample, runs JVM unit tests and lint, verifies local Maven publication, and checks public API compatibility.

## Components

| Category | Components |
|---|---|
| Theme | `UIKitTheme`, `UIKitColors`, `UIKitTokens` |
| Buttons | `UIKitButton`, `UIKitIconButton` |
| Inputs | `UIKitTextField`, `UIKitPasswordField`, `UIKitSearchField` |
| Selection | `UIKitDropdownField<T>`, `UIKitDatePickerDialog`, `UIKitFilterChip`, `UIKitCheckbox`, `UIKitRadioGroup<T>` |
| Dialogs and sheets | `UIKitDialog`, `UIKitConfirmationDialog`, `UIKitBottomSheet` |
| Feedback | `UIKitLoader`, `UIKitEmptyState`, `UIKitSnackbarHost` |
| Layout | `UIKitCard`, `UIKitDivider` |

## Project structure

```text
ComposeUIKit/
├── composeuikit/                 # Reusable Android library
│   └── src/
│       ├── main/kotlin/dev/tauseef/composeuikit/
│       │   ├── theme/
│       │   └── components/
│       │       ├── buttons/
│       │       ├── inputs/
│       │       ├── selection/
│       │       ├── dialogs/
│       │       ├── sheets/
│       │       ├── feedback/
│       │       └── layout/
│       └── test/                 # JVM unit tests
├── sample/                       # Interactive consumer app
│   └── src/
│       ├── main/
│       └── androidTest/          # Sample UI tests
├── docs/                         # Architecture and API guides
├── gradle/libs.versions.toml
└── .github/workflows/            # CI
```

## Requirements

The project is configured with:

- JDK 17
- Android Gradle Plugin 8.13.2
- Kotlin 2.2.21
- Gradle 8.13
- Android SDK 36
- Minimum Android SDK 26
- Compose BOM 2025.10.01

Use a compatible Android Studio version with Android SDK 36 installed.

## Open and run

1. Clone this repository.
2. Open the repository root in Android Studio.
3. Sync Gradle.
4. Select the `sample` run configuration.
5. Run it on an emulator or Android device.

The repository includes the **Gradle Wrapper**, pinned to Gradle 8.13. You do not need to install Gradle separately. On the first run, the wrapper downloads the distribution and verifies it against the official SHA-256 checksum.

Run the following commands from the repository root:

```bash
./gradlew :composeuikit:assembleRelease
./gradlew :composeuikit:test
./gradlew :composeuikit:lint
./gradlew :sample:assembleDebug
./gradlew :sample:assembleDebugAndroidTest
./gradlew :sample:connectedDebugAndroidTest
```

On Windows, use `gradlew.bat` instead of `./gradlew`. Android Studio also detects the checked-in wrapper automatically.

## Use the library in the sample project

The sample consumes the library as a Gradle module dependency:

```kotlin
dependencies {
    implementation(project(":composeuikit"))
}
```

### Text field

```kotlin
var email by rememberSaveable { mutableStateOf("") }

UIKitTheme {
    UIKitTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email address",
        placeholder = "name@example.com",
        helperText = "Use your work email",
        modifier = Modifier.fillMaxWidth()
    )
}
```

### Dropdown

```kotlin
var department by rememberSaveable { mutableStateOf<String?>(null) }

UIKitDropdownField(
    selectedOption = department,
    options = listOf("Design", "Engineering", "Operations"),
    onOptionSelected = { department = it },
    optionLabel = { it },
    label = "Department",
    modifier = Modifier.fillMaxWidth()
)
```

### Button with loading state

```kotlin
UIKitButton(
    text = "Save changes",
    onClick = onSave,
    loading = isSaving,
    enabled = formIsValid
)
```

### Publish to a local Maven repository

The library has a release publication configured with sources and POM metadata. To produce a local Maven repository for integration testing:

```bash
./gradlew :composeuikit:publishToMavenLocal
```

Artifacts are written to your local Maven cache under `~/.m2/repository`. Use that local Maven cache to verify a consumer-style dependency. The intended Central coordinate is `io.github.tauseef-ahmad-dev:composeuikit:0.1.0`. The artifact becomes installable only after the signed deployment has passed Portal validation and has been published. See [Publishing](docs/publishing.md).

## Design principles

1. **State belongs to the consumer.** Components accept values and callbacks rather than owning business state.
2. **No app-specific dependencies.** The library must not depend on navigation, repositories, networking, persistence, or Firebase.
3. **Material 3 first.** Use accessible, well-understood Compose primitives instead of custom drawing where it adds no value.
4. **Customisation without complexity.** Expose useful parameters, not every internal implementation detail.
5. **Documented public API.** Public composables should have KDoc and a sample.
6. **Test behaviour.** Validate callbacks, disabled states, validation messages, selection and semantics.

## Documentation

- [Architecture](docs/architecture.md)
- [Component catalogue](docs/components.md)
- [Theming](docs/theming.md)
- [Accessibility and RTL](docs/accessibility.md)
- [Publishing](docs/publishing.md)
- [Contributing](CONTRIBUTING.md)
- [Changelog](CHANGELOG.md)

## Quality checks

GitHub Actions runs the same lightweight validation for pull requests and pushes to `main`:

- Gradle Wrapper integrity validation.
- Library release compilation.
- JVM unit tests.
- Android lint.
- Sample app debug compilation.
- Local Maven publication verification and signed Maven Central release workflow.
- Public API compatibility checks.

Emulator-based instrumentation tests and automatic screenshot capture are intentionally excluded from CI to keep the pipeline fast and deterministic. Instrumentation tests remain in the repository and can be run manually on a connected emulator or device.

Sample instrumentation tests are included in the repository and can be run on a connected emulator/device with:

```bash
./gradlew :sample:connectedDebugAndroidTest
```

## GitHub prereleases

Versioned GitHub prereleases are built from matching tags. The separate Maven Central workflow uploads signed artifacts to the Central Portal only after release checks pass; the deployment still requires review and publication in the Portal.

For maintainers, create a version tag after the main-branch validation is green:

```bash
git tag v0.1.0
git push origin v0.1.0
```

This produces a GitHub prerelease. To publish the matching artifact to Maven Central, configure the secrets and follow [docs/publishing.md](docs/publishing.md).

## Roadmap

- [x] Initial Android library and sample modules
- [x] Material 3 theme and token foundation
- [x] Buttons and text inputs
- [x] Dropdown, date picker, chips, checkbox, and radio group
- [x] Dialog, bottom sheet, feedback, and layout primitives
- [x] Interactive sample playground
- [x] Architecture, theming, component, and accessibility docs
- [x] Expand component previews and interaction tests
- [x] Capture and add sample screenshots to README
- [x] API compatibility checks and tagged GitHub prerelease workflow
- [x] Verify Maven Central namespace and configure signed publishing

## License

ComposeUIKit is distributed under the Apache License 2.0. See [LICENSE](LICENSE).
