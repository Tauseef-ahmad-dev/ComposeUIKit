# Contributing

Thanks for your interest in ComposeUIKit.

## Guidelines

- Keep components state-hoisted where practical.
- Expose a `Modifier` parameter on public composables.
- Keep application business logic out of the library.
- Add previews for meaningful component states.
- Add or update tests for behaviour changes.
- Document public APIs with KDoc.
- Run the library tests, API compatibility check, lint and sample build before opening a pull request.
- Run the sample instrumentation suite on an emulator with `./gradlew :sample:connectedDebugAndroidTest`.
- Update the committed API dump only when a public API change is intentional; review the diff before committing it.

## Pull requests

Please include:
- A concise description of the change.
- Screenshots for visual changes.
- Tests or an explanation of why tests are not applicable.
- Any public API or compatibility impact.
