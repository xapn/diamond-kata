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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.generate;
import static testasyouthink.TestAsYouThink.resultOf;
import static testasyouthink.TestAsYouThink.whenOutsideOperatingConditions;

class DiamondTest {

    public static class Diamond {

        private static final int CODE_OF_A = "A".codePointAt(0);
        private static final String LINE_SEPARATOR = "\n";
        private static final String ONE_SPACE = " ";
        private final Letter givenLetter;

        private Diamond(String letter) {
            givenLetter = new Letter(letter);
        }

        public static String of(String letter) {
            Diamond diamond = new Diamond(letter);
            return diamond.crystallize();
        }

        private String crystallize() {
            List<String> topHalf = rangeClosed(CODE_OF_A, givenLetter.getCode())
                    .mapToObj(this::lineOf)
                    .collect(toList());
            List<String> downHalf = new ArrayList<>(topHalf.subList(0, topHalf.size() - 1));
            reverse(downHalf);
            return assembly(topHalf, downHalf);
        }

        private String lineOf(Integer code) {
            String letterForLine = valueOf((char) code.intValue());
            return indentation(code) + (code == CODE_OF_A ? letterForLine : letterForLine + spacing(
                    code) + letterForLine);
        }

        private String indentation(Integer code) {
            return repeatSpace(givenLetter.getCode() - code);
        }

        private String spacing(Integer code) {
            return repeatSpace(2 * (code - CODE_OF_A - 1) + 1);
        }

        private String repeatSpace(Integer times) {
            return generate(() -> ONE_SPACE)
                    .limit(times)
                    .collect(joining());
        }

        private String assembly(List<String> topHalf, List<String> downHalf) {
            return Stream
                    .of(topHalf, downHalf)
                    .flatMap(List::stream)
                    .collect(joining(LINE_SEPARATOR));
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

        int getCode() {
            return letter.codePointAt(0);
        }
    }

    @Nested
    class Given_a_valid_letter {

        @Test
        void should_create_a_diamond_splinter_given_A() {
            resultOf(() -> Diamond.of("A")).isEqualTo("A");
        }

        @Test
        void should_create_a_diamond_given_B() {
            resultOf(() -> Diamond.of("B")).isEqualTo(" A\n" //
                    + "B B\n" //
                    + " A");
        }

        @Test
        void should_create_a_diamond_given_C() {
            resultOf(() -> Diamond.of("C")).isEqualTo("  A\n" //
                    + " B B\n" //
                    + "C   C\n" //
                    + " B B\n" //
                    + "  A");
        }

        @Test
        void should_create_a_diamond_given_D() {
            resultOf(() -> Diamond.of("D")).isEqualTo("   A\n" //
                    + "  B B\n" //
                    + " C   C\n" //
                    + "D     D\n" //
                    + " C   C\n" //
                    + "  B B\n" //
                    + "   A");
        }
    }

    @Nested
    class Given_an_invalid_letter {

        @Test
        void should_fail_to_create_a_diamond_given_nil() {
            whenOutsideOperatingConditions(() -> Diamond.of(null))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("Letter missing!");
        }

        @Test
        void should_fail_to_create_a_diamond_given_no_letter() {
            whenOutsideOperatingConditions(() -> Diamond.of(""))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("A letter is expected!");
        }

        @Test
        void should_fail_to_create_a_diamond_given_anything_but_a_letter() {
            whenOutsideOperatingConditions(() -> Diamond.of("1"))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("Only letters are expected!");
        }

        @Test
        void should_fail_to_create_a_diamond_given_several_letters() {
            whenOutsideOperatingConditions(() -> Diamond.of("AB"))
                    .thenItFails()
                    .becauseOf(IllegalArgumentException.class)
                    .withMessage("Only one letter is expected!");
        }
    }
}
