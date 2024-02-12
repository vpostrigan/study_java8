package modern_java_recipes.chapter4_comparators_and_collectors;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class b_Converting_Stream_into_Collection {

    public static void main(String[] args) {
        // Пример 4.8  Создание списка
        List<String> superHeroes =
                Stream.of("Mr. Furious", "The Blue Raja", "The Shoveler",
                                "The Bowler", "Invisible Boy", "The Spleen", "The Sphinx")
                        .collect(Collectors.toList());

        // Пример 4.9  Создание множества
        Set<String> villains =
                Stream.of("Casanova Frankenstein", "The Disco Boys",
                                "The Not-So-Goodie Mob", "The Suits", "The Suzies",
                                "The Furriers", "The Furriers")
                        .collect(Collectors.toSet());

        // Пример 4.10  Создание связного списка
        List<String> actors =
                Stream.of("Hank Azaria", "Janeane Garofalo", "William H. Macy",
                                "Paul Reubens", "Ben Stiller", "Kel Mitchell", "Wes Studi")
                        .collect(Collectors.toCollection(LinkedList::new));

        // Пример 4.11  Создание массива
        String[] wannabes =
                Stream.of("The Waffler", "Reverse Psychologist", "PMS Avenger")
                        .toArray(String[]::new);

        // Пример 4.12  Создание отображения
        Map<String, String> actorMap = Stream.of(new Actor())
                .collect(Collectors.toMap(Actor::getName, Actor::getRole));
        actorMap.forEach((key, value) ->
                System.out.printf("%s played %s%n", key, value)
        );
    }

    private static class Actor {
        String name;
        String role;

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }

}
