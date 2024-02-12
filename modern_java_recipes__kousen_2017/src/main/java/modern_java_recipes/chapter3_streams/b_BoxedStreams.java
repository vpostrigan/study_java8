package modern_java_recipes.chapter3_streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Обернутые потоки (создать коллекцию из потока значений примитивных типов)
 */
public class b_BoxedStreams {

    public static void main(String[] args) {
        // Пример 3.8  Преобразование потока строк в список
        List<String> strings = Stream.of("this", "is", "a", "list", "of", "strings")
                .collect(Collectors.toList());

        // Пример 3.9  Преобразование потока int в список Integer (НЕ КОМПИЛИРУЕТСЯ)
        // IntStream.of(1, 2, 3)
        //        .collect(Collectors.toList());  // не компилируется

        // //

        // 1) воспользоваться методом boxed интерфейса Stream, чтобы преобразовать IntStream в Stream<Integer>
        // Пример 3.10  Использование метода boxed
        List<Integer> ints = IntStream.of(3, 1, 4, 1, 5, 9)
                .boxed()
                .collect(Collectors.toList());

        // 2) воспользоваться методом mapToObj для преобразования каждого значения примитивного типа в экземпляр класса-обертки
        // Пример 3.11  Использование метода mapToObj
        List<Integer> ints2 = IntStream.of(3, 1, 4, 1, 5, 9)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        // 3) Пример 3.12  Использование варианта collect с тремя аргументами
        List<Integer> ints3 = IntStream.of(3, 1, 4, 1, 5, 9)
                .collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);

        // //

        // Пример 3.13  Преобразование IntStream в массив
        int[] intArray1 = IntStream.of(3, 1, 4, 1, 5, 9).toArray();
        // или
        // int[] intArray2 = IntStream.of(3, 1, 4, 1, 5, 9).toArray(int[]::new);

    }

}
