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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

public class Diamond {

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
        List<String> topHalf = givenLetter
                .streamOfCodes()
                .mapToObj(Line::new)
                .map(Line::build)
                .collect(toList());
        List<String> downHalf = new ArrayList<>(topHalf.subList(0, topHalf.size() - 1));
        reverse(downHalf);
        return assembly(topHalf, downHalf);
    }

    private String assembly(List<String> topHalf, List<String> downHalf) {
        return Stream
                .of(topHalf, downHalf)
                .flatMap(List::stream)
                .collect(joining(LINE_SEPARATOR));
    }

    private class Line {

        private final Integer code;

        private Line(Integer code) {
            this.code = code;
        }

        private String build() {
            String letterForLine = valueOf((char) code.intValue());
            return indentation() + (isA() ? letterForLine : letterForLine + spacing() + letterForLine);
        }

        private boolean isA() {
            return code == Letter.CODE_OF_BIG_A || code == Letter.CODE_OF_LITTLE_A;
        }

        private String indentation() {
            return repeatSpace(givenLetter.getCode() - code);
        }

        private String spacing() {
            return repeatSpace(2 * (code - givenLetter.getMatchingFirstCode() - 1) + 1);
        }

        private String repeatSpace(Integer times) {
            return generate(() -> ONE_SPACE)
                    .limit(times)
                    .collect(joining());
        }
    }
}
