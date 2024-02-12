package modern_java_recipes.chapter3_streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class l_ConcatenatingStreams {

    public static void main(String[] args) {
        {
            // Пример 3.59  Конкатенация двух потоков
            Stream<String> first = Stream.of("a", "b", "c").parallel();
            Stream<String> second = Stream.of("X", "Y", "Z");
            List<String> strings = Stream.concat(first, second)
                    .collect(Collectors.toList());
            List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z");
            assert stringList.equals(strings);
        }
        {
            // Пример 3.60  Конкатенация нескольких потоков
            Stream<String> first = Stream.of("a", "b", "c").parallel();
            Stream<String> second = Stream.of("X", "Y", "Z");
            Stream<String> third = Stream.of("alpha", "beta", "gamma");
            List<String> strings = Stream.concat(Stream.concat(first, second), third)
                    .collect(Collectors.toList());
            List<String> stringList = Arrays.asList("a", "b", "c",
                    "X", "Y", "Z",
                    "alpha", "beta", "gamma");
            assert stringList.equals(strings);
        }
        {
            // Пример 3.61  Конкатенация с помощью метода reduce
            Stream<String> first = Stream.of("a", "b", "c").parallel();
            Stream<String> second = Stream.of("X", "Y", "Z");
            Stream<String> third = Stream.of("alpha", "beta", "gamma");
            Stream<String> fourth = Stream.empty();

            List<String> strings = Stream.of(first, second, third, fourth)
                    .reduce(Stream.empty(), Stream::concat)
                    .collect(Collectors.toList());
            List<String> stringList = Arrays.asList("a", "b", "c",
                    "X", "Y", "Z", "alpha", "beta", "gamma");
            assert stringList.equals(strings);
        }
        {
            // Пример 3.62  Применение flatMap для конкатенации потоков
            Stream<String> first = Stream.of("a", "b", "c").parallel();
            Stream<String> second = Stream.of("X", "Y", "Z");
            Stream<String> third = Stream.of("alpha", "beta", "gamma");
            Stream<String> fourth = Stream.empty();

            List<String> strings = Stream.of(first, second, third, fourth)
                    .flatMap(Function.identity())
                    .collect(Collectors.toList());
            List<String> stringList = Arrays.asList("a", "b", "c",
                    "X", "Y", "Z", "alpha", "beta", "gamma");
            assert stringList.equals(strings);
        }

        // Пример 3.63  Параллельный или нет?
        // Метод concat создает параллельный поток,
        // если хотя бы один из входных потоков параллелен, но для flatMap это не так
        {
            Stream<String> first = Stream.of("a", "b", "c").parallel();
            Stream<String> second = Stream.of("X", "Y", "Z");
            Stream<String> third = Stream.of("alpha", "beta", "gamma");

            Stream<String> total = Stream.concat(Stream.concat(first, second), third);
            assert total.isParallel();
        }
        {
            Stream<String> first = Stream.of("a", "b", "c").parallel();
            Stream<String> second = Stream.of("X", "Y", "Z");
            Stream<String> third = Stream.of("alpha", "beta", "gamma");
            Stream<String> fourth = Stream.empty();

            Stream<String> total = Stream.of(first, second, third, fourth)
                    .flatMap(Function.identity());
            assert !total.isParallel();
        }

    }

}
