package modern_java_recipes.chapter7_io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Требуется найти в файловой системе файлы, обладающие заданными свойствами.
 */
public class d_SearchInFileSystem {

    public static void main(String[] args) {
        // Пример 7.8  Поиск обычных файлов из пакета fileio
        try (Stream<Path> paths =
                     Files.find(Paths.get("src/main/java"), Integer.MAX_VALUE,
                             (path, attributes) ->
                                     !attributes.isDirectory() && path.toString().contains("fileio"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Печатаются такие пути:
        // src/main/java/fileio/FileList.java
        // src/main/java/fileio/ProcessDictionary.java
        // src/main/java/fileio/SearchForFiles.java
        // src/main/java/fileio/WalkTheTree.java
    }

}
