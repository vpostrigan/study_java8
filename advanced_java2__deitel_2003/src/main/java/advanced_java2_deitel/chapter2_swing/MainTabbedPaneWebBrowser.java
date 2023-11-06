package advanced_java2_deitel.chapter2_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainTabbedPaneWebBrowser extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();

    public MainTabbedPaneWebBrowser() {
        super("JTabbedPane Web Browser");

        createNewTab();

        getContentPane().add(tabbedPane);

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new NewTabAction());
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction());
        fileMenu.setMnemonic('F');

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void createNewTab() {
        JPanel panel = new JPanel(new BorderLayout());

        WebBrowserPane browserPane = new WebBrowserPane();
        WebToolBar toolBar = new WebToolBar(browserPane);

        panel.add(toolBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(browserPane), BorderLayout.CENTER);

        tabbedPane.addTab("Browser " + tabbedPane.getTabCount(), panel);
    }

    private class NewTabAction extends AbstractAction {
        public NewTabAction() {
            putValue(Action.NAME, "New Browser Tab");
            putValue(Action.SHORT_DESCRIPTION, "Create New Web Browser Tab");
            putValue(Action.MNEMONIC_KEY, Integer.valueOf('N'));
        }

        public void actionPerformed(ActionEvent event) {
            createNewTab();
        }
    }

    private class ExitAction extends AbstractAction {
        public ExitAction() {
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit Application");
            putValue(Action.MNEMONIC_KEY, Integer.valueOf('x'));
        }

        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        MainTabbedPaneWebBrowser browser = new MainTabbedPaneWebBrowser();
        browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }

}
