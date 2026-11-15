<div align="center">

<img src="https://i.imgur.com/8qvAgA0.png" alt="ConcreteMixer Icon" width="250px">

# ConcreteMixer

[![](https://img.shields.io/badge/License-MPL--2.0-blue)](./LICENSE "Project License: MPL-2.0")
[![](https://img.shields.io/badge/Java-25-orange)](# "Java Version: 25")
[![](https://img.shields.io/github/v/release/LeafCommunity/ConcreteMixer.svg?label=Release&color=ok)](https://github.com/LeafCommunity/ConcreteMixer/releases/latest "Latest Release")
[![](https://img.shields.io/spiget/downloads/106707?color=yellow&label=Spigot%20Downloads)](https://www.spigotmc.org/resources/concretemixer.106707/ "Spigot Resource Page")
[![](https://img.shields.io/modrinth/dt/ufdoTeFB?color=%2300af5c&label=Modrinth%20Downloads&logo=modrinth)](https://modrinth.com/plugin/ufdoTeFB "Modrinth Project Page")

</div>

Use cauldrons to make concrete. Throw in powder... get concrete!

![](https://i.imgur.com/iGBYwER.gif "Concrete transformation demo.")

[![](https://bstats.org/signatures/bukkit/ConcreteMixer.svg)](https://bstats.org/plugin/bukkit/ConcreteMixer/15590)


## CraftBukkit 26.3 port

This source port targets CraftBukkit 26.3 using the Spigot/Bukkit API, with no
Paper-only APIs. Build and runtime require JDK 25 or newer.

Build with Maven 3.9+: `mvn --batch-mode clean verify`

The installable output is `target/ConcreteMixer-1.2.2-craftbukkit26.3.jar`.
Do not install `original-ConcreteMixer-*.jar`; it lacks bundled dependencies.

On Windows you can run `build.cmd`; on Linux/macOS run `sh build.sh`.
Both require Java 25 and Maven on PATH, and access to Maven Central,
hub.spigotmc.org and jitpack.io.

Alternatively, push this source to a GitHub repository (including
`.github/workflows/build.yml`), then run **Actions > Build > Run workflow**.
Download the **ConcreteMixer-CraftBukkit-26.3** artifact from the successful run
and extract the plugin JAR. The ZIP itself is not a plugin.

Stop the server, replace the old ConcreteMixer JAR in `plugins/`, and start the
server. Keep `plugins/ConcreteMixer/config.yml` to preserve your settings.
Use a separate test server with a copy of your configuration for the first run.

### Changes

- Target the 26.3 API and Java 25, with Java 25-capable compiler/shading plugins.
- Use Bukkit SPLASH and POOF particles directly; remove XSeries.
- Resolve configured sounds without the old enum-based YAML adapter, preserving
  legacy sound names and accepting namespaced keys.
- Read the game version from Bukkit without the old Evergreen version parser;
  query `26.3` rather than incorrectly reducing it to `26`.
- Cancel both tracked tasks when powder stacks merge, and stop tracking removed,
  picked-up, or already converted item entities.

### Validation status

Regression tests for game-version parsing are included but have NOT been run.
The full Maven build and a live CraftBukkit 26.3 server test have NOT been run:
the editing environment only has a Java 21 runtime, no Java compiler or Maven,
and dependency downloads are blocked. The Maven XML was validated locally. This is a
source port pending compilation and runtime verification, not a tested release.
The GitHub workflow compiles and packages it using Java 25; it does not launch
a Minecraft server. The existing optional GriefPrevention hook is retained and
also needs testing against your installed GriefPrevention version.

After building, check startup, a new and existing config, all 16 powder colors,
splash/transform effects, water consumption on/off, permissions, stack merging,
picking powder up during conversion, `/concretemixer reload`, and claimed
cauldrons if you use protection plugins. Confirm item counts are preserved.

## Versioning

Releases use `<Minecraft version>.<plugin revision>`.
For example, `26.3.1` is the first plugin release for Minecraft 26.3.
