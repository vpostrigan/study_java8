package modern_java_recipes.chapter3_streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
уществуют следующие методы создания потоков:
+ Stream.of(T... values) и Stream.of(T t);
+ Arrays.stream(T[] array) с перегруженными вариантами для int[], double[] и long[];
+ Stream.iterate(T seed, UnaryOperator<T> f);
+ Stream.generate(Supplier<T> s);
+ Collection.stream();
+ range и rangeClosed:
– IntStream.range(int startInclusive, int endExclusive);
– IntStream.rangeClosed(int startInclusive, int endInclusive);
– LongStream.range(long startInclusive, long endExclusive);
– LongStream.rangeClosed(long startInclusive, long endInclusive)
 */
public class CreatingStreams_1 {

    public static void main(String[] args) {
        // Пример 3.2  Создание потока методом Stream.of
        String names = Stream.of("Gomez", "Morticia", "Wednesday", "Pugsley")
                .collect(Collectors.joining(","));
        System.out.println(names); // Gomez,Morticia,Wednesday,Pugsley

        // Пример 3.3  Создание потока методом Arrays.stream
        String[] munsters = {"Herman", "Lily", "Eddie", "Marilyn", "Grandpa"};
        names = Arrays.stream(munsters)
                .collect(Collectors.joining(","));
        System.out.println(names); // Herman,Lily,Eddie,Marilyn,Grandpa

        // //

        // Пример 3.4  Создание потока методом Stream.iterate
        // метод «возвращает бесконечный последовательный упорядоченный поток Stream,
        // порождаемый итеративным применением функции f к начальному элементу seed».
        List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(nums); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1))
                .limit(10)
                .forEach(System.out::println);
        /*
2023-04-27
2023-04-28
2023-04-29
2023-04-30
2023-05-01
2023-05-02
2023-05-03
2023-05-04
2023-05-05
2023-05-06
         */

        // Пример 3.5  Создание потока случайных чисел типа double
        Stream.generate(Math::random)
                .limit(3)
                .forEach(System.out::println);
/*
0.7874955979415602
0.84088666643971
0.9408527130183777
 */

        // Пример 3.6  Создание потока из коллекции
        List<String> bradyBunch = Arrays.asList("Greg", "Marcia", "Peter", "Jan", "Bobby", "Cindy");
        names = bradyBunch.stream()
                .collect(Collectors.joining(","));
        System.out.println(names); // Greg,Marcia,Peter,Jan,Bobby,Cindy

        // Пример 3.7  Методы range и rangeClosed
        List<Integer> ints = IntStream.range(10, 15)
                // Необходимо, чтобы коллектор мог преобразовать примитивы в List<T>
                .boxed()
                .collect(Collectors.toList());
        System.out.println(ints); // [10, 11, 12, 13, 14]

        List<Long> longs = LongStream.rangeClosed(10, 15)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs); // [10, 11, 12, 13, 14, 15]
    }

}
