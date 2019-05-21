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

import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;

class Letter {

    static final int CODE_OF_BIG_A = "A".codePointAt(0);
    static final int CODE_OF_LITTLE_A = "a".codePointAt(0);
    private final String letter;
    private int matchingFirstCode;

    Letter(String letter) {
        this.letter = letter;
        validate();
        matchingFirstCode = isUpperCase() ? CODE_OF_BIG_A : CODE_OF_LITTLE_A;
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
        return !letter.matches("^[A-Za-z]*$");
    }

    private boolean moreThanOneLetter() {
        return letter.length() > 1;
    }

    IntStream streamOfCodes() {
        return rangeClosed(matchingFirstCode, getCode());
    }

    int getMatchingFirstCode() {
        return matchingFirstCode;
    }

    int getCode() {
        return letter.codePointAt(0);
    }

    private boolean isUpperCase() {
        return letter.matches("^[A-Z]$");
    }
}
