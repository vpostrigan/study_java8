package modern_java_recipes.chapter5_optional_type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Требуется обойти коллекцию или отображение.
 */
public class f_forEach {

    public static void main(String[] args) {
        // Пример 5.17  Обход линейной коллекции
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);

        // [1]
        integers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        // [2]
        integers.forEach((Integer n) -> {
            System.out.println(n);
        });

        // [3]
        integers.forEach(n -> System.out.println(n));

        // [4]
        integers.forEach(System.out::println);


        // Пример 5.18  Обход Map
        Map<Long, String> map = new HashMap<>();
        map.put(86L, "Don Adams (Maxwell Smart)");
        map.put(99L, "Barbara Feldon");
        map.put(13L, "David Ketchum");
        map.forEach((num, agent) ->
                System.out.printf("Агента %d играл(а) %s%n", num, agent));
    }

}
