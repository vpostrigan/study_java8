package modern_java_recipes.chapter6_optional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хотя Optional – ссылочный тип, ему никогда не следует присваивать значение null.
 * Это считается серьезной ошибкой.
 *
 * Требуется вернуть объект Optional, содержащий известное значение.
 */
public class a_Creating_Optional {

    public static void main(String[] args) {
        // Пример 6.1  Являются ли объекты Optional неизменяемыми?
        AtomicInteger counter = new AtomicInteger();
        Optional<AtomicInteger> opt = Optional.ofNullable(counter);
        System.out.println(opt); // Optional[0]

        counter.incrementAndGet();
        System.out.println(opt); // Optional[1]

        opt.get().incrementAndGet();
        System.out.println(opt); // Optional[2]

        // Ссылке на Optional можно присвоить новое значение
        opt = Optional.ofNullable(new AtomicInteger());

        // //

        // статические фабричные методы создания объектов типа Optional:
        // static <T> Optional<T> empty()
        // static <T> Optional<T> of(T value)
        // static <T> Optional<T> ofNullable(T value)

    }

    // Пример 6.2  Создание Optional методом of
    public static <T> Optional<T> createOptionalTheHardWay(T value) {
        return value == null ? Optional.empty() : Optional.of(value);
    }

    // Пример 6.3  Создание Optional методом ofNullable
    public static <T> Optional<T> createOptionalTheEasyWay(T value) {
        return Optional.ofNullable(value); // (внутри Пример 6.2)
    }

}
