package lambda_warburton.solid;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntStream_Test {

    public static void main(String[] args) {
        test1();
        test2();
        test3();

        // int upTo = 10000;
        // long c = IntStream.range(1, upTo).filter(SingleResp::isPrime).count();
        // System.out.println(c);
    }

    private static void test1() {
        int[] array = IntStream.range(1, 5).toArray();
        System.out.println("test1: " + Arrays.toString(array));
    }

    private static void test2() {
        int count = IntStream.of(1, 2, 3, 4)
                .filter(e -> e > 2)
                .peek(e -> System.out.println("Filtered value0: " + e))
                .map(e -> e * e)
                .peek(e -> System.out.println("Filtered value1: " + e))
                .sum();
        System.out.println("test2: " + count);
    }

    private static void test3() {
        int c = IntStream.range(1, 5).reduce(0, (all, i) -> all + i);
        System.out.println("test3: " + c);
    }

}
