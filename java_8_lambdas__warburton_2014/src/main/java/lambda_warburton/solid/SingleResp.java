package lambda_warburton.solid;

import java.util.stream.IntStream;

public class SingleResp {

    public static void main(String[] args) {
        int upTo = 10000;
        long c = IntStream.range(1, upTo).filter(SingleResp::isPrime).count();
        System.out.println(c);
    }

    private static boolean isPrime(int n) {
        return IntStream.range(2, n).allMatch(n0 -> (n % n0) != 0);
    }

}
