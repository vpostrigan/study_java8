package modern_java_recipes.chapter3_streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class c_ReductionOperations {

    public static void main(String[] args) {
        // [Встроенные операции редукции]

        // Операции редукции в интерфейсе IntStream
        // Метод                                    Тип возвращаемого значения
        // average                                  OptionalDouble
        // count                                    Long
        // max                                      OptionalInt
        // min                                      OptionalInt
        // sum                                      Int
        // summaryStatistics                        IntSummaryStatistics
        // collect(Supplier<R> supplier,            R
        //   ObjIntConsumer<R> accumulator,
        //   BiConsumer<R,R> combiner)
        // reduce                                   int, OptionalInt

        // Пример 3.14  Операции редукции для потока IntStream
        String[] strings = "this is an array of strings".split(" ");
        long count = Arrays.stream(strings)
                .map(String::length)
                .count();
        System.out.println("Всего существует " + count + " строк");
        // Всего существует 6 строк

        int totalLength = Arrays.stream(strings)
                .mapToInt(String::length)
                .sum();
        System.out.println("Суммарная длина равна " + totalLength);
        // Суммарная длина равна 22

        OptionalDouble ave = Arrays.stream(strings)
                .mapToInt(String::length)
                .average();
        System.out.println("Средняя длина равна " + ave);
        // Средняя длина равна OptionalDouble[3.6666666666666665]

        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();
        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        System.out.println("Максимальная и минимальная длины равны " + max + " и " + min);
        // Максимальная и минимальная длины равны OptionalInt[7] и OptionalInt[2]


        // [Базовые реализации редукции]
        // метод sum
        // Пример 3.15  Суммирование чисел с помощью reduce
        int sum1 = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y).orElse(0);

        int sum2 = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> {
                    System.out.printf("x=%d, y=%d%n", x, y);
                    return x + y;
                }).orElse(0);
        //        x=1, y=2          начальные значения x и y равны 1 и 2 (первые два элемента потока)
        //        x=3, y=3
        //        x=6, y=4
        //        x=10, y=5
        //        x=15, y=6
        //        x=21, y=7
        //        x=28, y=8
        //        x=36, y=9
        //        x=45, y=10

        // Пример 3.18  Удвоение значений в процессе суммирования (НЕПРАВИЛЬНО - первое значение не было удвоено.)
        int doubleSum1 = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + 2 * y).orElse(0);
        // Пример 3.19  Удвоение значений в процессе суммирования (ПРАВИЛЬНО)
        int doubleSum2 = IntStream.rangeClosed(1, 10)
                .reduce(0, (x, y) -> x + 2 * y);


        // [Библиотечные бинарные операторы]
        // Пример 3.21  Выполнение редукции с помощью бинарного оператора
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(0, Integer::sum);
        System.out.println(sum); // 55

        // Пример 3.22  Нахождение максимума с помощью редукции
        Integer max2 = Stream.of(3, 1, 4, 1, 5, 9)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("Максимальное значение равно " + max2); // Максимальное значение равно 9

        // Пример 3.23  Конкатенация потока строк с помощью редукции
        String s = Stream.of("this", "is", "a", "list")
                .reduce("", String::concat);
        // String concat(String str)
        // первый параметр становится объектом, от имени которого вызывается метод concat, а второй – аргументом concat
        System.out.println(s); // thisisalist


        // [Использование коллекторов]
        // Пример 3.24  Собирание строк с помощью StringBuilder
        String s21 = Stream.of("this", "is", "a", "list")
                .collect(() -> new StringBuilder(), // Поставщик результата
                        (sb, str) -> sb.append(str), // Добавить к результату одно значение
                        (sb1, sb2) -> sb1.append(sb2)) // Объединить два результата
                .toString();
        // или
        // Пример 3.25  Собирание строк с помощью ссылок на методы
        String s22 = Stream.of("this", "is", "a", "list")
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
        // или
        Stream.of("this", "is", "a", "list")
                .collect(Collectors.joining());


        // [Самая общая форма редукции] (третий вариант)
        class Book {
            private Integer id;
            private String title;
            // ... конструкторы, методы чтения и установки, toString, equals, hashCode ...

            public Integer getId() {
                return id;
            }
        }
        HashMap<Integer, Book> bookMap = Stream.of(new Book(), new Book())
                .reduce(new HashMap<Integer, Book>(), // Нейтральное значение для putAll
                        (map, book) -> { // Поместить одну книгу в Map методом put
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> { // Объединить две структуры Map методом putAll
                            map1.putAll(map2);
                            return map1;
                        });
        bookMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }

}
