package modern_java_recipes.chapter3_streams;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * You want to know how many elements are in a stream.
 */
public class CountingElements_7 {

    public static void main(String[] args) {
        // Пример 3.39  Подсчет элементов в потоке
        long count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).count();
        System.out.printf("В потоке %d элементов%n", count); // В потоке 9 элементов

        // count можно заменить на
        // return mapToLong(e -> 1L).sum();

        // альтернатива (для подчиненного коллектора)
        count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .collect(Collectors.counting());
        System.out.printf("В потоке %d элементов%n", count); // В потоке 9 элементов

        // Пример 3.41  Подсчет строк в группах по длине
        Map<Boolean, Long> numberLengthMap = Stream.of("1", "12", "123")
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0, Collectors.counting()));
        numberLengthMap.forEach((k, v) -> System.out.printf("%5s: %d%n", k, v));
        //false: 2
        // true: 1
    }

}
