package advanced_java2_deitel.chapter8_jdbc.books;

// Java core packages

import java.awt.*;
import java.sql.*;

// Java extension packages
import javax.swing.*;

// Fig. 8.26: DisplayAuthors.java
// Displaying the contents of table authors in database books.
public class DisplayAuthors extends JFrame {

    // constructor connects to database, queries database,
    // processes results and displays results in window
    public DisplayAuthors() {
        super("Authors Table of Books Database");

        // connect to database books and query database
        try {
            // load database driver class
            Class.forName("COM.cloudscape.core.RmiJdbcDriver");

            Connection connection = DriverManager.getConnection("jdbc:cloudscape:rmi:books");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM authors");

            // process query results
            StringBuilder results = new StringBuilder();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            for (int i = 1; i <= numberOfColumns; i++) {
                results.append(metaData.getColumnName(i)).append("\t");
            }
            results.append("\n");

            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(resultSet.getObject(i)).append("\t");
                }
                results.append("\n");
            }

            // close statement and connection
            statement.close();
            connection.close();

            // set up GUI and display window
            JTextArea textArea = new JTextArea(results.toString());
            Container container = getContentPane();

            container.add(new JScrollPane(textArea));

            setSize(300, 100);  // set window size
            setVisible(true);   // display window
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        } catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null, classNotFound.getMessage(), "Driver Not Found", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }

    public static void main(String args[]) {
        DisplayAuthors window = new DisplayAuthors();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

