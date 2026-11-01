# Publishing

ComposeUIKit publishes the Android release variant to Maven Central using the verified namespace:

```text
io.github.tauseef-ahmad-dev:composeuikit:<version>
```

The Android namespace remains `dev.tauseef.composeuikit`; it is independent of the Maven group ID.

## Local Maven repository

Build the release publication locally:

```bash
./gradlew :composeuikit:publishMavenPublicationToLocalRepository
```

The repository is written to:

```text
composeuikit/build/repo/
└── io/github/tauseef-ahmad-dev/composeuikit/0.1.0/
```

A separate Android project can add the generated folder as a Maven repository:

```kotlin
repositories {
    maven {
        url = uri("../ComposeUIKit/composeuikit/build/repo")
    }
}

dependencies {
    implementation("io.github.tauseef-ahmad-dev:composeuikit:0.1.0")
}
```

## Central Portal prerequisites

Before publishing, configure the following repository secrets in GitHub under **Settings → Secrets and variables → Actions**:

| Secret | Value |
|---|---|
| `MAVEN_CENTRAL_USERNAME` | Username from a generated Central Portal user token |
| `MAVEN_CENTRAL_PASSWORD` | Password from that user token |
| `MAVEN_CENTRAL_SIGNING_KEY` | ASCII-armored private GPG key |
| `MAVEN_CENTRAL_SIGNING_KEY_PASSWORD` | Passphrase for the GPG key (may be empty if the key has no passphrase) |
| `MAVEN_CENTRAL_SIGNING_KEY_ID` | Short GPG key ID |

Do not commit credentials or private signing keys to the repository, and do not paste them into chat.

Generate a Central Portal user token from the Account section at [central.sonatype.com](https://central.sonatype.com/). Use the generated token username and password, not your Portal login credentials.

Create a dedicated GPG signing key and publish its public key to the public keyservers required by Maven Central. Keep the private key secure. Export it in ASCII-armored format for the GitHub secret:

```bash
gpg --export-secret-keys --armor YOUR_KEY_ID
```

The output is sensitive. Store it directly in the GitHub Actions secret and never in the repository.

## Publishing workflow

The `Publish to Maven Central` workflow runs on a version tag or can be started manually from **Actions** with an existing tag, such as `v0.1.0`.

It verifies that the tag matches the Gradle version, runs API compatibility checks, builds and tests the library, runs lint and compiles the sample app, then uploads a signed deployment to the Central Portal.

The workflow intentionally uses manual deployment publication. After a successful upload:

1. Open [Central Portal → Deployments](https://central.sonatype.com/publishing/deployments).
2. Inspect the deployment and resolve any validation errors.
3. Click **Publish** only after confirming the coordinates and artifacts are correct.

Published Maven Central releases are immutable. Do not publish a version until its API, POM metadata, signatures, and consumer integration have been reviewed.

## Release checklist

- [ ] Confirm the version in the root `build.gradle.kts`.
- [ ] Update `CHANGELOG.md`.
- [ ] Confirm the matching annotated Git tag exists.
- [ ] Confirm all five GitHub Actions secrets are configured.
- [ ] Run the publishing workflow against the intended tag.
- [ ] Review the Central Portal deployment and validation.
- [ ] Publish the deployment from the Portal.
- [ ] Verify the artifact resolves from Maven Central in a clean Android project.

## Consumer dependency

After the deployment is published and indexed, consumers can add:

```kotlin
dependencies {
    implementation("io.github.tauseef-ahmad-dev:composeuikit:0.1.0")
}
```

Maven Central indexing can take some time after publication. The dependency is not installable from Central merely because the GitHub release exists.
