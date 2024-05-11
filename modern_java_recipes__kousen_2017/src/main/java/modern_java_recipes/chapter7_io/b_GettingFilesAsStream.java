package modern_java_recipes.chapter7_io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Требуется обработать все файлы в каталоге как поток.
 */
public class b_GettingFilesAsStream {

    public static void main(String[] args) {
        // Пример 7.6  Использование метода Files.list(path)
        try (Stream<Path> list = Files.list(Paths.get("src/main/java"))) {
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // src\main\java\modern_java_recipes

    }

}
