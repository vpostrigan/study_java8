package modern_java_recipes.chapter6_optional;

import java.util.Optional;

/**
 * Требуется избежать обертывания Optional другим Optional
 */
public class d_OptionalFlatMapAndMap {

    public static void main(String[] args) {

        // Пример 6.14  Возврат Optional
        c_OptionalInGetAndSet.Manager mrSlate = new c_OptionalInGetAndSet.Manager("Mr. Slate");

        c_OptionalInGetAndSet.Department d = new c_OptionalInGetAndSet.Department();
        d.setBoss(mrSlate);
        System.out.println("Начальник: " + d.getBoss());
        // Начальник: Optional[Manager{name='Mr. Slate'}]

        c_OptionalInGetAndSet.Department d1 = new c_OptionalInGetAndSet.Department();
        System.out.println("Начальник: " + d1.getBoss());
        // Начальник: Optional.empty


        // Пример 6.15  Извлечь имя начальника, обернутого объектом Optional
        System.out.println("Имя: " + d.getBoss().orElse(new c_OptionalInGetAndSet.Manager("Unknown")).getName()); // Имя: Mr. Slate
        System.out.println("Имя: " + d1.getBoss().orElse(new c_OptionalInGetAndSet.Manager("Unknown")).getName()); // Имя: Unknown
        System.out.println("Имя: " + d.getBoss().map(c_OptionalInGetAndSet.Manager::getName)); // Имя: Optional[Mr. Slate]
        System.out.println("Имя: " + d1.getBoss().map(c_OptionalInGetAndSet.Manager::getName)); // Имя: Optional.empty


        // Пример 6.17  Optional, обернутый другим Optional
        c_OptionalInGetAndSet.Company co = new c_OptionalInGetAndSet.Company();
        co.setDepartment(d);
        System.out.println("Отдел компании: " + co.getDepartment());
        // Отдел компании: Optional[Department{boss=Manager{name='Mr. Slate'}}]
        System.out.println("Начальник отдела компании: " +
                co.getDepartment()
                        .map(c_OptionalInGetAndSet.Department::getBoss));
        // Начальник отдела компании: Optional[Optional[Manager{name='Mr. Slate'}]]

        // Пример 6.18  Использование метода flatMap для компании
        System.out.println(
                co.getDepartment()
                        .flatMap(c_OptionalInGetAndSet.Department::getBoss)
                        .map(c_OptionalInGetAndSet.Manager::getName));

        // Пример 6.19  Использование метода flatMap для компании, обернутой Optional
        Optional<c_OptionalInGetAndSet.Company> company = Optional.of(co);
        System.out.println(
                company
                        .flatMap(c_OptionalInGetAndSet.Company::getDepartment)
                        .flatMap(c_OptionalInGetAndSet.Department::getBoss)
                        .map(c_OptionalInGetAndSet.Manager::getName)
        );

    }

}
