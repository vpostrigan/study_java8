package modern_java_recipes.chapter5_optional_type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Код внутри лямбда-выражения должен возбудить исключение, но мы не хотим
 * загромождать лямбда-выражение кодом обработки исключения.
 */
public class i_ExternalMethodForProcessingExceptions {

    public static void main(String[] args) {
        // Пример 5.32  Клиентский код
        List<Integer> values = Arrays.asList(30, 10, 40, 10, 50, 90);
        List<Integer> scaled = div(values, 10);
        System.out.println(scaled);
        // печатается: [3, 1, 4, 1, 5, 9]

        scaled = div(values, 0);
        System.out.println(scaled);
        // возбуждает ArithmeticException: / деление на 0
    }

    // Пример 5.31  Лямбда-выражение, которое может возбуждать неконтролируемое исключение
    private static List<Integer> div(List<Integer> values, Integer factor) {
        return values.stream()
                .map(n -> n / factor) // Может возбуждать ArithmeticException
                .collect(Collectors.toList());
    }

    // Пример 5.33  Лямбда-выражение с блоком try/catch
    private static List<Integer> div2(List<Integer> values, Integer factor) {
        return values.stream()
                .map(n -> {
                    try {
                        return n / factor;
                    } catch (ArithmeticException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    // //

    // Пример 5.34  Вынесение лямбда-выражения в отдельный метод
    private static Integer divide(Integer value, Integer factor) {
        try {
            return value / factor;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Integer> divUsingMethod(List<Integer> values, Integer factor) {
        return values.stream()
                .map(n -> divide(n, factor))
                .collect(Collectors.toList());
    }

}
