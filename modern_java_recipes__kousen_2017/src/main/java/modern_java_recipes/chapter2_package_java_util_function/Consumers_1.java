package modern_java_recipes.chapter2_package_java_util_function;

import java.util.List;
import java.util.Arrays;
import java.util.function.Consumer;

/*
Потребитель принимает аргумент универсального типа и ничего не возвращает.
 */
public class Consumers_1 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        // Реализация с анонимным внутренним классом
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        // Лямбда-выражение
        strings.forEach(s -> System.out.println(s));
        // Ссылка на метод
        strings.forEach(System.out::println);

        // //

        // Дополнительные интерфейсы семейства Consumer
        // Интерфейс      | Единственный абстрактный метод
        // ----------------------------------------------
        // IntConsumer    | void accept(int x)
        // DoubleConsumer | void accept(double x)
        // LongConsumer   | void accept(long x)
        // BiConsumer     | void accept(T t, U u)
        // ObjIntConsumer<T>
        // ObjLongConsumer
        // ObjDoubleConsumer

        // //

        // Optional.ifPresent(Consumer<? super T> consumer)

        // Stream.forEach(Consumer<? super T> action)
        // Stream.forEachOrdered
        // Stream.peek(Consumer<? super T> action) // для отладки
    }

}
