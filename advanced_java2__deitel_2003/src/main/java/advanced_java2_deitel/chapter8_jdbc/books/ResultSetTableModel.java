package advanced_java2_deitel.chapter8_jdbc.books;

// Java core packages

import java.sql.*;
import java.util.*;

// Java extension packages
import javax.swing.table.*;

// Fig. 8.28: ResultSetTableModel.java
// A TableModel that supplies ResultSet data to a JTable.

// ResultSet rows and columns are counted from 1 and JTable rows and columns are counted from 0.
// When processing ResultSet rows or columns for use in a JTable, it is
// necessary to add 1 to the row or column number to manipulate
// the appropriate ResultSet column (i.e., JTable column 0 is
// ResultSet column 1 and JTable row 0 is ResultSet row 1).
public class ResultSetTableModel extends AbstractTableModel {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;

    // initialize resultSet and obtain its meta data object;
    // determine number of rows
    public ResultSetTableModel(String driver, String url, String query) throws SQLException, ClassNotFoundException {
        // load database driver class
        Class.forName(driver);

        // connect to database
        connection = DriverManager.getConnection(url);

        // create Statement to query database
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        // set query and execute it
        setQuery(query);
    }

    // set new database query string
    public void setQuery(String query) throws SQLException {
        // specify query and execute it
        resultSet = statement.executeQuery(query);

        // obtain meta data for ResultSet
        metaData = resultSet.getMetaData();

        // determine number of rows in ResultSet
        resultSet.last();                   // move to last row
        numberOfRows = resultSet.getRow();  // get row number

        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    // get class that represents column type
    @Override
    public Class getColumnClass(int column) {
        // determine Java class of column
        try {
            String className = metaData.getColumnClassName(column + 1);

            // return Class object that represents className
            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // if problems occur above, assume type Object
        return Object.class;
    }

    // get number of columns in ResultSet
    @Override
    public int getColumnCount() {
        // determine number of columns
        try {
            return metaData.getColumnCount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        // if problems occur above, return 0 for number of columns
        return 0;
    }

    // get name of a particular column in ResultSet
    @Override
    public String getColumnName(int column) {
        // determine column name
        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        // if problems, return empty string for column name
        return "";
    }

    // return number of rows in ResultSet
    @Override
    public int getRowCount() {
        return numberOfRows;
    }

    // obtain value in particular row and column
    @Override
    public Object getValueAt(int row, int column) {
        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(row + 1);

            return resultSet.getObject(column + 1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        // if problems, return empty string object
        return "";
    }

    // close Statement and Connection
    @Override
    protected void finalize() {
        // close Statement and Connection
        try {
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}