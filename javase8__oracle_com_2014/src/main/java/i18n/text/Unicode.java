package i18n.text;

public class Unicode {

    // Creating a String from a Code Point
    String newString1(int codePoint) {
        return new String(Character.toChars(codePoint));
    }

    // Creating a String from a Code Point - Optimized for BMP Characters
    String newString2(int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf(codePoint);
        } else {
            return new String(Character.toChars(codePoint));
        }
    }

    // Creating String Objects in Bulk
    String[] newStrings3(int[] codePoints) {
        String[] result = new String[codePoints.length];
        char[] codeUnits = new char[2];
        for (int i = 0; i < codePoints.length; i++) {
            int count = Character.toChars(codePoints[i], codeUnits, 0);
            result[i] = new String(codeUnits, 0, count);
        }
        return result;
    }

    // Generating Messages
    // The formatting API supports supplementary characters. The following example is a simple way to generate a message.
    // recommended
    // System.out.printf("Character %c is invalid.%n", codePoint);

}
