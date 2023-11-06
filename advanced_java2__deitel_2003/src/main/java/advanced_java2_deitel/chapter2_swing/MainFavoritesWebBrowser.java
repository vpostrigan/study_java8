package advanced_java2_deitel.chapter2_swing;

import javax.swing.*;
import java.awt.*;

public class MainFavoritesWebBrowser extends JFrame {
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;
    private WebBrowserPane favoritesBrowserPane;

    public MainFavoritesWebBrowser() {
        super("Deitel Web Browser");

        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);
        favoritesBrowserPane = new WebBrowserPane();

        favoritesBrowserPane.addHyperlinkListener(toolBar);

        favoritesBrowserPane.goToUrl(getClass().getResource("favorites.html"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(favoritesBrowserPane),
                new JScrollPane(browserPane));
        splitPane.setDividerLocation(210);
        splitPane.setOneTouchExpandable(true);

        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(splitPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFavoritesWebBrowser browser = new MainFavoritesWebBrowser();
        browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }

}
