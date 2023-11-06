package modern_java_recipes.chapter2_package_java_util_function;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

/*
Функция принимает один аргумент и возвращает значение.
 */
public class Function_4 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara",
                "Zoё", "Jayne", "Simon", "River", "Shepherd Book");

        // Анонимный внутренний класс
        List<Integer> nameLengths = names.stream()
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .collect(Collectors.toList());

        // Лямбда-выражение
        nameLengths = names.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());

        // Ссылка на метод
        nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());

        // Дополнительные интерфейсы семейства Function
        // ---------------------------------------------------
        // Интерфейс            | Единственный абстрактный метод
        // IntFunction          | R apply(int value)
        // DoubleFunction       | R apply(double value)
        // LongFunction         | R apply(long value)
        // ToIntFunction        | int applyAsInt(T value)
        // ToDoubleFunction     | double applyAsDouble(T value)
        // ToLongFunction       | long applyAsLong(T value)
        // DoubleToIntFunction  | int applyAsInt(double value)
        // DoubleToLongFunction | long applyAsLong(double value)
        // IntToDoubleFunction  | double applyAsDouble(int value)
        // IntToLongFunction    | long applyAsLong(int value)
        // LongToDoubleFunction | double applyAsDouble(long value)
        // LongToIntFunction    | int applyAsInt(long value)
        // BiFunction           | R apply(T t, U u)

        // Дополнительные интерфейсы семейства Function
        // ---------------------------------------------------
        // Интерфейс            | Единственный абстрактный метод
        // ToIntBiFunction      | int applyAsInt(T t, U u)
        // ToDoubleBiFunction   | double applyAsDouble(T t, U u)
        // ToLongBiFunction     | long applyAsLong(T t, U u)
    }

}
