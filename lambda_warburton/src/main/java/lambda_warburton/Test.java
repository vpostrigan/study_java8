package lambda_warburton;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        {
            Map<String, Long> map = Stream.of(args).collect(

                    Collectors.groupingBy(e -> (String) e, Collectors.counting()));

            map.forEach((e, v) -> System.out.println(e + " " + v));
        }
        System.out.println();

        {
            List<String> roster = Arrays.asList(args);
            List<String> namesOfMaleMembersCollect = roster.stream()
                    /*
                     * .filter(p -> p.getGender() == Person.Sex.MALE) .map(p -> p.getName())
                     */
                    .collect(Collectors.toList());
        }

        {
            args = new String[] { "dfg", "123" };
            Collection<String> args2 = Arrays.asList(args);
            args2.stream().forEach(s -> System.out.println(s));
        }

        {
            SortedSet<String> s = new TreeSet<String>((s1, s2) -> {
                return s1.compareTo(s2);
            });
            for (String a : args)
                s.add(a);
            System.out.println(s.size() + " distinct words: " + s);
        }

        {
            List<String> list = Arrays.asList("1", "3 ", "2 ", "4");
            list.stream().map(s -> s.trim()).collect(Collectors.toList()).stream().forEach(s -> System.out.println(s));
        }
    }

}
