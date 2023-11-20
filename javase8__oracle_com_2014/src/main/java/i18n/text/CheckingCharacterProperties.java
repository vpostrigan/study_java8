package i18n.text;

public class CheckingCharacterProperties {

    public static void main(String[] args) {
        char ch = '0';

        // This code is WRONG!
        // check if ch is a letter
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
        }
        // check if ch is a digit
        if (ch >= '0' && ch <= '9') {
        }
        // check if ch is a whitespace
        if ((ch == ' ') || (ch == '\n') || (ch == '\t')) {
        }

        // This code is OK!
        if (Character.isLetter(ch)) {
        }
        if (Character.isDigit(ch)) {
        }
        if (Character.isSpaceChar(ch)) {
        }

        // //

        // The Character.getType method returns the Unicode category of a character
        if (Character.getType('a') == Character.LOWERCASE_LETTER) {
        }
        if (Character.getType('R') == Character.UPPERCASE_LETTER) {
        }
        if (Character.getType('>') == Character.MATH_SYMBOL) {
        }
        if (Character.getType('_') == Character.CONNECTOR_PUNCTUATION) {
        }
    }

}
