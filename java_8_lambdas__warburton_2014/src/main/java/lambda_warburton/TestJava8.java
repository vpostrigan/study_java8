package lambda_warburton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collector.Characteristics;

public class TestJava8 {

    public static void main(String[] args) throws Exception {

        {
            List<String> l = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
            String merged = l.stream()
                    .peek(s -> System.out.println(s))
                    .reduce((a, b) -> a + " " + b)
                    .get();
            System.out.println(merged);
        }

        Set<Characteristics> s = Collectors.groupingByConcurrent(String::length).characteristics();

        test1();

        List<String> roster = new ArrayList<>();
        Integer totalAgeReduce = roster.stream().map(String::length).reduce(0, (a, b) -> a + b);
    }

    /*public List<String> ndHeadings(Reader input) {
        return withLinesOf(input,
                lines -> lines.filter(line -> line.endsWith(":"))
                        .map(line -> line.substring(0, line.length() - 1))
                        .collect(toList()),
                HeadingLookupException::new);
    }*/

    private <R> R withLinesOf(Reader input, Function<Stream<String>, R> handler,
            Function<IOException, RuntimeException> error) {
        try (BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        } catch (IOException e) {
            throw error.apply(e);
        }
    }

    private static void test1() {
        simpleMovingAverage(new double[] { 0, 1, 2, 3, 4, 3.5 }, 3);

        parallelDiceRolls();

        Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr", "Pete Best",
                "Stuart Sutcliffe");
        Optional<String> m = names.peek(name -> System.out.println(name))
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));
        
        Collectors.maxBy(Comparator.comparing(String::length)).characteristics();
        
        System.out.println(m);

        // //

        names = Stream.of("John", "Paul", "George", "John", "Paul", "John");
        Map<String, Long> map = names.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        System.out.println(map);
    }

    static Map<Integer, Double> parallelDiceRolls() {
        int N = 100000;
        double fraction = 1.0 / N;
        Map<Integer, Double> m = IntStream.range(0, N).parallel().mapToObj((pos) -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int rstThrow = random.nextInt(1, 7);
            int secondThrow = random.nextInt(1, 7);
            return rstThrow + secondThrow;
        }).collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(n -> fraction)));

        m.forEach((i, d) -> {
            System.out.println(i + " " + d);
        });

        new ManualDiceRolls();

        return m;
    }

    public static class ManualDiceRolls {

        public ManualDiceRolls() {
            final int N = 100000000;
            double fraction = 1.0 / N;

            int numberOfThreads = Runtime.getRuntime().availableProcessors();
            int workPerThread = N / numberOfThreads;
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

            Map<Integer, Double> results = new ConcurrentHashMap<>();

            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < numberOfThreads; i++) {
                futures.add(executor.submit(() -> {
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    for (int j = 0; j < workPerThread; j++) {
                        int entry = random.nextInt(1, 7) + random.nextInt(1, 7);
                        results.compute(entry, (key, previous) -> previous == null ? fraction : previous + fraction);
                    }
                }));
            }
            futures.forEach((future) -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            executor.shutdown();

            results.entrySet().forEach(System.out::println);
        }
    }

    private int addIntegers(List<Integer> values) {
        return values.parallelStream().mapToInt(i -> i).sum();
    }

    public static double[] parallelInitialize(int size) {
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }

    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length).mapToDouble(i -> {
            double prefix = i == start ? 0 : sums[i - n];
            return (sums[i] - prefix) / n;
        }).toArray();
    }

}
