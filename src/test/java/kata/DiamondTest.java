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

import static org.assertj.core.api.Assertions.assertThat;
import static testasyouthink.TestAsYouThink.givenSut;
import static testasyouthink.TestAsYouThink.resultOf;
import static testasyouthink.TestAsYouThink.when;

class DiamondTest {

    @Test
    void should_behave_given_context() {
        // Choose the proper syntax for your test case.

        resultOf(() -> "TestAsYouThink").isEqualTo("TestAsYouThink");

        when(() -> "TestAsYouThink").then(result -> {
            assertThat(result)
                    .startsWith("Test")
                    .hasSize(14);
        });

        givenSut(StringBuilder::new)
                .whenSutRuns(sut -> sut
                        .append("TestAsYouThink")
                        .reverse())
                .then(sut -> assertThat(sut).containsSequence("knihTuoYsAtseT"));
    }
}
