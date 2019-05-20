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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;
import static testasyouthink.TestAsYouThink.resultOf;
import static testasyouthink.TestAsYouThink.whenOutsideOperatingConditions;

class DiamondTest {

    private static final int CODE_OF_A = "A".codePointAt(0);

    String diamondOf(String letter) {
        Letter givenLetter = new Letter(letter);
        if (givenLetter.isA()) {
            return letter;
        } else {
            return Stream
                    .of(CODE_OF_A, givenLetter.getCode())
                    .map(code -> code == CODE_OF_A ? valueOf((char) code.intValue()) : valueOf(
                            (char) code.intValue()) + valueOf((char) code.intValue()))
                    .collect(joining());
        }
    }

    static class Letter {

        private final String letter;

        Letter(String letter) {
            this.letter = letter;
            validate();
        }

        private void validate() {
            if (letter == null) {
                throw new IllegalArgumentException("Letter missing!");
            } else if (letter.isEmpty()) {
                throw new IllegalArgumentException("A letter is expected!");
            } else if (otherThanLetters()) {
                throw new IllegalArgumentException("Only letters are expected!");
            } else if (moreThanOneLetter()) {
                throw new IllegalArgumentException("Only one letter is expected!");
            }
        }

        private boolean otherThanLetters() {
            return !letter.matches("^[A-Z]*$");
        }

        private boolean moreThanOneLetter() {
            return letter.length() > 1;
        }

        boolean isA() {
            return "A".equals(letter);
        }

        int getCode() {
            return letter.codePointAt(0);
        }
    }

    @Nested
    class Given_a_valid_letter {

        @Test
        void should_create_a_diamond_splinter_given_A() {
            resultOf(() -> diamondOf("A")).isEqualTo("A");
        }

        @Test
        void should_create_a_diamond_given_B() {
            resultOf(() -> diamondOf("B")).isEqualTo("ABB");
        }
    }

    @Nested
    class Given_an_invalid_letter {

        @Test
        void should_fail_to_create_a_diamond_given_nil() {
            whenOutsideOperatingConditions(() -> diamondOf(null))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("Letter missing!");
        }

        @Test
        void should_fail_to_create_a_diamond_given_no_letter() {
            whenOutsideOperatingConditions(() -> diamondOf(""))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("A letter is expected!");
        }

        @Test
        void should_fail_to_create_a_diamond_given_anything_but_a_letter() {
            whenOutsideOperatingConditions(() -> diamondOf("1"))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("Only letters are expected!");
        }

        @Test
        void should_fail_to_create_a_diamond_given_several_letters() {
            whenOutsideOperatingConditions(() -> diamondOf("AB"))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("Only one letter is expected!");
        }
    }
}
