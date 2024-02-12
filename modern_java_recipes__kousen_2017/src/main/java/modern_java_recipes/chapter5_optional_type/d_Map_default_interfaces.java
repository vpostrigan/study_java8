package modern_java_recipes.chapter5_optional_type;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class d_Map_default_interfaces {

    public static void main(String[] args) {
        // Таблица 5.1. Методы по умолчанию интерфейса Map
        //   Метод          |    Назначение
        // compute             Вычислить новое значение по существующему ключу и значению
        // computeIfAbsent     Вернуть значение заданного ключа, если он присутствует, или использовать заданную
        //                     функцию для вычисления нового значения и сохранения его в отображении
        // computeIfPresent    Вычислить новое значение вместо существующего
        // forEach             Обойти отображение, передавая потребителю каждую пару ключ-значение
        // getOrDefault        Если ключ присутствует в отображении, вернуть его значение, иначе вернуть
        //                     значение по умолчанию
        // merge               Если ключ отсутствует в отображении, вернуть переданное значение, иначе
        //                     вычислить новое
        // putIfAbsent         Если ключ отсутствует в отображении, ассоциировать его с заданным значением
        // remove              Удалить запись, связанную с данным ключом, только если значение в ней совпадает
        //                     с заданным
        // replace             Заменить значение, ассоциированное с ключом
        // replaceAll          Заменить значения во всех записях отображения результатами применения
        //                     заданной функции к текущей записи

        System.out.println(new d_Map_default_interfaces().fib2(20)); // 6765

        // Пример 5.11  Вызов метода countWords
        String passage = "NSA agent walks into a bar. Bartender says, " +
                "’Hey, I have a new joke for you.’ Agent says, ‘heard it’.";
        Map<String, Integer> counts = new d_Map_default_interfaces().countWords(passage, "a", "NSA", "agent", "joke");
        counts.forEach((word, count) -> System.out.println(word + "=" + count));
        // a=2
        // agent=1
        // NSA=1
        // joke=1

        System.out.println();
        Map<String, Integer> stringIntegerMap = fullWordCounts(passage);
        stringIntegerMap.forEach((word, count) -> System.out.println(word + "=" + count));
        // new=1
        // a=2
        // agent=2
        // for=1
        // i=1
        // it=1
        // joke=1
        // walks=1
        // into=1
        // bar=1
        // have=1
        // bartender=1
        // hey=1
        // says=2
        // nsa=1
        // heard=1
        // you=1
    }

    // computeIfAbsent

    // Пример 5.8  Рекурсивное вычисление чисел Фибоначчи
    private long fib(long i) {
        if (i == 0) return 0;
        if (i == 1) return 1;
        return fib(i - 1) + fib(i - 2);
    }

    // хэширование, в функциональном программировании эта техника называется запоминанием (memoization)

    // Пример 5.9  Вычисление чисел Фибоначчи с использованием кэша
    private Map<Long, BigInteger> cache = new HashMap<>();

    private BigInteger fib2(long i) {
        System.out.println("Start0: " + i);
        if (i == 0) return BigInteger.ZERO;
        if (i == 1) return BigInteger.ONE;
        return cache.computeIfAbsent(i,
                n -> {
                    System.out.println("Start: " + i + " " + n);
                    BigInteger b = fib2(n - 2);
                    return b.add(fib2(n - 1));
                }
        );
    }

    // computeIfPresent

    // конкорданса - анализируем текст и подсчитываем количество вхождений каждого слова

    // Пример 5.10  Обновление счетчика встречаемости только для определенных слов
    public Map<String, Integer> countWords(String passage, String... strings) {
        Map<String, Integer> wordCounts = new HashMap<>();

        Arrays.stream(strings).forEach(s -> wordCounts.put(s, 0));

        Arrays.stream(passage.split(" "))
                .forEach(
                        word -> wordCounts.computeIfPresent(word, (key, val) -> val + 1)
                );
        return wordCounts;
    }

    // Прочие методы

    // Пример 5.12  Использование метода merge
    // счетчики вхождения всех слов в данный текст
    static Map<String, Integer> fullWordCounts(String passage) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String testString = passage.toLowerCase().replaceAll("\\W", " ");
        Arrays.stream(testString.split("\\s+"))
                .forEach(word ->
                        wordCounts.merge(word, 1, Integer::sum));
        return wordCounts;
    }

}
