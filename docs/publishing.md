# Publishing

ComposeUIKit is currently an early-development library. Its public API is not stable and it has not been released to Maven Central.

## Build a local Maven repository

The release publication creates an AAR, sources JAR, and Maven POM:

```bash
./gradlew :composeuikit:publishReleasePublicationToLocalRepository
```

The repository is written to:

```text
composeuikit/build/repo/
└── dev/tauseef/composeuikit/0.1.0/
```

A separate Android project can add the generated folder as a Maven repository for an integration test:

```kotlin
repositories {
    maven {
        url = uri("../ComposeUIKit/composeuikit/build/repo")
    }
}

dependencies {
    implementation("dev.tauseef:composeuikit:0.1.0")
}
```

The coordinates are development coordinates. Do not advertise them as a public hosted artifact.

## Tagged GitHub prerelease

The repository contains a release workflow that creates a GitHub prerelease with the AAR, sources JAR, and POM. It verifies that the pushed tag matches the Gradle project version and reruns the API check, unit tests, lint, sample compilation, and local publication before creating the prerelease.

After reviewing the changelog and confirming the version in the root `build.gradle.kts`, create and push an annotated tag:

```bash
git tag -a v0.1.0 -m "ComposeUIKit 0.1.0"
git push origin v0.1.0
```

The workflow marks GitHub releases as prereleases while the public API remains in early development. Maven Central publication is intentionally separate and still requires namespace verification and signing credentials.

## Before Maven Central

- Stabilise and review the public API.
- Settle the artifact namespace and version.
- Run API compatibility checks.
- Validate POM metadata and dependency scopes.
- Configure signing keys and secret credentials outside the repository.
- Complete Maven Central namespace verification and publisher onboarding.
- Publish a tagged release with changelog and release notes.
- Test the published artifact from a clean consumer project.
