# Component catalogue

The sample app is the interactive reference for each component. The table below describes the initial API surface.

| Component | Package | Notes |
|---|---|---|
| `UIKitTheme` | theme | Light/dark Material 3 theme with override points |
| `UIKitButton` | buttons | Primary, outlined, text, loading and disabled states |
| `UIKitIconButton` | buttons | Icon-only action with required accessibility label |
| `UIKitTextField` | inputs | Label, placeholder, helper/error, keyboard and icon slots |
| `UIKitPasswordField` | inputs | Caller-owned value and local visibility toggle |
| `UIKitSearchField` | inputs | Search value and clear action |
| `UIKitDropdownField<T>` | selection | Generic single selection |
| `UIKitDatePickerDialog` | selection | Material 3 date picker; returns UTC milliseconds |
| `UIKitFilterChip` | selection | Caller-controlled selected state |
| `UIKitCheckbox` | selection | Checkbox with optional clickable label |
| `UIKitRadioGroup<T>` | selection | Generic vertical single-selection group |
| `UIKitDialog` | dialogs | Flexible alert dialog wrapper |
| `UIKitConfirmationDialog` | dialogs | Confirm/cancel dialog |
| `UIKitBottomSheet` | sheets | Modal Material 3 bottom sheet |
| `UIKitLoader` | feedback | Accessible indeterminate loading indicator |
| `UIKitEmptyState` | feedback | Empty state with optional recovery action |
| `UIKitSnackbarHost` | feedback | Host for caller-owned SnackbarHostState |
| `UIKitCard` | layout | Configurable Material 3 card |
| `UIKitDivider` | layout | Horizontal divider |

## Example

```kotlin
var email by rememberSaveable { mutableStateOf("") }

UIKitTheme {
    UIKitTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email address",
        placeholder = "name@example.com",
        helperText = "We will use this for account updates",
        modifier = Modifier.fillMaxWidth()
    )
}
```

## API guidance

- Keep values and business validation in the consuming app.
- Always pass a stable key when rendering repeated components in lazy lists.
- Provide content descriptions for icon-only actions.
- Use `enabled` and `readOnly` intentionally; they communicate different interaction semantics.
- Date picker callbacks return UTC epoch milliseconds. Convert to local dates at the application boundary.
