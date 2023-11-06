package advanced_java2_deitel.chapter20_jsp.fig10_20;

// Java core packages

import java.io.*;
import java.sql.*;
import java.util.*;

// GuestDataBean.java
// Class GuestDataBean makes a database connection and supports
// inserting and retrieving data from the database.
public class GuestDataBean {

    private Connection connection;
    private PreparedStatement addRecord, getRecords;

    // construct TitlesBean object
    public GuestDataBean() throws Exception {
        // load the Cloudscape driver
        Class.forName("COM.cloudscape.core.RmiJdbcDriver");

        // connect to the database
        connection = DriverManager.getConnection("jdbc:rmi:jdbc:cloudscape:guestbook");

        getRecords = connection.prepareStatement(
                "SELECT firstName, lastName, email FROM guests");
        addRecord = connection.prepareStatement(
                "INSERT INTO guests ( firstName, lastName, email ) VALUES ( ?, ?, ? )");
    }

    // return an ArrayList of GuestBeans
    public List<GuestBean> getGuestList() throws SQLException {
        List<GuestBean> guestList = new ArrayList<>();

        // obtain list of titles
        ResultSet results = getRecords.executeQuery();

        // get row data
        while (results.next()) {
            GuestBean guest = new GuestBean();

            guest.setFirstName(results.getString(1));
            guest.setLastName(results.getString(2));
            guest.setEmail(results.getString(3));

            guestList.add(guest);
        }
        return guestList;
    }

    // insert a guest in guestbook database
    public void addGuest(GuestBean guest) throws SQLException {
        addRecord.setString(1, guest.getFirstName());
        addRecord.setString(2, guest.getLastName());
        addRecord.setString(3, guest.getEmail());

        addRecord.executeUpdate();
    }

    // close statements and terminate database connection
    @Override
    protected void finalize() {
        // attempt to close database connection
        try {
            getRecords.close();
            addRecord.close();
            connection.close();
        } catch (SQLException sqlException) { // process SQLException on close operation
            sqlException.printStackTrace();
        }
    }

}
