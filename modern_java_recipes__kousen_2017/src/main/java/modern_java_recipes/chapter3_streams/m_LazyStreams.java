package modern_java_recipes.chapter3_streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Требуется обработать минимальное число элементов потока, необходимое для решения задачи.
 */
public class m_LazyStreams {

    public static void main(String[] args) {
        // Пример 3.65  Первое число от 200 до 400, делящееся на 3
        OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .findFirst();
        System.out.println(firstEvenDoubleDivBy3);
        // OptionalInt[204]

        // видно каждый элемент, проходящий по конвейеру:
        firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(m_LazyStreams::multByTwo)
                .filter(m_LazyStreams::divByThree)
                .findFirst();
        // В multByTwo с аргументом 100
        // В divByThree с аргументом 200
        // В multByTwo с аргументом 101
        // В divByThree с аргументом 202
        // В multByTwo с аргументом 102
        // В divByThree с аргументом 204
    }

    public static int multByTwo(int n) {
        System.out.printf("В multByTwo с аргументом %d%n", n);
        return n * 2;
    }

    public static boolean divByThree(int n) {
        System.out.printf("В divByThree с аргументом %d%n", n);
        return n % 3 == 0;
    }

}
