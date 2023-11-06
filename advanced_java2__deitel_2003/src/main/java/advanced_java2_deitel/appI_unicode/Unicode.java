package advanced_java2_deitel.appI_unicode;

import java.awt.*;
import javax.swing.*;

// Fig. K.3: Unicode.java
// Demonstrating how to use Unicode in Java programs.
public class Unicode extends JFrame {

    private JLabel english, chinese, cyrillic, french, german,
            japanese, portuguese, spanish;

    public Unicode() {
        super("Demonstrating Unicode");

        // get content pane and set its layout
        Container container = getContentPane();
        container.setLayout(new GridLayout(8, 1));

        // JLabel constructor with a string argument
        english = new JLabel("\u0057\u0065\u006C\u0063\u006F" +
                "\u006D\u0065\u0020\u0074\u006F\u0020Unicode\u0021");
        english.setToolTipText("This is English");
        container.add(english);

        chinese = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u0020\u0020Unicode\u0021");
        chinese.setToolTipText("This is Traditional Chinese");
        container.add(chinese);

        cyrillic = new JLabel("\u0414\u043E\u0431\u0440\u043E" +
                "\u0020\u043F\u043E\u0436\u0430\u043B\u043E\u0432" +
                "\u0430\u0422\u044A\u0020\u0432\u0020Unicode\u0021");
        cyrillic.setToolTipText("This is Russian");
        container.add(cyrillic);

        french = new JLabel("\u0042\u0069\u0065\u006E\u0076" +
                "\u0065\u006E\u0075\u0065\u0020\u0061\u0075\u0020" +
                "Unicode\u0021");
        french.setToolTipText("This is French");
        container.add(french);

        german = new JLabel("\u0057\u0069\u006C\u006B\u006F" +
                "\u006D\u006D\u0065\u006E\u0020\u007A\u0075\u0020" +
                "Unicode\u0021");
        german.setToolTipText("This is German");
        container.add(german);

        japanese = new JLabel("Unicode\u3078\u3087\u3045\u3053\u305D\u0021");
        japanese.setToolTipText("This is Japanese");
        container.add(japanese);

        portuguese = new JLabel("\u0053\u00E9\u006A\u0061\u0020" +
                "\u0042\u0065\u006D\u0076\u0069\u006E\u0064" +
                "\u006F\u0020Unicode\u0021");
        portuguese.setToolTipText("This is Portuguese");
        container.add(portuguese);

        spanish = new JLabel("\u0042\u0069\u0065\u006E\u0076" +
                "\u0065\u006E\u0069\u0064\u0061\u0020\u0061\u0020" +
                "Unicode\u0021");
        spanish.setToolTipText("This is Spanish");
        container.add(spanish);
    }

    public static void main(String args[]) {
        Unicode application = new Unicode();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.pack();
        application.setVisible(true);
    }

}
