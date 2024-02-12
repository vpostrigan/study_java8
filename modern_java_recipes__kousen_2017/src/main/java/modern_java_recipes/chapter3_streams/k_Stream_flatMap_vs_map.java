package modern_java_recipes.chapter3_streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * map, если каждый элемент преобразуется в одно значение.
 * flatMap, если каждый элемент преобразуется в несколько значений и получившийся поток нужно «разгладить».
 */
public class k_Stream_flatMap_vs_map {

    public static void main(String[] args) {
        // Пример 3.55  Создание заказчиков и заказов
        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");

        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));

        List<Customer> customers = Arrays.asList(sheridan, ivanova, garibaldi);

        // Пример 3.56  Применение map для отображения Customer на name
        customers.stream()
                .map(Customer::getName)
                .forEach(System.out::println);
        // Sheridan Ivanova Garibaldi

        // Пример 3.57  Применение map для отображения Customer на orders
        customers.stream()
                .map(Customer::getOrders)
                .forEach(System.out::println);
        // [Order{id=1}, Order{id=2}, Order{id=3}]
        // [Order{id=4}, Order{id=5}]
        // []
        customers.stream()
                .map(customer -> customer.getOrders().stream())
                .forEach(System.out::println);
        // java.util.stream.ReferencePipeline$Head@6f496d9f
        // java.util.stream.ReferencePipeline$Head@723279cf
        // java.util.stream.ReferencePipeline$Head@10f87f48

        // Пример 3.58  Применение flatMap к заказам
        customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .forEach(System.out::println);
        // Order{id=1}
        // Order{id=2}
        // Order{id=3}
        // Order{id=4}
        // Order{id=5}
    }

    // Пример 3.54  Отношение один ко многим
    private static class Customer {
        private String name;
        private List<Order> orders = new ArrayList<>();

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public Customer addOrder(Order order) {
            orders.add(order);
            return this;
        }
    }

    private static class Order {
        private int id;

        public Order(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    '}';
        }
    }

}
