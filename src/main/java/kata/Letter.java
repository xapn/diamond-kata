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

class Letter {

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
        return !letter.matches("^[A-Za-z]*$");
    }

    private boolean moreThanOneLetter() {
        return letter.length() > 1;
    }

    int getCode() {
        return letter.codePointAt(0);
    }

    String getValue() {
        return letter;
    }
}
