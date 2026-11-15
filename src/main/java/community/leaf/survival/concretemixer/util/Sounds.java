/*
 * Copyright © 2026, RezzedUp and Contributors <https://github.com/LeafCommunity/ConcreteMixer>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package community.leaf.survival.concretemixer.util;

import com.rezzedup.util.valuables.Adapter;
import community.leaf.configvalues.bukkit.YamlAccessor;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;

import java.util.Locale;
import java.util.Optional;

/** Registry sounds, accepting both old enum names and namespaced sound keys. */
public final class Sounds {
    private Sounds() {}

    public static final YamlAccessor<Sound> YAML = YamlAccessor.of(Adapter.of(
        value -> parse(String.valueOf(value)),
        sound -> Optional.of(sound.getKeyOrThrow().toString())
    ));

    @SuppressWarnings("deprecation")
    public static Optional<Sound> parse(String text) {
        String name = text.trim();
        // Preserve exact legacy names, including names containing underscores.
        try {
            return Optional.ofNullable(Sound.valueOf(name.toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException ignored) {
            NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
            return key == null ? Optional.empty() : Optional.ofNullable(Registry.SOUNDS.get(key));
        }
    }
}
