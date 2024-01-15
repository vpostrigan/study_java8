package modern_java_recipes.chapter3_streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Using_anyMatch_allMatch_noneMatch_10 {

    public static void main(String[] args) {
        Primes calculator = new Primes();

        assert true ==
                IntStream.of(2, 3, 5, 7, 11, 13, 17, 19)
                        .allMatch(calculator::isPrime);

        assert false ==
                Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20)
                        .anyMatch(calculator::isPrime);


        // «если поток пустой, то возвращается true и предикат не вычисляется»
        assert true == Stream.empty().allMatch(e -> false);
        assert true == Stream.empty().noneMatch(e -> true);
        // anyMatch для пустого потока возвращает false
        assert false == Stream.empty().anyMatch(e -> true);
    }

    private static class Primes {
        // Пример 3.51  Проверка простоты числа
        public boolean isPrime(int num) {
            int limit = (int) (Math.sqrt(num) + 1);
            return num == 2 || num > 1 &&
                    IntStream.range(2, limit)
                            .noneMatch(divisor -> num % divisor == 0);
        }
    }

}
