package modern_java_recipes.chapter1_the_basics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * использовать ссылку на метод как лямбда-выражение
 */
public class MethodReferences_2 {

    public static void main(String[] args) {
        // Example 1-8. Using a method reference to access println
        // Пример 1.8  Использование ссылки на метод для доступа к println
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(x -> System.out.println("x")); // С помощью лямбда-выражения

        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(System.out::println); // С помощью ссылки на метод

        Consumer<Integer> printer = System.out::println;
        Stream.of(3, 1, 4, 1, 5, 9)
                .forEach(printer); //  Присваивание ссылки на метод переменной типа функционального интерфейса


        // Пример 1.9  Ссылка на статический метод
        // Example 1-9. Using a method reference to a static method
        Stream.generate(Math::random) // Статический метод
                .limit(10)
                .forEach(System.out::println);
        // 0.5539407609000148
        // 0.36070177626577693
        // 0.893325991607708
        // ...


        // There are three forms of the method reference syntax, and one is a bit misleading:
        // 1) object::instanceMethod
        // Refer to an instance method using a reference to the supplied object, as in System.out::println
        // 2) Class::staticMethod
        // Refer to static method, as in Math::max
        // 3) Class::instanceMethod
        // Invoke the instance method on a reference to an object supplied by the context, as in String::length

        // эквивалентно System.out::println
        // x -> System.out.println(x)

        // эквивалентно Math::max
        // (x,y) -> Math.max(x,y)

        // эквивалентно String::length
        // x -> x.length()


        // Example 1-10. Invoking a multiple-argument instance method from a class reference
        // Пример 1.10  Вызов метода экземпляра с несколькими аргументами через имя класса
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.compareTo(s2)) // Ссылка на метод
                .collect(Collectors.toList());

        sorted = strings.stream()
                .sorted(String::compareTo) // эквивалентное лямбда-выражение
                .collect(Collectors.toList());


        // Example 1-11. Invoking the length method on String using a method reference
        // Пример 1.11  Вызов метода length объекта типа String с помощью ссылки на метод
        Stream.of("this", "is", "a", "stream", "of", "strings")
                .map(String::length)// Метод экземпляра через метод класса
                .forEach(System.out::println); // Метод экземпляра через ссылку на объект
        // Example 1-12. Lambda expression equivalents for method references
        // Пример 1.12  Лямбда-выражения, эквивалентные ссылкам на методы
        Stream.of("this", "is", "a", "stream", "of", "strings")
                .map(s -> s.length())
                .forEach(x -> System.out.println(x));
    }

}
