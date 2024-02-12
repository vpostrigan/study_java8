package modern_java_recipes.chapter1_the_basics;

public class d_FunctionalInterfaces {

    public static void main(String[] args) {

    }

    // @FunctionalInterface - компилятор проверяет, что интерфейс удовлетворяет требованиям к функциональному интерфейсу
    @FunctionalInterface
    public interface PalindromeChecker {
        // Функциональным интерфейсом в Java 8 называется интерфейс с единственным абстрактным методом.

        boolean isPalidrome(String s);

        // Все методы интерфейса являются открытыми
        // * По крайней мере, так было до выхода версии Java 9, в которой в интерфейсах разрешены также закрытые (private) методы.
    }


    // В функциональных интерфейсах также могут быть методы, объявленные с ключевыми словами default и static.

    // Пример 1.23  MyInterface – функциональный интерфейс, содержащий статический метод и метод по умолчанию
    @FunctionalInterface
    public interface MyInterface {
        int myMethod(); // Единственный абстрактный метод

        // int myOtherMethod(); // Если раскомментировать эту строку, интерфейс перестанет быть функциональным

        default String sayHello() {
            return "Hello, World!";
        }

        static void myStaticMethod() {
            System.out.println("Это статический метод интерфейса");
        }
    }

    // Пример 1.24  После расширения функциональный интерфейс перестает быть функциональным
    //@FunctionalInterface
    public interface MyChildInterface extends MyInterface {
        int anotherMethod(); // Дополнительный абстрактный метод
    }

    // //

    // Comparator имеет @FunctionalInterface, но у него два абстрактных метода
    // В требованиях к функциональным интерфейсам оговорено,
    // что методы Object не учитываются при подсчете абстрактных методов,
    // поэтому Comparator все же считается функциональным интерфейсом.

}
