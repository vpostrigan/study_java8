package modern_java_recipes.chapter6_optional;

import java.util.Optional;

/**
 * Требуется использовать Optional в методах чтения и установки.
 */
public class c_OptionalInGetAndSet {

    public static void main(String[] args) {

    }

    static class Company {
        private Department department;

        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "department=" + department +
                    '}';
        }
    }

    // Пример 6.12  Использование Optional на уровне DAO
    static class Department {
        private Manager boss;

        public Optional<Manager> getBoss() { // обертывать допускающие значение null атрибуты
            return Optional.ofNullable(boss);
        }

        public void setBoss(Manager boss) { // но не делать этого в методах установки
            this.boss = boss;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "boss=" + boss +
                    '}';
        }
    }

    static class Manager {
        private String name;

        public Manager(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Manager{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
