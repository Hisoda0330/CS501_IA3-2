# Profile Picture with Notification Badge (Jetpack Compose)

A minimal Jetpack Compose sample that shows how to overlay a notification badge on a profile avatar using a `Box`, aligned to the **bottom-end** via `Modifier.align(Alignment.BottomEnd)`. Includes a toggle button to show/hide the badge.

## Features
- `Box` layout with layered children
- Circular avatar placeholder (initials)
- Customizable badge (number, dot, or emoji)
- Toggle button and AssistChip to show/hide the badge
- Material 3 components

## How it Works
- The avatar is a `Box` clipped with `CircleShape`.
- The badge is another composable placed **inside the same `Box`** with:
  ```kotlin
  if (showBadge) {
      Badge(modifier = Modifier.align(Alignment.BottomEnd)) { Text("9+") }
  }
