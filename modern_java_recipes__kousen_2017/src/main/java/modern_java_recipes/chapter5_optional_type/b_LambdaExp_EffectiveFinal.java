package modern_java_recipes.chapter5_optional_type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Внутри лямбда-выражения требуется получить доступ к переменной, определенной вне его.
 */
public class b_LambdaExp_EffectiveFinal {

    public static void main(String[] args) {
        // Пример 5.6  Вычисление суммы чисел в списке
        List<Integer> nums = Arrays.asList(3, 1, 4, 1, 5, 9);

        int total = 0;
        for (int n : nums) {
            total += n;
        }

        total = 0;
        //nums.forEach(n -> total += n);
        // not effectively final

        total = 0;
        total = nums.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    // Пример 5.4  Тривиальный пользовательский интерфейс на Swing
    private static class MyGUI extends JFrame {
        private JTextField name = new JTextField("Please enter your name");
        private JTextField response = new JTextField("Greeting");
        private JButton button = new JButton("Say Hi");

        public MyGUI() {
            // до выхода Java 8 компилятор потребовал бы, чтобы переменная greeting была объявлена с ключевым словом final
            String greeting = "Hello, %s!";

            button.addActionListener(e -> {
                response.setText(String.format(greeting, name.getText()));
                // greeting = "Anything else";
            });
        }
    }

}
