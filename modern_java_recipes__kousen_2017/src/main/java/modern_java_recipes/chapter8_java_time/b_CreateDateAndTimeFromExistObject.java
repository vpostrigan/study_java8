package modern_java_recipes.chapter8_java_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Требуется модифицировать экземпляр одного из классов даты/времени.
 */
public class b_CreateDateAndTimeFromExistObject {

    public static void main(String[] args) throws Exception {
        // методы plus или minus, в противном случае – методом with.

        localDatePlus();
        localTimePlus();
        plus_minus();
        with();
        try {
            withInvalidDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        temporalField();
    }

    //Пример 8.6  Использование методов plus для объектов LocalDate и LocalTime
    private static void localDatePlus() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.of(2017, Month.FEBRUARY, 2);

        LocalDate end = start.plusDays(3);
        System.out.println(end.format(formatter));
        // 2017-02-05

        end = start.plusWeeks(5);
        System.out.println(end.format(formatter));
        // 2017-03-09

        end = start.plusMonths(7);
        System.out.println(end.format(formatter));
        // 2017-09-02

        end = start.plusYears(2);
        System.out.println(end.format(formatter));
        // 2019-02-02
    }

    private static void localTimePlus() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        LocalTime start = LocalTime.of(11, 30, 0, 0);

        LocalTime end = start.plusNanos(1_000_000);
        System.out.println(end.format(formatter));
        // 11:30:00.001

        end = start.plusSeconds(20);
        System.out.println(end.format(formatter));
        // 11:30:20

        end = start.plusMinutes(45);
        System.out.println(end.format(formatter));
        // 12:15:00

        end = start.plusHours(5);
        System.out.println(end.format(formatter));
        // 16:30:00
    }

    // Пример 8.7  Методы plus и minus
    private static void plus_minus() throws Exception {
        Period period = Period.of(2, 3, 4); // 2 years, 3 months, 4 days

        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);

        LocalDateTime end = start.plus(period);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2019-05-06T11:30:00

        end = start.plus(3, ChronoUnit.HALF_DAYS);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2017-02-03T23:30:00

        end = start.minus(period);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2014-10-29T11:30:00

        end = start.minus(2, ChronoUnit.CENTURIES);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 1817-02-02T11:30:00

        end = start.plus(3, ChronoUnit.MILLENNIA);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 5017-02-02T11:30:00
    }

    private static void with() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);

        LocalDateTime end = start.withMinute(45);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2017-02-02T11:45:00

        end = start.withHour(16);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2017-02-02T16:30:00

        end = start.withDayOfMonth(28);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2017-02-28T11:30:00

        end = start.withDayOfYear(300);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2017-10-27T11:30:00

        end = start.withYear(2020);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2020-02-02T11:30:00
    }

    private static void withInvalidDate() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        start.withDayOfMonth(29);
        // Exception in thread "main" java.time.DateTimeException: Invalid date 'February 29' as '2017' is not a leap year
    }

    // Пример 8.9  Изменение месяца, так что получается недопустимая дата
    private static void temporalField() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.JANUARY, 31, 11, 30);
        LocalDateTime end = start.with(ChronoField.MONTH_OF_YEAR, 2);
        System.out.println(end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // 2017-02-28T11:30:00
    }

}
