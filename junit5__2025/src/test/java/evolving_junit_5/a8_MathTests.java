package evolving_junit_5;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class a8_MathTests {

    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource(textBlock =
            "4.0, 2.0\n" +
                    "9.0, 3.0\n" +
                    "2.0, 1.41421\n")
    void sqrt(double a, double b) {
        assertEquals(b, Math.sqrt(a), 0.00001);
    }

    @RepeatedTest(100)
    void flakyTest() {
        assertEquals(0.0, Math.random(), 0.9);
    }

    @TestFactory
    Stream<DynamicTest> evenNumbersAreDivisibleByTwo() {
        return IntStream.iterate(0, n -> n + 2)
                .limit(1_000)
                .mapToObj(n -> dynamicTest(n + " is divisible by 2", () -> {
                    assertEquals(0, n % 2);
                }));
    }

}
