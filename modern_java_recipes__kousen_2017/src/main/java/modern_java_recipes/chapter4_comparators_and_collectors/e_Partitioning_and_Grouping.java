package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Требуется распределить элементы коллекции по категориям
 */
public class e_Partitioning_and_Grouping {

    public static void main(String[] args) {
        // Пример 4.20  Разбиение строк по четности длины
        final List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key, value) -> System.out.printf("%5s: %s%n", key, value));
        //
        // false: [a, strings, use, a]
        //  true: [this, is, long, list, of, to, as, demo]

        // Пример 4.21  Группировка строк по длине
        Map<Integer, List<String>> lengthMap2 = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        lengthMap2.forEach((k, v) -> System.out.printf("%d: %s%n", k, v));
        //
        // 1: [a, a]
        // 2: [is, of, to, as]
        // 3: [use]
        // 4: [this, long, list, demo]
        // 7: [strings]
    }

}
