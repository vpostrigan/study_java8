package modern_java_recipes.chapter1_the_basics;

import java.util.Arrays;
import java.util.List;

public class DefaultMethodsInInterfaces_6 {

    public static void main(String[] args) {
        // Множественное наследование никогда не поддерживалось в Java из-за проблемы ромбовидного наследования.

        // Animal два дочерних класса, Bird и Horse, в каждом из которых переопределен метод speak из класса Animal
        // Pegasus (который наследует и Horse, и Bird)
        // Animal animal = new Pegasus();
        // animal.speak(); // какой взять из Bird или Horse?

        // C++ множественное наследование разрешено, если не компилируется можно использовать виртуальное наследование

        // Для интерфейсов множественное наследование разрешено, но работает это лишь потому,
        // что наследуются только сигнатуры методов.

        // Обычно после добавления нового метода в интерфейс все его существующие реализации перестают компилироваться.
        // Но если новый метод является методом по умолчанию, то все существующие реализации наследуют его и продолжают работать.

        // //

        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9);
        // Использование метода по умолчанию removeIf интерфейса Collection
        boolean removed = nums.removeIf(n -> n <= 0);
        System.out.println("Элементы " + (removed ? "были" : "НЕ были") + " удалены");
        // Использование метода по умолчанию forEach интерфейса Iterator
        nums.forEach(System.out::println);
        // Элементы НЕ были удалены
        //3
        //1
        //4
        //1
        //5
        //9
    }

    // Пример 1.26  Интерфейс Employee с методом по умолчанию
    public interface Employee {
        String getFirst();

        String getLast();

        void convertCaffeineToCodeForMoney();

        default String getName() { // Метод по умолчанию, имеющий реализацию
            return String.format("%s %s", getFirst(), getLast());

            // В определении метода getName имеется ключевое слово default,
            // а реализован он в терминах других, абстрактных методов, getFirst и getLast.
        }
    }

}

// - статический метод должен иметь реализацию;
// - статический метод нельзя переопределять;
// - при вызове статического метода указывается имя интерфейса;
// - чтобы воспользоваться статическими методами интерфейса, реализовывать его необязательно.
