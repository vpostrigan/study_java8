package modern_java_recipes.chapter6_optional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Требуется применить функцию к коллекции объектов Optional, но только к тем, что содержат значение.
 */
public class e_OptionalIteration {

    public static void main(String[] args) {

    }

    // Пример 6.20  Поиск работников по идентификатору
    public List<Employee> findEmployeesByIds(List<Integer> ids) {
        return ids.stream()
                .map(e_OptionalIteration::findEmployeeById)
                .filter(Optional::isPresent) // Удалить пустые Optional
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    // Пример 6.21  Использование метода Optional.map
    public List<Employee> findEmployeesByIds2(List<Integer> ids) {
        return ids.stream()
                .map(e_OptionalIteration::findEmployeeById)
                // Преобразовывает непустой Optional<Employee> в Stream<Employee>
                .flatMap(optional -> optional.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }

    static Optional<Employee> findEmployeeById(Integer id) {
        return Optional.of(null);
    }

    private class Employee {

    }

}
