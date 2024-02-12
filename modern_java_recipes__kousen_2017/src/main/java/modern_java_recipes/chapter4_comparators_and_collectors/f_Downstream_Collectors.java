package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Требуется произвести постобработку коллекций, возвращенных операцией groupingBy или partitioningBy.
 */
public class f_Downstream_Collectors {

    public static void main(String[] args) {
        // Пример 4.22  Разбиение строк по четности длины
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key, value) -> System.out.printf("%5s: %s%n", key, value));
        //
        // false: [a, strings, use, a]
        // true: [this, is, long, list, of, to, as, demo]

        // Пример 4.23  Подсчет количества строк в каждой категории
        Map<Boolean, Long> numberLengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0,
                        Collectors.counting()));
        numberLengthMap.forEach((k, v) -> System.out.printf("%5s: %d%n", k, v));
        //
        // false: 4
        // true: 8

        // Таблица 4.2. Соответствие между методами класса Collectors и интерфейса Stream
        // Stream                    | Collectors
        // ---------------------------------------------
        // count                     | counting
        // map                       | mapping
        // min                       | minBy
        // max                       | maxBy
        // IntStream.sum             | summingInt
        // DoubleStream.sum          | summingDouble
        // LongStream.sum            | summingLong
        // IntStream.summarizing     | summarizingInt
        // DoubleStream.summarizing  | summarizingDouble
        // LongStream.summarizing    | summarizingLong
    }

}