package modern_java_recipes.chapter6_optional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class b_ValueFromOptional {

    public static void main(String[] args) {
        // Пример 6.4  Получение первой строки четной длины
        Optional<String> firstEven =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();

        // Пример 6.5  Получение первой строки нечетной длины
        Optional<String> firstOdd =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 != 0)
                        .findFirst();
        // System.out.println(firstOdd.get()); // возбуждается NoSuchElementException

        // Пример 6.6  Получение первой строки четной длины с защитой вызова get
        Optional<String> first =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        System.out.println(first.isPresent() ? first.get() : "Нет строк четной длины");

        // Пример 6.7  Использование orElse
        Optional<String> firstOdd2 =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 != 0)
                        .findFirst();
        System.out.println(firstOdd2.orElse("Нет строк нечетной длины"));

        // Пример 6.8  Использование Supplier в методе orElseGet
        // Optional<ComplexObject> val = values.stream.findFirst()
        // val.orElse(new ComplexObject()); // Всегда создается новый объект
        // val.orElseGet(() -> new ComplexObject()); // Объект создается только при необходимости

        // Пример 6.10  Использование метода orElseThrow
        Optional<String> first2 =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        System.out.println(first2.orElseThrow(NoSuchElementException::new));

        // Пример 6.11  Использование метода ifPresent
        Optional<String> first3 =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 == 0)
                        .findFirst();
        first3.ifPresent(val -> System.out.println("Найдена строка четной длины"));

        first3 =
                Stream.of("five", "even", "length", "string", "values")
                        .filter(s -> s.length() % 2 != 0)
                        .findFirst();
        first3.ifPresent(val -> System.out.println("Найдена строка нечетной длины"));
    }

}
