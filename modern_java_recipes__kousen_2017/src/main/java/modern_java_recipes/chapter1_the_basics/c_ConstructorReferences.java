package modern_java_recipes.chapter1_the_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 1.3. Ссылки на Конструкторы
 */
public class c_ConstructorReferences {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace", "Karen Spärck Jones");

        // Пример 1.15  Преобразование строк в экземпляры класса Person
        List<Person> list = names.stream()
                .map(n -> new Person(n))
                .collect(Collectors.toList());
        // или
        List<Person> list2 = names.stream()
                // Person(String name)
                .map(Person::new)
                .collect(Collectors.toList());

        // Пример 1.13  Преобразование списка людей в список имен
        list.stream()
                .map(person -> person.getName())
                .collect(Collectors.toList());
        // или
        list.stream()
                .map(Person::getName)
                .collect(Collectors.toList());


        // Пример 1.17  Преобразование списка в поток и обратно
        Person before = new Person("Grace Hopper");
        List<Person> people = Stream.of(before).collect(Collectors.toList());
        Person after = people.get(0);
        assert before == after;
        before.setName("Grace Murray Hopper");
        assert "Grace Murray Hopper".equals(after.getName());
        // Имя изменилось и в ссылке after
        // Копирующий конструктор позволяет разорвать эту связь.

        people = Stream.of(before)
                // new Person(Person p)
                .map(Person::new)
                .collect(Collectors.toList());
        after = people.get(0);
        assert before != after;
        assert before.equals(after);
        before.setName("Rear Admiral Dr. Grace Murray Hopper");
        assert !before.equals(after);


        // Пример 1.20.  Использование конструктора с переменным числом аргументов
        names.stream()
                .map(name -> name.split(" "))
                // Person(String... names)
                .map(Person::new)
                .collect(Collectors.toList());


        // Пример 1.21  Создание массива объектов Person
        Person[] array = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);
        System.out.println(Arrays.asList(array));
        // [Person{name='Grace Hopper'}, Person{name='Barbara Liskov'}, Person{name='Ada Lovelace'}, Person{name='Karen Spärck Jones'}]
    }

    public static class Person {
        private String name;

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }

        public Person(Person p) {
            this.name = p.name;
        }

        public Person(String... names) {
            this.name = Arrays.stream(names)
                    .collect(Collectors.joining(" "));
        }

        // методы чтения и установки ...
        // методы equals, hashCode и toString methods ...
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
