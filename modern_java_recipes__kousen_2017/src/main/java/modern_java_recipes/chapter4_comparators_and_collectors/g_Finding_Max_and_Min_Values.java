package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Требуется найти минимальное и максимальное значения в потоке.
 */
public class g_Finding_Max_and_Min_Values {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Cersei", 250_000, "Lannister"),
                new Employee("Jamie", 150_000, "Lannister"),
                new Employee("Tyrion", 1_000, "Lannister"),
                new Employee("Tywin", 1_000_000, "Lannister"),
                new Employee("Jon Snow", 75_000, "Stark"),
                new Employee("Robb", 120_000, "Stark"),
                new Employee("Eddard", 125_000, "Stark"),
                new Employee("Sansa", 0, "Stark"),
                new Employee("Arya", 1_000, "Stark"));
        Employee defaultEmployee =
                new Employee("A man (or woman) has no name", 0, "Black and White");

        // найти работника с максимальной зарплатой
        // Пример 4.25  Использование метода BinaryOperator.maxBy
        Optional<Employee> optionalEmp = employees.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(Employee::getSalary)));
        System.out.println("Работник с максимальной зарплатой: " +
                optionalEmp.orElse(defaultEmployee));
        // Работник с максимальной зарплатой: Employee{name='Tywin', salary=1000000, department='Lannister'}

        // Пример 4.26  Использование метода Stream.max
        optionalEmp = employees.stream()
                .max(Comparator.comparingInt(Employee::getSalary));

        // Пример 4.27  Нахождение наибольшей зарплаты
        OptionalInt maxSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .max();
        System.out.println("Наибольшая зарплата равна " + maxSalary);
        // Наибольшая зарплата равна OptionalInt[1000000]

        // Пример 4.28  Использование метода Collectors.maxBy
        optionalEmp = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));

        // создания отображения отделов на списки работников,
        // а затем в каждом отделе ищется работник с наибольшей зарплатой
        // Пример 4.29  Использование метода Collectors.maxBy в роли подчиненного коллектора
        Map<String, Optional<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
        map.forEach((house, emp) ->
                System.out.println(house + ": " + emp.orElse(defaultEmployee)));
    }

    private static class Employee {
        private String name;
        private Integer salary;
        private String department;

        public Employee(String name, Integer salary, String department) {
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public Integer getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", department='" + department + '\'' +
                    '}';
        }
    }

}
