# Accessibility and layout direction

ComposeUIKit builds on Material 3 semantics and interaction primitives. Accessibility remains a release requirement, not a visual polish step.

## Component guidance

- Add meaningful labels to inputs.
- Use a content description for icon-only controls.
- Keep disabled controls genuinely disabled and prevent callbacks from firing.
- Pair visual errors with text, not color alone.
- Ensure touch targets remain comfortable on small screens.
- Avoid announcing decorative icons as meaningful content.
- Use Material selection primitives so roles and selected state are exposed to accessibility services.

## Right-to-left layouts

Components should rely on Compose layout direction rather than hardcoded left/right positioning. Prefer logical start/end padding and arrangements. Test the sample in Arabic and verify that labels, icons, menus, and dialogs remain understandable.

## Verification checklist

- TalkBack can identify each control.
- Controls expose role, enabled state, and selected state.
- Form errors are visible as text.
- Clear and visibility actions have understandable labels.
- The sample remains usable at larger font scales.
- RTL layout is checked on an Arabic locale.
