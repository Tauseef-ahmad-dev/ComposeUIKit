# Accessibility and layout direction

ComposeUIKit builds on Material 3 semantics and interaction primitives. Accessibility is part of component correctness, not a visual polish step.

## Component guidance

- Give every input a meaningful visible label or an explicit semantic label.
- Supply a meaningful content description for icon-only actions.
- Keep disabled controls genuinely disabled and prevent callbacks from firing.
- Pair visual errors with readable text; never communicate an error through color alone.
- Preserve comfortable touch targets and avoid making a small icon the only clickable target.
- Avoid announcing decorative symbols as meaningful content.
- Prefer Material selection primitives so roles and selected state are exposed to accessibility services.
- Keep validation and form announcements owned by the consuming application.

## Right-to-left layouts

Components should rely on Compose layout direction rather than hardcoded left/right positioning. Prefer logical start/end padding and arrangements. Avoid absolute positioning for content that should mirror in RTL.

To manually verify the sample in Arabic:

1. Change the emulator system language to Arabic.
2. Force-stop and relaunch the sample.
3. Check text fields, dropdown menus, chips, checkbox/radio rows, dialogs, and bottom sheets.
4. Confirm that text alignment and component order remain natural and that icons with directional meaning mirror appropriately.
5. Repeat with a large font setting to check wrapping and clipping.

## Verification checklist

- TalkBack can identify each control and its purpose.
- Controls expose role, enabled state, and selected state.
- Icon-only controls expose a useful content description.
- Form errors are visible as text and remain readable in dark theme.
- Clear and password-visibility actions have understandable labels.
- Disabled controls do not invoke callbacks.
- The sample remains usable at larger font scales.
- RTL layout is checked on an Arabic locale.

The sample instrumentation suite covers key accessibility-facing behaviour, including the icon button description, error messaging, selection state, and disabled/loading buttons:

```bash
./gradlew :sample:connectedDebugAndroidTest
```

Run the same suite on an Arabic-configured emulator when validating a release candidate. Automated semantics assertions do not replace a manual TalkBack pass.
