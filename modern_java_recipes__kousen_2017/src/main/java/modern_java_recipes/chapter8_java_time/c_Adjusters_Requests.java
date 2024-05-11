package modern_java_recipes.chapter8_java_time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.util.stream.IntStream;

/**
 * Имеется временнóе значение, и требуется подкорректировать его,
 * руководствуясь собственной логикой, или получить о нем информацию.
 */
public class c_Adjusters_Requests {

    public static void main(String[] args) throws Exception {

        // [Работа с TemporalAdjuster]
        adjusters();

        payDay();

        // [Работа с TemporalQuery]
        queries();
    }

    // Пример 8.10  Использование статических методов класса TemporalAdjusters
    static void adjusters() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);

        System.out.println(start.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println(start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
        System.out.println(start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)));
        // 2017-03-01T11:30
        // 2017-02-09T11:30
        // 2017-02-02T11:30
    }

    // Пример 8.12  Тестирование корректора для июля 2017-го
    static void payDay() throws Exception {
        TemporalAdjuster adjuster = new PaydayAdjuster();

        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date -> System.out.println(date.with(adjuster).getDayOfMonth())); // 14
        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date -> System.out.println(date.with(adjuster).getDayOfMonth())); // 31

        // или
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date -> System.out.println(date.with(PaydayAdjuster2::adjustInto).getDayOfMonth()));
        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date -> System.out.println(date.with(PaydayAdjuster2::adjustInto).getDayOfMonth()));
    }

    static class PaydayAdjuster implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal input) {
            LocalDate date = LocalDate.from(input);
            int day;
            if (date.getDayOfMonth() < 15) {
                day = 15;
            } else {
                day = date.with(TemporalAdjusters.lastDayOfMonth())
                        .getDayOfMonth();
            }
            date = date.withDayOfMonth(day);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
            }
            return input.with(date);
        }
    }

    static class PaydayAdjuster2 {
        static Temporal adjustInto(Temporal input) {
            LocalDate date = LocalDate.from(input);
            int day;
            if (date.getDayOfMonth() < 15) {
                day = 15;
            } else {
                day = date.with(TemporalAdjusters.lastDayOfMonth())
                        .getDayOfMonth();
            }
            date = date.withDayOfMonth(day);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
            }
            return input.with(date);
        }
    }

    // //

    // Пример 8.15  Использование методов из класса TemporalQueries
    static void queries() throws Exception {
        System.out.println(LocalDate.now().query(TemporalQueries.precision())); // Days
        System.out.println(LocalTime.now().query(TemporalQueries.precision())); // Nanos
        System.out.println(ZonedDateTime.now().query(TemporalQueries.zone())); // Europe/Helsinki
        System.out.println(ZonedDateTime.now().query(TemporalQueries.zoneId())); // Europe/Helsinki
    }

    // Пример 8.16  Метод, вычисляющий, сколько дней осталось до пиратского праздника
    private long daysUntilPirateDay(TemporalAccessor temporal) {
        int day = temporal.get(ChronoField.DAY_OF_MONTH);
        int month = temporal.get(ChronoField.MONTH_OF_YEAR);
        int year = temporal.get(ChronoField.YEAR);

        LocalDate date = LocalDate.of(year, month, day);
        LocalDate tlapd = LocalDate.of(year, Month.SEPTEMBER, 19);
        if (date.isAfter(tlapd)) {
            tlapd = tlapd.plusYears(1);
        }
        return ChronoUnit.DAYS.between(date, tlapd);
    }

    // Пример 8.17  Выполнение запроса TemporalQuery с помощью ссылки на метод
    public void pirateDay() throws Exception {
        IntStream.range(10, 19)
                .mapToObj(n -> LocalDate.of(2017, Month.SEPTEMBER, n))
                .forEach(date -> System.out.println(date.query(this::daysUntilPirateDay)));
        IntStream.rangeClosed(20, 30)
                .mapToObj(n -> LocalDate.of(2017, Month.SEPTEMBER, n))
                .forEach(date -> {
                    Long days = date.query(this::daysUntilPirateDay);
                    System.out.println(days);
                });
    }

}
