# Architecture

ComposeUIKit is split into a reusable Android library and a sample application.

## Modules

### `:composeuikit`

Contains public UI APIs, design tokens, and wrappers around stable Material 3 primitives. It must remain independent of any application domain.

- No navigation dependency.
- No ViewModels, repositories, network clients, or persistence.
- No app-specific validation or business rules.
- Public composables accept a `Modifier` and keep caller-owned state hoisted where practical.

### `:sample`

An executable showcase and integration example. It is a consumer of the library, not a dependency of the library.

## Package boundaries

- `theme`: Material 3 theme, semantic colors, typography, shapes, and spacing tokens.
- `components.buttons`: buttons and icon actions.
- `components.inputs`: text, password, and search inputs.
- `components.selection`: dropdowns, date picker, chips, checkbox, and radio group.
- `components.dialogs`: alert and confirmation dialogs.
- `components.sheets`: modal bottom sheet.
- `components.feedback`: progress, empty state, and snackbar host.
- `components.layout`: card and divider.

## State ownership

Prefer state hoisting for values that affect application behaviour. For example, a text field receives `value` and `onValueChange`; it does not store the form value internally. A component may own ephemeral presentation state when that state has no meaning outside the component, such as password visibility or dropdown expansion.

## Dependency policy

Compose runtime, Compose UI, Compose UI graphics, and Material 3 are exposed with Gradle's `api` configuration because their types appear in public composable and theme signatures. Tooling, preview-only, and test dependencies remain non-transitive. Keep the runtime dependency graph small and promote no other dependency to `api` unless its types become part of the public API.

## Change policy

Treat all public composable signatures as library API. Document parameters with KDoc, add sample usage, test meaningful behaviour, and record breaking changes in the changelog.
