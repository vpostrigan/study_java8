package modern_java_recipes.chapter2_package_java_util_function;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Предикат принимает аргумент и возвращает значение типа boolean.
 */
public class c_Predicate {
    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    public static final Predicate<String> STARTS_WITH_S = s -> s.startsWith("S");

    public static void main(String[] args) {
        String[] names;

        names = Stream.of("Mal", "Wash", "Kaylee", "Inara", "Zoё",
                        "Jayne", "Simon", "River", "Shepherd Book")
                .sorted()
                .toArray(String[]::new);

        c_Predicate p = new c_Predicate();

        assert "Inara, Jayne, River, Simon" == p.getNamesOfLength(5, names);
        assert "Shepherd Book, Simon" == p.getNamesStartingWith("S", names);

        assert "Inara, Jayne, River, Simon" == p.getNamesSatisfyingCondition(s -> s.length() == 5, names);
        assert "Shepherd Book, Simon" == p.getNamesSatisfyingCondition(s -> s.startsWith("S"), names);
        assert "Inara, Jayne, River, Simon" == p.getNamesSatisfyingCondition(LENGTH_FIVE, names);
        assert "Shepherd Book, Simon" == p.getNamesSatisfyingCondition(STARTS_WITH_S, names);

        // Композиция
        assert "Simon" ==
                p.getNamesSatisfyingCondition(LENGTH_FIVE.and(STARTS_WITH_S), names);
        // Композиция
        assert "Inara, Jayne, River, Shepherd Book, Simon" ==
                p.getNamesSatisfyingCondition(LENGTH_FIVE.or(STARTS_WITH_S), names);
        // Отрицание
        assert "Kaylee, Mal, Shepherd Book, Wash, Zoё" ==
                p.getNamesSatisfyingCondition(LENGTH_FIVE.negate(), names);

        // //

        // example, violates stateless from documentation
        List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Groovy", "Scala", "Scala", "test", "test", "test", "test");
        list.stream().filter(distinct(3)).forEach(System.out::println);
        // Scala
        // test
    }

    public String getNamesOfLength(int length, String... names) {
        return Arrays.stream(names)
                .filter(s -> s.length() == length) // Предикат для поиска строк заданной длины
                .collect(Collectors.joining(", "));
    }

    public String getNamesStartingWith(String s, String... names) {
        return Arrays.stream(names)
                .filter(s0 -> s0.startsWith(s))  // Предикат для поиска строк с заданным префиксом
                .collect(Collectors.joining(", "));
    }

    public String getNamesSatisfyingCondition(Predicate<String> condition, String... names) {
        return Arrays.stream(names)
                .filter(condition) // Фильтр по заданному предикату
                .collect(Collectors.joining(", "));
    }

    public static <T> Predicate<T> distinct(long atLeast) {
        Map<T, Long> map = new ConcurrentHashMap<>();
        return t -> map.merge(t, 1L, Long::sum) == atLeast;
    }

}
