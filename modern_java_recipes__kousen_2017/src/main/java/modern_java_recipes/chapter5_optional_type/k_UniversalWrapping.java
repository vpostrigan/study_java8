package modern_java_recipes.chapter5_optional_type;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Имеется лямбда-выражение, возбуждающее исключение.
 * Требуется универсальная обертка, которая перехватывает все контролируемые исключения
 * и повторно возбуждает их как неконтролируемые.
 */
public class k_UniversalWrapping {

    public static void main(String[] args) {

    }

    // Пример 5.39  Основанный на Function функциональный интерфейс, который возбуждает Exception
    @FunctionalInterface
    interface FunctionWithException<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    // Пример 5.40  Метод-обертка для обработки исключений
    private static <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    // Пример 5.41  Использование универсального метода-обертки
    public List<String> encodeValuesWithWrapper(String... values) {
        return Arrays.stream(values)
                .map(wrapper(s -> URLEncoder.encode(s, "UTF-8")))
                .collect(Collectors.toList());
    }

}
