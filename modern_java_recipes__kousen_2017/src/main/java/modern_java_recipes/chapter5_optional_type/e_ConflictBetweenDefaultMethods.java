package modern_java_recipes.chapter5_optional_type;

/**
 * Имеется класс, реализующий два интерфейса, в которых встречаются методы
 * по умолчанию с одинаковой сигнатурой, но разными реализациями.
 */
public class e_ConflictBetweenDefaultMethods {

    public static void main(String[] args) {
        // три возможности:
        //  в случае конфликта между методом класса и методом по умолчанию интерфейса всегда побеждает класс;
        //  в случае конфликта между двумя интерфейсами, один из которых расширяет другой,
        //        побеждает расширяющий интерфейс, как и в случае классов;
        //  если между интерфейсами нет отношения наследования, то класс не откомпилируется.
        //        (реализовать метод в самом классе, и все заработает)

    }

    // Пример 5.13  Интерфейс Company с методом по умолчанию
    public interface Company {
        default String getName() {
            return "Initech";
        }
        // прочие методы
    }

    // Пример 5.14  Интерфейс Employee с методом по умолчанию
    public interface Employee {
        String getFirst();

        String getLast();

        void convertCaffeineToCodeForMoney();

        default String getName() {
            return String.format("%s %s", getFirst(), getLast());
        }
    }

    // Пример 5.15  Первая попытка написать класс CompanyEmployee (НЕ КОМПИЛИРУЕТСЯ)
    /*public class CompanyEmployee implements Company, Employee {
        private String first;
        private String last;

        @Override
        public void convertCaffeineToCodeForMoney() {
            System.out.println("Coding...");
        }

        @Override
        public String getFirst() {
            return first;
        }

        @Override
        public String getLast() {
            return last;
        }
    }
    */

    // Пример 5.16  Исправленная версия класса CompanyEmployee
    public class CompanyEmployee implements Company, Employee {
        @Override
        public String getFirst() {
            return null;
        }

        @Override
        public String getLast() {
            return null;
        }

        @Override
        public void convertCaffeineToCodeForMoney() {

        }

        @Override
        public String getName() {
            return String.format("%s работает в компании %s",
                    Employee.super.getName(), // Получить доступ к реализациям по умолчанию с помощью super
                    Company.super.getName());
        }
    }

}
