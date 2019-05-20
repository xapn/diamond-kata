/*-
 * #%L
 * Diamond Kata
 * %%
 * Copyright (C) 2019 Chrysocode
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

package kata;

import org.junit.jupiter.api.Test;

import static testasyouthink.TestAsYouThink.whenOutsideOperatingConditions;

class DiamondTest {

    @Test
    void should_fail_to_create_a_diamond_given_nil() {
        whenOutsideOperatingConditions(() -> {
            String letter = null;
            if (letter == null) {
                throw new IllegalArgumentException("Letter missing!");
            }
        })
                .thenItFails()
                .becauseOf(IllegalArgumentException.class)
                .withMessage("Letter missing!");
    }
}
