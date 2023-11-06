package advanced_java2_deitel.chapter2_swing;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.net.MalformedURLException;
import java.net.URL;

public class WebToolBar extends JToolBar implements HyperlinkListener {
    private WebBrowserPane webBrowserPane;
    private JButton backButton;
    private JButton forwardButton;
    private JTextField urlTextFiled;

    public WebToolBar(WebBrowserPane browser) {
        super("Web Navigation");

        webBrowserPane = browser;
        webBrowserPane.addHyperlinkListener(this);

        urlTextFiled = new JTextField(25);
        urlTextFiled.addActionListener(event -> {
            try {
                URL url = new URL(urlTextFiled.getText());
                webBrowserPane.goToUrl(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        backButton = new JButton(new ImageIcon(getClass().getResource("back.gif")));
        backButton.addActionListener(event -> {
            URL url = webBrowserPane.back();
            urlTextFiled.setText(url.toString());
        });

        forwardButton = new JButton(new ImageIcon(getClass().getResource("forward.gif")));
        forwardButton.addActionListener(event -> {
            URL url = webBrowserPane.forward();
            urlTextFiled.setText(url.toString());
        });

        add(urlTextFiled);
        add(backButton);
        add(forwardButton);
    }

    public void hyperlinkUpdate(HyperlinkEvent event) {
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            URL url = event.getURL();

            webBrowserPane.goToUrl(url);
            urlTextFiled.setText(url.toString());
        }
    }

}
