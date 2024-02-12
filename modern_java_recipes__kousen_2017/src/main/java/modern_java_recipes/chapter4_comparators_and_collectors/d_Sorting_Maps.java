package modern_java_recipes.chapter4_comparators_and_collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * отсортировать отображение по ключу или по значению.
 */
public class d_Sorting_Maps {

    public static void main(String[] args) {

        Path dictionary = null;

        // Пример 4.18  Чтение файла словаря в отображение
        System.out.println("\nРаспределение числа слов по длинам:");
        try (Stream<String> lines = Files.lines(dictionary)) {
            lines
                    .filter(s -> s.length() > 20)
                    .collect(
                            // Collectors.groupingBy(String::length) => Map<Integer,List<String>>
                            Collectors.groupingBy(String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.printf("%d: %d%n", len, num));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Распределение числа слов по длинам:
        // 21: 82
        // 22: 41
        // 23: 17
        // 24: 5

        // Пример 4.19  Сортировка отображения по ключу
        System.out.println("\nРаспределение числа слов по длинам (в порядке убывания):");
        try (Stream<String> lines = Files.lines(dictionary)) {
            Map<Integer, Long> map =
                    lines
                            .filter(s -> s.length() > 20)
                            .collect(
                                    Collectors.groupingBy(String::length, Collectors.counting()));
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Длина %d: %2d слов%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Распределение числа слов по длинам (в порядке убывания):
        // Длина 24: 5 слов
        // Длина 23: 17 слов
        // Длина 22: 41 слов
        // Длина 21: 82 слов
    }

}
