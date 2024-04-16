package modern_java_recipes.chapter5_optional_type;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Имеется лямбда-выражение, которое возбуждает контролируемое исключение,
 * а в объявлении абстрактного метода функционального интерфейса,
 * который оно реализует, это исключение не упомянуто.
 */
public class j_ExceptionsAndLambda {

    public static void main(String[] args) {

    }

    // Пример 5.35  URL-кодирование коллекции строк (НЕ КОМПИЛИРУЕТСЯ)
    public List<String> encodeValues(String... values) {
        return Arrays.stream(values)
                // .map(s -> URLEncoder.encode(s, "UTF-8"))
                .collect(Collectors.toList());
    }

    // Пример 5.36  Объявление исключения (ТОЖЕ НЕ КОМПИЛИРУЕТСЯ)
    public List<String> encodeValues2(String... values) throws UnsupportedEncodingException {
        return Arrays.stream(values)
                //.map(s -> URLEncoder.encode(s, "UTF-8"))
                .collect(Collectors.toList());
    }

    // Пример 5.37  URL-кодирование с использованием блока try/catch (ПРАВИЛЬНО)
    public List<String> encodeValues3_AnonInnerClass(String... values) {
        return Arrays.stream(values)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        try {
                            return URLEncoder.encode(s, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return "";
                        }
                    }
                })
                .collect(Collectors.toList());
    }

    public List<String> encodeValues3(String... values) {
        return Arrays.stream(values)
                .map(s -> {
                    try {
                        return URLEncoder.encode(s, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.toList());
    }

    // Пример 5.38  Делегирование URL-кодирования отдельному методу
    private String encodeString(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> encodeValuesUsingMethod(String... values) {
        return Arrays.stream(values)
                .map(this::encodeString)
                .collect(Collectors.toList());
    }

}
