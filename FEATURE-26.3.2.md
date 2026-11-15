# ConcreteMixer 26.3.2: non-player powder sources

This update is based on the 26.3 port shared in this conversation, including
the subsequent Shade 3.6.2 fix. Merge the included files into the same paths
at the root of your fork. If you have made other edits to these Java files,
merge those edits rather than blindly replacing them. The POM bumps the project
to 26.3.2. Keep your existing tag-only build/release workflow.

## Configuration

After installing, edit plugins/ConcreteMixer/config.yml. Add the new setting
under the existing cauldrons section (do not create a second cauldrons section):

```yaml
cauldrons:
  allow-non-player-items: true
```

Then run /concretemixer reload. Missing settings default to false and are added
by the existing config loader. Enable this before spawning new powder items.
Items already lying in the world are not scanned retroactively.

## Behavior

- Listen for loose concrete powder spawning from droppers, dispensers, spilled
  containers, and other sources without a player thrower UUID.
- Delay source detection by one tick, letting player-drop handling finish.
- Player drops still use concretemixer.cauldrons when that permission is required.
  Automated sources have no player permission and are controlled by the new setting.
- All entry paths (spawn, player drop, merge, and cauldron refill) share source
  validation and duplicate-task protection. Disabling the option also stops
  active conversions of items without a player source.
- Preserve existing conversion effects, stack counts, and water-consumption setting.
- When water consumption is enabled, automated items consult protection hooks.
  The generic hook allows cancellation of its synthetic cauldron-level check.
  GriefPrevention claimed cauldrons reject automated items because no player can
  be authorized. When water consumption is disabled, existing behavior skips
  cauldron access checks for both player and automated conversions.
- A dropped shulker box remains a shulker box. Only powder that Minecraft actually
  spills into loose item entities is eligible; the plugin does not unpack boxes.
- Existing timing is retained: items must reach a water cauldron within about
  two seconds of tracking. This update does not add long-distance transport scans.

## Release

Commit the updated files to your fork. Create a new 26.3.2 tag/release targeting
that commit. The JAR will be ConcreteMixer-26.3.2.jar.

## Validation status and server checks

The POM XML and patch ZIP were validated locally, and source changes were
reviewed. Compilation and automated Java tests could not run in the editing
environment (no JDK/Maven; dependency downloads blocked). Live CraftBukkit 26.3
behavior, including ItemSpawnEvent ordering and spilled shulker contents, still
requires verification. Treat this as a feature implementation pending testing.

On a test server verify:

1. Default false: player drops convert; dropper/dispenser powder does not.
2. Enable: dropper and dispenser powder dropped directly into a water cauldron
   converts once, with unchanged item count and the configured water consumption.
3. Destroy a shulker item containing powder so Minecraft spills its contents:
   loose powder converts; unrelated items do not. Intact boxes stay intact.
4. Enable require-permission-node: permitted players work; denied players do not.
   Automated items still work only when allow-non-player-items is enabled.
5. Test one item, a full stack, and merging stacks; check for losses/duplication.
6. Pick up or remove powder mid-conversion; no conversion of removed entities.
7. Toggle the option off and reload while powder is converting; automation stops.
8. Test water consumption on and off, then empty and refill the cauldron.
9. If installed, test GriefPrevention claims: automation with water consumption
   enabled is denied inside a claim and allowed outside. Player trust still applies.
10. Test a plugin that cancels cauldron-level checks; automatic conversion that
    consumes water must be denied. Check console for errors throughout.
