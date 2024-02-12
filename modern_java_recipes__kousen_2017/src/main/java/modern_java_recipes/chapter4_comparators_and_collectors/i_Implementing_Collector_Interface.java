package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Требуется вручную реализовать интерфейс java.util.stream.Collector,
 * поскольку ни один из фабричных методов класса java.util.stream.Collectors не делает
 * в точности то, что нужно.
 */
public class i_Implementing_Collector_Interface {

    public static void main(String[] args) {


    }

    // Пример 4.34  Использование метода collect для возврата списка
    public List<String> evenLengthStrings(String... strings) {
        return Stream.of(strings)
                .filter(s -> s.length() % 2 == 0)
                .collect(Collectors.toList());
    }

    // Пример 4.36  Использование метода collect для возврата немодифицируемой коллекции SortedSet
    public SortedSet<String> oddLengthStringSet(String... strings) {
        Collector<String, ?, SortedSet<String>> intoSet =
                Collector.of(
                        TreeSet<String>::new, // Supplier, возвращающий новый контейнер TreeSet
                        SortedSet::add, // BiConsumer, добавляющий одну строку в TreeSet
                        (left, right) -> { // BinaryOperator, объединяющий два объекта SortedSet в один
                            left.addAll(right);
                            return left;
                        },
                        Collections::unmodifiableSortedSet // finisher, создающая немодифицируемое множество
                );
        return Stream.of(strings)
                .filter(s -> s.length() % 2 != 0)
                .collect(intoSet);
    }

}
