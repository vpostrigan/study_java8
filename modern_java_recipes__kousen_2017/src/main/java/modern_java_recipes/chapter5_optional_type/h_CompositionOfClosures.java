package modern_java_recipes.chapter5_optional_type;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Требуется последовательно применить ряд небольших независимых функций.
 */
public class h_CompositionOfClosures {

    public static void main(String[] args) {
        // Пример 5.24  Использование методов compose и andThen
        Function<Integer, Integer> add2 = x -> {
            System.out.println(x + " + 2");
            return x + 2;
        };
        Function<Integer, Integer> mult3 = x -> {
            System.out.println(x + " * 3");
            return x * 3;
        };

        // Метод compose применяет свой аргумент до (before) исходной функции
        Function<Integer, Integer> mult3add2 = add2.compose(mult3);
        // а метод andThen – после нее
        Function<Integer, Integer> add2mmult3 = add2.andThen(mult3);

        // 2 * 3
        // 6 + 2
        // 8
        System.out.println(mult3add2.apply(2));

        // 2 + 2
        // 4 * 3
        // 12
        System.out.println(add2mmult3.apply(2)); // 12


        // Пример 5.25  Выделение целого числа из строки и прибавление к нему 2
        Function<Integer, Integer> add22 = x -> x + 2;
        Function<String, Integer> parseThenAdd2 = add22.compose(Integer::parseInt);
        System.out.println(parseThenAdd2.apply("1"));
        // печатается 3

        // Пример 5.26  Прибавить число, затем преобразовать в строку
        Function<Integer, String> plus2toString = add22.andThen(Objects::toString);
        System.out.println(plus2toString.apply(1));
        // печатается "3"


        // Пример 5.27  Композиция замыканий с помощью потребителей
        //default Consumer<T> andThen(Consumer<? super T> after)
        // Пример 5.28  Составной потребитель для печати и протоколирования
        //Logger log = Logger.getLogger(...);
        Consumer<String> printer = System.out::println;
        Consumer<String> logger = System.out::println;//log::info;
        Consumer<String> printThenLog = printer.andThen(logger);
        Stream.of("this", "is", "a", "stream", "of", "strings")
                .forEach(printThenLog);


        // Пример 5.29  Методы композиции в интерфейсе Predicate
        //default Predicate<T> and(Predicate<? super T> other) // логических операций И
        //default Predicate<T> negate() // НЕ
        //default Predicate<T> or(Predicate<? super T> other) // ИЛИ

        IntPredicate triangular = h_CompositionOfClosures::isTriangular;
        IntPredicate perfect = h_CompositionOfClosures::isPerfect;
        IntPredicate both = triangular.and(perfect);
        IntStream.rangeClosed(1, 10_000)
                .filter(both)
                .forEach(System.out::println);
        // 1
        // 36
        // 1225
    }

    // Пример 5.30  Треугольные числа, являющиеся также квадратными
    public static boolean isPerfect(int x) { // Примеры: 1, 4, 9, 16, 25, 36, 49, 64, 81, …
        return Math.sqrt(x) % 1 == 0;
    }

    public static boolean isTriangular(int x) { // Примеры: 1, 3, 6, 10, 15, 21, 28, 36, 45, …
        double val = (Math.sqrt(8 * x + 1) - 1) / 2;
        return val % 1 == 0;
    }

}
