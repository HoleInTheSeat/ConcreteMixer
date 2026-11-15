/*
 * Copyright © 2026, RezzedUp and Contributors <https://github.com/LeafCommunity/ConcreteMixer>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package community.leaf.survival.concretemixer.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Game version filters without assuming Minecraft versions begin with 1. */
public final class GameVersions {
    private static final Pattern VERSION = Pattern.compile("^(\\d+\\.\\d+)(\\.\\d+)?(?:-|$)");

    private GameVersions() {}

    public static List<String> fromBukkitVersion(String bukkitVersion) {
        Matcher matcher = VERSION.matcher(bukkitVersion);
        if (!matcher.find()) {
            // An unfamiliar version must not prevent the plugin from starting.
            return List.of(bukkitVersion.split("-", 2)[0]);
        }
        String base = matcher.group(1);
        return matcher.group(2) == null
            ? List.of(base)
            : List.of(base + matcher.group(2), base);
    }
}
