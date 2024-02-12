package modern_java_recipes.chapter5_optional_type;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class c_StreamRandomNumbers {

    public static void main(String[] args) {

        // от 0.0 до 1.0
        double random = Math.random();

        // Пример 5.7  Порождение потока случайных чисел
        Random r = new Random();
        r.ints(5) // Пять случайных целых чисел
                .sorted()
                .forEach(System.out::println);
        // -1323381168
        // -68228756
        // 240472750
        // 826130150
        // 1091489502

        r.doubles(5, 0, 0.5) // Пять случайных чисел типа double от 0 (включая) до 0.5 (не включая)
                .sorted()
                .forEach(System.out::println);
        // 0.09264398692101011
        // 0.13449496546092543
        // 0.2619780663249413
        // 0.301619881868358
        // 0.3184756039062501

        List<Long> longs =
                r.longs(5)
                        .boxed() // Обертывание long типом Long, чтобы числа можно было собрать в коллекцию
                        .collect(Collectors.toList());
        System.out.println(longs);
        // [8558831962739672028, 5392117473603186553, -1637682156524982607, 5781382437077106502, 8405374457660224604]

        // Другая форма collect, без вызова boxed
        List<Integer> listOfInts = r
                .ints(5, 10, 20)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println(listOfInts);
        // [16, 17, 11, 10, 16]
    }

}
