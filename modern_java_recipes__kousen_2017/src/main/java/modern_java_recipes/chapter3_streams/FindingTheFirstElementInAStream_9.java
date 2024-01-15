package modern_java_recipes.chapter3_streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FindingTheFirstElementInAStream_9 {

    public static void main(String[] args) {
        // Пример 3.46  Нахождение первого четного числа
        Optional<Integer> firstEven = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEven); // Optional[4] (всегда будет 4)

        // Пример 3.47  Применение findFirst к пустому потоку
        Optional<Integer> firstEvenGT10 = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(n -> n > 10)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEvenGT10); // Optional.empty

        // Пример 3.48  Использование firstEven с параллельным потоком
        firstEven = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .parallel()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEven); // Optional[4] (всегда будет 4)


        // [Множества и порядок следования]
        List<String> wordList = Arrays.asList("this", "is", "a", "stream", "of", "strings");
        Set<String> words = new HashSet<>(wordList);
        Set<String> words2 = new HashSet<>(words);
        // Теперь добавим и удалим столько элементов, чтобы вызвать повторное хэширование
        IntStream.rangeClosed(0, 50).forEachOrdered(i ->
                words2.add(String.valueOf(i)));
        words2.retainAll(wordList);
        // Множества равны, но порядок элементов различен
        System.out.println(words.equals(words2)); // true
        System.out.println("До : " + words); // [a, strings, stream, of, this, is]
        System.out.println("После: " + words2); // [this, is, strings, stream, of, a]


        // findAny - В данном случае поведение операции явно недетерминировано
        Optional<Integer> any = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered() // Порядок нас не волнует
                .parallel() // Для распараллеливания используем обычный fork-join pool
                .map(FindingTheFirstElementInAStream_9::delay) // Вводим случайную задержку
                .findAny(); // Возвращаем первый элемент независимо от порядка следования

        // findFirst и findAny являются укорачивающими терминальными операциями.
        // Укорачивающая (short-circuiting) операция может порождать конечный поток, получив на входе бесконечный.
    }

    // Пример 3.49  Применение findAny к параллельному потоку со случайной задержкой
    public static Integer delay(Integer n) {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException ignored) {
        }
        return n;
    }

}
