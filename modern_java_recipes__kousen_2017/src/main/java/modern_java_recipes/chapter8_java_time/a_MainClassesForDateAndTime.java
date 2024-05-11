package modern_java_recipes.chapter8_java_time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Желательно использовать новые классы для работы с датами и временем из пакета java.time.
 */
public class a_MainClassesForDateAndTime {

    public static void main(String[] args) {
        // Использовать фабричные методы классов Instant, Duration, Period, LocalDate, LocalTime, LocalDateTime, ZonedDateTime и других.
        // Все новые классы порождают неизменяемые экземпляры, поэтому они потокобезопасны.
        // Ни в одном из них нет открытых конструкторов, так что экземпляры создаются фабричными методами.

        // Пример 8.1  Фабричный метод now
        System.out.println("Instant.now(): " + Instant.now());
        System.out.println("LocalDate.now(): " + LocalDate.now());
        System.out.println("LocalTime.now(): " + LocalTime.now());
        System.out.println("LocalDateTime.now(): " + LocalDateTime.now());
        System.out.println("ZonedDateTime.now(): " + ZonedDateTime.now());
        // Instant.now(): 2024-05-16T06:19:42.341Z
        // LocalDate.now(): 2024-05-16
        // LocalTime.now(): 09:19:42.429
        // LocalDateTime.now(): 2024-05-16T09:19:42.429
        // ZonedDateTime.now(): 2024-05-16T09:19:42.429+03:00[Europe/Helsinki]

        // Пример 8.3  Метод of классов для работы с датой и временем
        System.out.println("Первая высадка на Луну:");
        LocalDate moonLandingDate = LocalDate.of(1969, Month.JULY, 20);
        LocalTime moonLandingTime = LocalTime.of(20, 18);
        System.out.println("Дата: " + moonLandingDate);
        System.out.println("Время: " + moonLandingTime);
        System.out.println("Нил Армстронг выходит на поверхность: ");
        LocalTime walkTime = LocalTime.of(20, 2, 56, 150_000_000);
        LocalDateTime walk = LocalDateTime.of(moonLandingDate, walkTime);
        System.out.println(walk);
        // Первая высадка на Луну:
        // Дата: 1969-07-20
        // Время: 20:18
        // Нил Армстронг выходит на поверхность:
        // 1969-07-20T20:02:56.150

        // Таблица 8.1. Префиксы методов из Date-Time API
        // Метод       | Тип         | Назначение
        // --------------------------------------
        // of          | Статический | фабричный Создает экземпляр
        // from        | Статический | фабричный Преобразует входные параметры в целевой класс
        // parse       | Статический | фабричный Разбирает входную строку
        // format      | Экземпляра  | Порождает форматированный вывод
        // get         | Экземпляра  | Возвращает часть объекта
        // is          | Экземпляра  | Запрашивает состояние объекта
        // with        | Экземпляра  | Создает новый объект, изменяя один элемент существующего
        // plus, minus | Экземпляра  | Создает новый объект, прибавляя или вычитая что-то из существующего
        // to          | Экземпляра  | Преобразует объект в другой тип
        // at          | Экземпляра  | Объединяет этот объект с другим

        // Пример 8.4  Добавление часового пояса к LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 10);
        System.out.println(dateTime); // 2017-07-04T13:20:10
        ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println(nyc);      // 2017-07-04T13:20:10-04:00[America/New_York]
        ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println(london);   // 2017-07-04T18:20:10+01:00[Europe/London]

        // Пример 8.5  Некоторые методы перечисления Month
        System.out.println("Дней в високосном феврале: " + Month.FEBRUARY.length(true));
        System.out.println("Порядковый номер первого дня августа в году (високосный год): " +
                Month.AUGUST.firstDayOfYear(true));
        System.out.println("Month.of(1): " + Month.of(1));
        System.out.println("Прибавление двух месяцев: " + Month.JANUARY.plus(2));
        System.out.println("Вычитание месяца: " + Month.MARCH.minus(1));
        // Дней в високосном феврале: 29
        // Порядковый номер первого дня августа в году (високосный год): 214
        // Month.of(1): JANUARY
        // Прибавление двух месяцев: MARCH
        // Вычитание месяца: FEBRUARY


    }

}
