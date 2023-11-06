package advanced_java2_deitel.chapter24_ejb_with_data.fig15_03;

// Java core libraries

import java.sql.*;

// Java standard extensions
import javax.ejb.*;
import javax.sql.*;
import javax.naming.*;

// EmployeeEJB.java
// EmployeeEJB is an entity EJB that uses bean-managed
// persistence to persist Employee data in a database.
public class EmployeeEJB implements EntityBean {

    private EntityContext entityContext;
    private Connection connection;

    private Integer employeeID;
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String title;
    private Double salary;

    // get Employee ID
    public Integer getEmployeeID() {
        return employeeID;
    }

    // set social security number
    public void setSocialSecurityNumber(String number) {
        socialSecurityNumber = number;
    }

    // get social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    // set first name
    public void setFirstName(String name) {
        firstName = name;
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }

    // set last name
    public void setLastName(String name) {
        lastName = name;
    }

    // get last name
    public String getLastName() {
        return lastName;
    }

    // set title
    public void setTitle(String jobTitle) {
        title = jobTitle;
    }

    // get title
    public String getTitle() {
        return title;
    }

    // set salary
    public void setSalary(Double amount) {
        salary = amount;
    }

    // get salary
    public Double getSalary() {
        return salary;
    }

    // create new Employee
    public Integer ejbCreate(Integer primaryKey) throws CreateException {
        employeeID = primaryKey;

        // INSERT new Employee in database
        try {
            // create INSERT statement
            String insert = "INSERT INTO Employee ( employeeID ) VALUES ( ? )";

            // create PreparedStatement to perform INSERT
            PreparedStatement insertStatement = connection.prepareStatement(insert);

            // set values for PreparedStatement
            insertStatement.setInt(1, employeeID.intValue());

            // execute INSERT and close PreparedStatement
            insertStatement.executeUpdate();
            insertStatement.close();

            return employeeID;
        } catch (SQLException sqlException) {
            // throw EJBException if INSERT fails
            throw new CreateException(sqlException.getMessage());
        }
    }

    // do post-creation tasks when creating new Employee
    public void ejbPostCreate(Integer primaryKey) {
    }

    // remove Employee information from database
    public void ejbRemove() throws RemoveException {
        // DELETE Employee record
        try {
            // get primary key of Employee to be removed
            Integer primaryKey = (Integer) entityContext.getPrimaryKey();

            // create DELETE statement
            String delete = "DELETE FROM Employee WHERE employeeID = ?";

            // create PreparedStatement to perform DELETE
            PreparedStatement deleteStatement = connection.prepareStatement(delete);

            // set values for PreparedStatement
            deleteStatement.setInt(1, primaryKey.intValue());

            // execute DELETE and close PreparedStatement
            deleteStatement.executeUpdate();
            deleteStatement.close();
        } catch (SQLException sqlException) {
            // throw new EJBException if DELETE fails
            throw new RemoveException(sqlException.getMessage());
        }
    }

    // store Employee information in database
    public void ejbStore() throws EJBException {
        // UPDATE Employee record
        try {
            // get primary key for Employee to be updated
            Integer primaryKey = (Integer) entityContext.getPrimaryKey();

            // create UPDATE statement
            String update = "UPDATE Employee SET " +
                    "socialSecurityNumber = ?, firstName = ?, " +
                    "lastName = ?, title = ?, salary = ? " +
                    "WHERE employeeID = ?";

            // create PreparedStatement to perform UPDATE
            PreparedStatement updateStatement = connection.prepareStatement(update);

            // set values in PreparedStatement
            updateStatement.setString(1, socialSecurityNumber);
            updateStatement.setString(2, firstName);
            updateStatement.setString(3, lastName);
            updateStatement.setString(4, title);
            updateStatement.setDouble(5, salary.doubleValue());
            updateStatement.setInt(6, primaryKey.intValue());

            // execute UPDATE and close PreparedStatement
            updateStatement.executeUpdate();
            updateStatement.close();
        } catch (SQLException sqlException) {
            // throw EJBException if UPDATE fails
            throw new EJBException(sqlException);
        }
    }

    // load Employee information from database
    public void ejbLoad() throws EJBException {
        // get Employee record from Employee database table
        try {
            // get primary key for Employee to be loaded
            Integer primaryKey = (Integer) entityContext.getPrimaryKey();

            // create SELECT statement
            String select = "SELECT * FROM Employee WHERE employeeID = ?";

            // create PreparedStatement for SELECT
            PreparedStatement selectStatement = connection.prepareStatement(select);
            // set employeeID value in PreparedStatement
            selectStatement.setInt(1, primaryKey.intValue());

            // execute selectStatement
            ResultSet resultSet = selectStatement.executeQuery();

            // get Employee information from ResultSet and update
            // local member variables to cache data
            if (resultSet.next()) {
                // get employeeID
                employeeID = new Integer(resultSet.getInt("employeeID"));
                // get social-security number
                socialSecurityNumber = resultSet.getString("socialSecurityNumber");
                // get first name
                firstName = resultSet.getString("firstName");
                // get last name
                lastName = resultSet.getString("lastName");
                // get job title
                title = resultSet.getString("title");
                // get salary
                salary = new Double(resultSet.getDouble("salary"));
            } else
                throw new EJBException("No such employee.");

            // close PreparedStatement
            selectStatement.close();
        } catch (SQLException sqlException) {
            // throw EJBException if SELECT fails
            throw new EJBException(sqlException);
        }
    }

    // find Employee using its primary key
    public Integer ejbFindByPrimaryKey(Integer primaryKey) throws FinderException, EJBException {
        // find Employee in database
        try {
            // create SELECT statement
            String select = "SELECT employeeID FROM Employee WHERE employeeID = ?";

            // create PreparedStatement for SELECT
            PreparedStatement selectStatement = connection.prepareStatement(select);

            // set employeeID value in PreparedStatement
            selectStatement.setInt(1, primaryKey.intValue());

            // execute selectStatement
            ResultSet resultSet = selectStatement.executeQuery();

            // return primary key if SELECT returns a record
            if (resultSet.next()) {

                // close resultSet and selectStatement
                resultSet.close();
                selectStatement.close();

                return primaryKey;
            } else // throw ObjectNotFoundException if SELECT produces no records
                throw new ObjectNotFoundException();
        } catch (SQLException sqlException) {
            // throw EJBException if SELECT fails
            throw new EJBException(sqlException);
        }
    }

    // set EntityContext and create DataSource Connection
    public void setEntityContext(EntityContext context) throws EJBException {
        // set entityContext
        entityContext = context;

        // look up the Employee DataSource and create Connection
        try {
            InitialContext initialContext = new InitialContext();

            // get DataSource reference from JNDI directory
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/Employee");

            // get Connection from DataSource
            connection = dataSource.getConnection();
        } catch (NamingException namingException) {
            // handle exception if DataSource not found in directory
            throw new EJBException(namingException);
        } catch (SQLException sqlException) {
            // handle exception when getting Connection to DataSource
            throw new EJBException(sqlException);
        }
    }

    // unset EntityContext
    public void unsetEntityContext() throws EJBException {
        entityContext = null;

        // close DataSource Connection
        try {
            connection.close();
        } catch (SQLException sqlException) {
            // throw EJBException if closing Connection fails
            throw new EJBException(sqlException);
        } finally {
            // prepare connection for reuse
            connection = null;
        }
    }

    // set employeeID to null when container passivates EJB
    public void ejbPassivate() {
        employeeID = null;
    }

    // get primary key value when container activates EJB
    public void ejbActivate() {
        employeeID = (Integer) entityContext.getPrimaryKey();
    }

}
