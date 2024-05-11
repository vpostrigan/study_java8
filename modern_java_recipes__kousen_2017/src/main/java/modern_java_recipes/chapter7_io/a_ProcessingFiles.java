package modern_java_recipes.chapter7_io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Требуется обработать содержимое текстового файла с помощью потоков.
 */
public class a_ProcessingFiles {

    public static void main(String[] args) {
        // Пример 7.1  Нахождение 10 самых длинных слов в словаре web2
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            lines.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
            // formaldehydesulphoxylate (24)
            // pathologicopsychological (24)
            // scientificophilosophical (24)
            // tetraiodophenolphthalein (24)
            // thyroparathyroidectomize (24)
            // anthropomorphologically (23)
            // blepharosphincterectomy (23)
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Пример 7.3  Нахождение количества слов каждой длины
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            lines.filter(s -> s.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.println(len + ": " + num));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 21: 82
        // 22: 41
        // 23: 17
        // 24: 5

        // Пример 7.4  Количество слов каждой длины в порядке убывания длин
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))) {
            Map<Integer, Long> map =
                    lines.filter(s -> s.length() > 20)
                            .collect(Collectors.groupingBy(String::length, Collectors.counting()));
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Длина %d: %d слов%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Длина 24: 5 слов
        // Длина 23: 17 слов
        // Длина 22: 41 слов
        // Длина 21: 82 слов

        // Пример 7.5  Использование метода BufferedReader.lines
        try (Stream<String> lines =
                     new BufferedReader(
                             new FileReader("/usr/share/dict/words")).lines()) {
            // ...то же, что в предыдущем примере ...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
