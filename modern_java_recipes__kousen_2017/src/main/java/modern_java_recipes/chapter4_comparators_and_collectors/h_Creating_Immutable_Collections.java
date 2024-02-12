package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Требуется создать неизменяемый список, множество или отображение средствами API потоков.
 */
public class h_Creating_Immutable_Collections {

    public static void main(String[] args) {
        // Пример 4.33  Создание немодифицируемого отображения
        Map<String, Integer> map = Collections.unmodifiableMap(
                new HashMap<String, Integer>() {{
                    put("have", 1);
                    put("the", 2);
                    put("high", 3);
                    put("ground", 4);
                }});

        // Java 9, весь этот рецепт можно заменить очень простым набором фабричных методов: List.of, Set.of и Map.of
    }

    // Пример 4.32  Создание немодифицируемых списков и множеств в Java 8
    // ... определить класс со следующими методами ...
    @SafeVarargs
    public final <T> List<T> createImmutableList(T... elements) {
        return Arrays.stream(elements)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    @SafeVarargs
    public final <T> Set<T> createImmutableSet(T... elements) {
        return Arrays.stream(elements)
                .collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
    }

}
