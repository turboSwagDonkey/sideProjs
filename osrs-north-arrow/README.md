# North Arrow (RuneLite plugin)

Draws an arrow next to your character that always points north, whatever way the camera is facing.

The arrow runs from your character to a point a few tiles north. Both points are projected from the game world to the screen, so the arrow lies flat on the ground and follows camera rotation and pitch.

## Settings

- **Arrow color** (with transparency)
- **Distance (tiles)**: how far north the arrow tip sits (1–10)
- **Arrowhead size** in pixels
- **Show shaft**: line from your character to the head
- **Show "N" label**

## Running it locally

Needs JDK 11+.

- **IntelliJ**: open this folder, then run `NorthArrowPluginTest.main` (in `src/test`). It starts RuneLite with the plugin loaded. Add `-ea` to VM options and `--developer-mode` to program arguments.
- **Command line**: `./gradlew shadowJar`, then `java -ea -jar build/libs/north-arrow-1.0-SNAPSHOT-all.jar --developer-mode`.

Turn it on under **Configuration → North Arrow**.

## Plugin Hub

The Plugin Hub wants each plugin in its own repo, with the Gradle project at the root. To submit it, copy this folder into a new repo and open a PR against [runelite/plugin-hub](https://github.com/runelite/plugin-hub) with a `plugins/north-arrow` file pointing at that repo and commit.
