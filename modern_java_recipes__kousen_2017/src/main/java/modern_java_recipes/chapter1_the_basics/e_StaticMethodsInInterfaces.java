package modern_java_recipes.chapter1_the_basics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 1.6. Статические методы в интерфейсах
 */
public class e_StaticMethodsInInterfaces {

    public static void main(String[] args) {
        // До Java 8 статические члены в интерфейсах вообще были запрещены.
        // Это привело к созданию служебных классов – класс java.util.Collections
        // Другой пример – класс java.nio.file.Paths из пакета NIO

        // эти методы нельзя переопределить обращаться к методу, указывая имя интерфейса.
        // Классы не обязаны реализовывать интерфейс, чтобы воспользоваться его статическими методами.


        // Примера статического метода интерфейса:
        // метод comparing интерфейса java.util.Comparator,
        // а также его варианты для примитивных типов: comparingInt, comparingLong и comparingDouble.
        // В интерфейсе Comparator есть также статические методы naturalOrder и reverseOrder.
        List<String> bonds = Arrays.asList("Connery", "Lazenby", "Moore",
                "Dalton", "Brosnan", "Craig");

        List<String> sorted = bonds.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(sorted);
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]

        sorted = bonds.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(sorted);
        // [Moore, Lazenby, Dalton, Craig, Connery, Brosnan]

        sorted = bonds.stream()
                .sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList());
        System.out.println(sorted);
        // [Brosnan, Connery, Craig, Dalton, Lazenby, Moore]

        sorted = bonds.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
        System.out.println(sorted);
        // [Moore, Craig, Dalton, Connery, Lazenby, Brosnan]

        sorted = bonds.stream()
                .sorted(Comparator.comparing(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        System.out.println(sorted);
        // [Craig, Moore, Dalton, Brosnan, Connery, Lazenby]


        // Запомните следующие положения:
        // - статический метод должен иметь реализацию;
        // - статический метод нельзя переопределять;
        // - при вызове статического метода указывается имя интерфейса;
        // - чтобы воспользоваться статическими методами интерфейса, реализовывать его необязательно.
    }

    interface App1 {
        static void myMethod() {
        }
    }

    class App12 implements App1 {
        //@Override
        void myMethod() {
        }
    }

}
