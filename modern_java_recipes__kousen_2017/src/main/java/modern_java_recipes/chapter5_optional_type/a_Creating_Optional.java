package modern_java_recipes.chapter5_optional_type;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Нужны статические служебные методы для проверки на null, сравнения и т. п.
 */
public class a_Creating_Optional {

    public static void main(String[] args) {
        // Методы в Objects могут выступать в роли предикатов в фильтрах.

        // Пример 5.1  Возврат полной коллекции и фильтрация null
        List<String> strings = Arrays.asList(
                "this", null, "is", "a", null, "list", "of", "strings", null);
        List<String> nonNullStrings = strings.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Пример 5.2  Тестирование фильтра
        List<String> strings2 =
                Arrays.asList("this", "is", "a", "list", "of", "strings");
        assert Objects.deepEquals(strings2, nonNullStrings);
    }

    // Пример 5.3  Фильтрация null в любом списке
    public <T> List<T> getNonNullElements(List<T> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
