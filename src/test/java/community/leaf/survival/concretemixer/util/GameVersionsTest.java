/*
 * Copyright © 2026, RezzedUp and Contributors <https://github.com/LeafCommunity/ConcreteMixer>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package community.leaf.survival.concretemixer.util;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameVersionsTest {
    @Test
    void keepsTwoPartCalendarVersion() {
        assertEquals(List.of("26.3"), GameVersions.fromBukkitVersion("26.3-R0.1-SNAPSHOT"));
    }

    @Test
    void includesPatchAndBaseVersion() {
        assertEquals(List.of("26.3.1", "26.3"), GameVersions.fromBukkitVersion("26.3.1-R0.1-SNAPSHOT"));
        assertEquals(List.of("1.21.3", "1.21"), GameVersions.fromBukkitVersion("1.21.3-R0.1-SNAPSHOT"));
    }

    @Test
    void acceptsReleaseVersionsAndUnknownFormats() {
        assertEquals(List.of("26.3"), GameVersions.fromBukkitVersion("26.3"));
        assertEquals(List.of("26.3.1", "26.3"), GameVersions.fromBukkitVersion("26.3.1"));
        assertEquals(List.of("custom"), GameVersions.fromBukkitVersion("custom-SNAPSHOT"));
    }
}
