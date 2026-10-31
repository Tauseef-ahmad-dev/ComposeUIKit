# Theming

ComposeUIKit uses Material 3 and provides a default light/dark palette. Consumers can override the Material color scheme, typography, shapes, and the library spacing/radius tokens.

## Default theme

```kotlin
UIKitTheme {
    // App content
}
```

## Custom color scheme

```kotlin
val appColors = lightColorScheme(
    primary = Color(0xFF174EA6),
    secondary = Color(0xFF52627D)
)

UIKitTheme(colorScheme = appColors) {
    // Components use the app MaterialTheme
}
```

## Custom typography and shapes

```kotlin
UIKitTheme(
    colorScheme = appColors,
    typography = appTypography,
    shapes = appShapes
) {
    // App content
}
```

## Spacing tokens

Read the current tokens inside composition:

```kotlin
val tokens = LocalUIKitTokens.current

Column(verticalArrangement = Arrangement.spacedBy(tokens.spacing.large)) {
    // Content
}
```

Available spacing values are 4, 8, 12, 16, 24, and 32 dp. Default corner radii are 6, 10, 16, and 24 dp.

The default light primary/foreground pair and dark primary/foreground pair are checked against the WCAG AA 4.5:1 text contrast target in JVM tests. The dark palette uses a dark foreground on its light primary color. Custom color schemes remain the application's responsibility: validate contrast and brand alignment for every component state.
