package modern_java_recipes.chapter3_streams;

import java.text.NumberFormat;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class SummaryStatistics_8 {

    public static void main(String[] args) {
        // Пример 3.42  Сводные статистики
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();
        System.out.println(stats);
        System.out.println("count: " + stats.getCount());
        System.out.println("min : " + stats.getMin());
        System.out.println("max : " + stats.getMax());
        System.out.println("sum : " + stats.getSum());
        System.out.println("ave : " + stats.getAverage());
        /*
DoubleSummaryStatistics{count=1000000, sum=499368.737981, min=0.000000, average=0.499369, max=0.999999}
count: 1000000
min : 2.1926407189898356E-7
max : 0.9999990507212801
sum : 499368.7379808824
ave : 0.4993687379808824
         */

        // //

        // Пример 3.44  Метод collect с поставщиком, аккумулятором и комбинатором
        DoubleSummaryStatistics teamStats = Stream.of(new Team())
                .mapToDouble(Team::getSalary)
                .collect(DoubleSummaryStatistics::new,
                        DoubleSummaryStatistics::accept,
                        DoubleSummaryStatistics::combine);

        // Пример 3.45  Метод collect с использованием summarizingDouble
        teamStats = Stream.of(new Team())
                .collect(Collectors.summarizingDouble(Team::getSalary));
    }

    // Пример 3.43  Класс Team с полями id, name и salary
    private static class Team {
        private static final NumberFormat nf = NumberFormat.getCurrencyInstance();
        private int id;
        private String name;

        public double getSalary() {
            return salary;
        }

        private double salary;

        // ... конструкторы, методы чтения и установки ...
        @Override
        public String toString() {
            return "Team{" +
                    "id=" + id +
                    ", name=’" + name + '\'' +
                    ", salary=" + nf.format(salary) +
                    '}';
        }
    }

}
