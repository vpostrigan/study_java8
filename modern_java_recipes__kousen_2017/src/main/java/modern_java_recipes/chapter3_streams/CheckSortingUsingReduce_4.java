package modern_java_recipes.chapter3_streams;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Требуется проверить, отсортирован ли поток.
 */
public class CheckSortingUsingReduce_4 {

    public static void main(String[] args) {
        // Пример 3.29  Суммирование BigDecimal с помощью reduce
        BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                // Использование метода add класса BigDecimal в качестве BinaryOperator
                .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val));
        System.out.println("Сумма равна " + total);

        // Пример 3.30  Сортировка строк по длине
        List<String> strings = Arrays.asList(
                "this", "is", "a", "list", "of", "strings");
        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        // Получается [“a”, “is”, “of”, “this”, “list”, “strings”]

        // для тестирования порядка проверить рядом стоящие элементы

        // Пример 3.31  Проверка правильности сортировки потока строк
        // (поток должен быть последовательным и упорядоченным)
        strings.stream()
                .reduce((prev, curr) -> {
                    // assertTrue(prev.length() <= curr.length()); // проверка
                    return curr; // curr становится следующим значением prev
                });
    }

}
