package modern_java_recipes.chapter1_the_basics;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class a_LambdaExpressions {

    public static void main(String[] args) {
        // A functional interface is an interface with a single abstract method (SAM)
        // Функциональный интерфейс – это интерфейс, имеющий единственный абстрактный метод.


        // Example 1-1. Anonymous Inner Class Implementation of Runnable
        // Пример 1.1  Анонимный внутренний класс, реализующий интерфейс Runnable

        // Works in Java 7 or earlier:
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("inside Runnable using an anonymous inner class");
                    }
                }
        ).start();


        // Example 1-2. Using a lambda expression in a Thread constructor
        // Пример 1.2  Использование лямбда-выражения в конструкторе Thread
        new Thread(() -> System.out.println("inside Thread constructor using lambda")).start();


        // Example 1-3. Assigning a lambda expression to a variable
        // Пример 1.3  Присваивание лямбда-выражения переменной
        Runnable r = () -> System.out.println("lambda expression implementing the run method");
        new Thread(r).start();


        // //


        // Example 1-4. An anonymous inner class implementation of FilenameFilter
        // Пример 1.4  Реализация FilenameFilter с помощью анонимного внутреннего класса
        File directory = new File("./src/main/java");
        String[] names = directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        System.out.println(Arrays.asList(names));


        // Example 1-5. Lambda expression implementing FilenameFilter
        // Пример 1.5  Лямбда-выражение, реализующее интерфейс FilenameFilter
        directory = new File("./src/main/java");
        names = directory.list((dir, name) -> // Лямбда-выражение
                name.endsWith(".java"));
        System.out.println(Arrays.asList(names));


        // Example 1-6. Lambda expression is explicit data types
        // Пример 1.6  Лямбда-выражение с явно заданными типами данных
        directory = new File("./src/main/java");
        names = directory.list((File dir, String name) -> // Явные типы данных
                name.endsWith(".java"));
        System.out.println(Arrays.asList(names));


        // Example 1-7. A block lambda
        // Пример 1.7  Блочное лямбда-выражение
        directory = new File("./src/main/java");
        names = directory.list((File dir, String name) -> { // Блочный синтаксис
            return name.endsWith(".java");
        });
        System.out.println(Arrays.asList(names));
    }

}
