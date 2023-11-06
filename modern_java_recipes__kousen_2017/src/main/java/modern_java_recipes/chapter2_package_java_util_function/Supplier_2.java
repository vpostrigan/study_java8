package modern_java_recipes.chapter2_package_java_util_function;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
Поставщик не принимает аргументов и возвращает значение.

Одно из основных применений интерфейсов семейства Supplier – поддержка отложенного выполнения.
 */
public class Supplier_2 {

    public static void main(String[] args) {
        // Реализация с помощью анонимного внутреннего класса
        DoubleSupplier randomSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return Math.random();
            }
        };
        // Лямбда-выражение
        randomSupplier = () -> Math.random();
        // Ссылка на метод
        randomSupplier = Math::random;

        // Дополнительные интерфейсы семейства Supplier
        // Интерфейс       | Единственный абстрактный метод
        // ----------------------------------------
        // IntSupplier     | int getAsInt()
        // DoubleSupplier  | double getAsDouble()
        // LongSupplier    | getAsLong()
        // BooleanSupplier | boolean getAsBoolean()

        // //

        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara",
                "Zoё", "Jayne", "Simon", "River", "Shepherd Book");
        Optional<String> first = names.stream()
                .filter(name -> name.startsWith("C"))
                .findFirst();
        System.out.println(first); // Optional.empty
        System.out.println(first.orElse("None")); // None

        // Коллекция с запятой-разделителем строится, даже если имя найдено
        String s = String.format("Ничего не найдено в %s",
                names.stream().collect(Collectors.joining(", ")));
        System.out.println(first.orElse(s));
        // Ничего не найдено в Mal, Wash, Kaylee, Inara, Zoё, Jayne, Simon, River, Shepherd Book

        // Коллекция с запятой-разделителем строится, только когда Optional пусто
        String s2 = String.format("Ничего не найдено в %s",
                names.stream().collect(Collectors.joining(", ")));
        System.out.println(first.orElseGet(() -> s2));
        // Ничего не найдено в Mal, Wash, Kaylee, Inara, Zoё, Jayne, Simon, River, Shepherd Book

        Pattern p = Pattern.compile("\\w");
    }

}
