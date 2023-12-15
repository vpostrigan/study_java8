package modern_java_recipes.chapter3_streams;

import java.util.stream.IntStream;

/*
Требуется видеть отдельные элементы потока по мере их обработки.
 */
public class DebuggingStreamsWithPeek_5 {

    public static void main(String[] args) {
        // Для проверки простой тест:
        // sumDoublesDivisibleBy3
        new DebuggingStreamsWithPeek_5().sumDoublesDivisibleBy31(100, 120); // 1554

        new DebuggingStreamsWithPeek_5().sumDoublesDivisibleBy32(100, 120); // 1554

        new DebuggingStreamsWithPeek_5().sumDoublesDivisibleBy33(100, 120); // 1554
    }

    // Пример 3.32  Удвоение, фильтрация и суммирование целых чисел
    public int sumDoublesDivisibleBy31(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();
    }

    // Пример 3.33  Добавление тождественного отображения для печати
    public int sumDoublesDivisibleBy32(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .map(n -> {
                    System.out.println(n);
                    return n;
                })
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .sum();
    }

    // Пример 3.34  Несколько методов peek
    public int sumDoublesDivisibleBy33(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .peek(n -> System.out.printf("original: %d%n", n))
                .map(n -> n * 2)
                .peek(n -> System.out.printf("doubled : %d%n", n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.printf("filtered: %d%n", n))
                .sum();
    }

}
/*
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
117
118
119
120
original: 100
doubled : 200
original: 101
doubled : 202
original: 102
doubled : 204
filtered: 204
original: 103
doubled : 206
original: 104
doubled : 208
original: 105
doubled : 210
filtered: 210
original: 106
doubled : 212
original: 107
doubled : 214
original: 108
doubled : 216
filtered: 216
original: 109
doubled : 218
original: 110
doubled : 220
original: 111
doubled : 222
filtered: 222
original: 112
doubled : 224
original: 113
doubled : 226
original: 114
doubled : 228
filtered: 228
original: 115
doubled : 230
original: 116
doubled : 232
original: 117
doubled : 234
filtered: 234
original: 118
doubled : 236
original: 119
doubled : 238
original: 120
doubled : 240
filtered: 240
 */