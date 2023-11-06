package advanced_java2_deitel.chapter8_jdbc.books;

// Java core packages
import java.awt.*;
import java.sql.*;

// Java extension packages
import javax.swing.*;

// Fig. 8.31: DisplayQueryResults.java
// Display the contents of the Authors table in the Books database.
public class DisplayQueryResults extends JFrame {
    private ResultSetTableModel tableModel;
    private JTextArea queryArea;

    // create ResultSetTableModel and GUI
    public DisplayQueryResults() {
        super("Displaying Query Results");

        // Cloudscape database driver class name
        String driver = "COM.cloudscape.core.RmiJdbcDriver";

        // URL to connect to books database
        String url = "jdbc:cloudscape:rmi:books";

        // query to select entire authors table
        String query = "SELECT * FROM authors";

        // create ResultSetTableModel and display database table
        try {
            // create TableModel for results of query
            // SELECT * FROM authors
            tableModel = new ResultSetTableModel(driver, url, query);

            // set up JTextArea in which user types queries
            queryArea = new JTextArea(query, 3, 100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            // set up JButton for submitting queries
            JButton submitButton = new JButton("Submit Query");

            // create Box to manage placement of queryArea and submitButton in GUI
            Box box = Box.createHorizontalBox();
            box.add(scrollPane);
            box.add(submitButton);

            // create JTable delegate for tableModel
            JTable resultTable = new JTable(tableModel);

            // place GUI components on content pane
            Container c = getContentPane();
            c.add(box, BorderLayout.NORTH);
            c.add(new JScrollPane(resultTable), BorderLayout.CENTER);

            // create event listener for submitButton pass query to table model
            submitButton.addActionListener(
                    e -> {
                        // perform a new query
                        try {
                            tableModel.setQuery(queryArea.getText());
                        } catch (SQLException sqlException) {
                            JOptionPane.showMessageDialog(null, sqlException.toString(),
                                    "Database error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
            );

            // set window size and display window
            setSize(500, 250);
            setVisible(true);
        } catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null, "Cloudscape driver not found", "Driver not found", JOptionPane.ERROR_MESSAGE);

            System.exit(1);   // terminate application
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.toString(), "Database error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);   // terminate application
        }
    }

    // execute application
    public static void main(String args[]) {
        DisplayQueryResults app = new DisplayQueryResults();

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}