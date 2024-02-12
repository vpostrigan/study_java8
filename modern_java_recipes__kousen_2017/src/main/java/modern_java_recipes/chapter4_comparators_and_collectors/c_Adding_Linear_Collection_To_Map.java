package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class c_Adding_Linear_Collection_To_Map {

    public static void main(String[] args) {
        // Пример 4.14  Коллекция книг
        List<Book> books = Arrays.asList(
                new Book(1, "Modern Java Recipes", 49.99),
                new Book(2, "Java 8 in Action", 49.99),
                new Book(3, "Java SE8 for the Really Impatient", 39.99),
                new Book(4, "Functional Programming in Java", 27.64),
                new Book(5, "Making Java Groovy", 45.99),
                new Book(6, "Gradle Recipes for Android", 23.76));

        // Пример 4.15  Добавление книг в отображение
        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));
        // или
        bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));
        bookMap = books.stream()
                .collect(Collectors.toMap(b -> b.getId(), b -> b));
    }

    // Пример 4.13  Простой класс, представляющий книгу
    private static class Book {
        private int id;
        private String name;
        private double price;

        public Book(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }
    }

}
