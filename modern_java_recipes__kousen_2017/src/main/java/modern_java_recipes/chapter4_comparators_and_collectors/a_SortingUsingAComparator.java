package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class a_SortingUsingAComparator {

    public static void main(String[] args) {
        // Пример 4.1  Сортировка строк в лексикографическом порядке
        List<String> sampleStrings =
                Arrays.asList("this", "is", "a", "list", "of", "strings");

        // defaultSort
        Collections.sort(sampleStrings);

        // defaultSortUsingStreams
        sampleStrings.stream()
                .sorted()
                .collect(toList());


        // Пример 4.2  Сортировка строк по длине
        // lengthSortUsingSorted
        sampleStrings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(toList());

        // lengthSortUsingComparator
        sampleStrings.stream()
                .sorted(comparingInt(String::length))
                .collect(toList());

        // Пример 4.3  Сортировка сначала по длине, затем лексикографически
        // lengthSortThenAlphaSort
        sampleStrings.stream()
                .sorted(Comparator.comparing(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(toList());

        // Пример 4.5  Сортировка гольфистов
        List<Golfer> golfers = Arrays.asList(
                new Golfer("Джек", "Никлаус", 68),
                new Golfer("Тайгер", "Вудс", 70),
                new Golfer("Том", "Уотсон", 70),
                new Golfer("Тай", "Уэбб", 68),
                new Golfer("Бубба", "Уотсон", 70)
        );
        // sortByScoreThenLastThenFirst
        golfers.stream()
                .sorted(comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .forEach(System.out::println);
        // Golfer{first='Джек', last='Никлаус', score=68}
        // Golfer{first='Тай', last='Уэбб', score=68}
        // Golfer{first='Тайгер', last='Вудс', score=70}
        // Golfer{first='Бубба', last='Уотсон', score=70}
        // Golfer{first='Том', last='Уотсон', score=70}


    }

    private static class Golfer {
        private String first;
        private String last;
        private int score;
        // ... прочие методы ...

        public Golfer(String first, String last, int score) {
            this.first = first;
            this.last = last;
            this.score = score;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Golfer{" +
                    "first='" + first + '\'' +
                    ", last='" + last + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

}
