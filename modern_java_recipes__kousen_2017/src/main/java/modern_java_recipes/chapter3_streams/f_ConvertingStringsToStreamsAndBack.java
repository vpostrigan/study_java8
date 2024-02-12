package modern_java_recipes.chapter3_streams;

import java.util.stream.Stream;

public class f_ConvertingStringsToStreamsAndBack {

    public static void main(String[] args) {
        f_ConvertingStringsToStreamsAndBack demo = new f_ConvertingStringsToStreamsAndBack();

        // true
        System.out.println(Stream.of("Madam, in Eden, I’m Adam",
                        "Go hang a salami; I’m a lasagna hog",
                        "Flee to me, remote elf!",
                        "A Santa pets rats as Pat taps a star step at NASA")
                .allMatch(demo::isPalindrome2));

        // false
        System.out.println(demo.isPalindrome2("Это НЕ палиндром"));
    }

    // Пример 3.36  Проверка на палиндром в Java 7 и более ранних версиях
    public boolean isPalindrome1(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    // Пример 3.37  Проверка на палиндром с помощью потоков Java 8
    public boolean isPalindrome2(String s) {
        String forward = s.toLowerCase().codePoints()
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }

}
