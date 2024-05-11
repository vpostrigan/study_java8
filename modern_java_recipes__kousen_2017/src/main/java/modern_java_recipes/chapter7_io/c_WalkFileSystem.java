package modern_java_recipes.chapter7_io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Требуется обойти файловую систему в глубину.
 */
public class c_WalkFileSystem {

    public static void main(String[] args) {
        // Пример 7.7  Обход дерева
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // src\main\java
        // src\main\java\modern_java_recipes
        // src\main\java\modern_java_recipes\1.txt
        // src\main\java\modern_java_recipes\chapter1_the_basics
        // src\main\java\modern_java_recipes\chapter1_the_basics\a_LambdaExpressions.java
        // src\main\java\modern_java_recipes\chapter1_the_basics\b_MethodReferences.java
        // src\main\java\modern_java_recipes\chapter1_the_basics\c_ConstructorReferences.java
        // src\main\java\modern_java_recipes\chapter1_the_basics\d_FunctionalInterfaces.java
        // src\main\java\modern_java_recipes\chapter1_the_basics\e_StaticMethodsInInterfaces.java


    }

}
