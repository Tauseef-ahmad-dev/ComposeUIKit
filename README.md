# ComposeUIKit

A reusable Jetpack Compose UI component library built with Kotlin and Material 3.

> **Status:** Early development (`0.1.0`). APIs may change before the first stable release.

## Modules

- `composeuikit` — reusable Android UI library.
- `sample` — demo application that consumes the library as a Gradle project dependency.

## Components in the initial scaffold

- `UIKitTheme`
- `UIKitButton` (primary, outlined and text variants)
- `UIKitTextField`
- `UIKitPasswordField`
- `UIKitSearchField`

## Tech stack

- Kotlin
- Jetpack Compose
- Material 3
- Gradle Kotlin DSL
- Version Catalog
- JUnit
- GitHub Actions

## Requirements

- Android Studio with a compatible Android Gradle Plugin
- JDK 17
- Android SDK 36
- Gradle 8.13 or compatible wrapper distribution

## Run the sample

Open the repository root in Android Studio, sync Gradle, select the `sample` run configuration and launch it on an emulator or device.

From a machine with Gradle installed:

```bash
gradle :sample:assembleDebug
gradle :composeuikit:test
gradle :composeuikit:lint
```

Generate the Gradle wrapper for the pinned Gradle distribution:

```bash
gradle wrapper --gradle-version 8.13
```

Then commit the generated `gradlew`, `gradlew.bat` and `gradle/wrapper` files.

## Usage

The sample app demonstrates consuming the library through:

```kotlin
dependencies {
    implementation(project(":composeuikit"))
}
```

Example:

```kotlin
UIKitTheme {
    UIKitTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email address"
    )
}
```

## Design principles

- State hoisting and predictable callbacks.
- Modifiers are exposed on public composables.
- Application validation and business rules remain outside the UI library.
- Components use Material 3 and support consumer theme customization.
- Keep runtime dependencies minimal.

## Roadmap

- [x] Initial Gradle modules and theme
- [x] Button and input component scaffold
- [ ] Dropdown and date picker
- [ ] Filter chips, radio group and checkbox
- [ ] Dialogs, bottom sheets and feedback components
- [ ] Accessibility and RTL test coverage
- [ ] Component screenshots and API documentation
- [ ] Publishing configuration

## License

Apache License 2.0. See [LICENSE](LICENSE).
