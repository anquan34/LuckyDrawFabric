# Lucky Draw — Fabric 1.21.11

A client-side cosmetic showcase screen inspired by the dark item-grid layout in the reference video. It does **not** accept bets, consume items, award money, or change the player's inventory. The reveal is only a visual selection.

## Requirements
- Minecraft Java Edition 1.21.11
- Fabric Loader 0.18.1 or newer
- Fabric API 0.141.1+1.21.11
- JDK 21
- Gradle (or open this folder in IntelliJ IDEA and let Gradle import it)

## Build
Run from this folder:

```bash
gradle build
```

The remapped mod JAR will be in `build/libs/`.

## Use
Install the built JAR in `.minecraft/mods` with Fabric API installed. In-game, press **G** to open the Lucky Draw screen. Press **REVEAL RANDOM ITEM** to show a random cosmetic item. The selected item is not added to inventory.

## Notes
This is a starter implementation. It reproduces the general dark panel + item-grid + reveal-button idea, not every control or server-specific feature visible in the video.
