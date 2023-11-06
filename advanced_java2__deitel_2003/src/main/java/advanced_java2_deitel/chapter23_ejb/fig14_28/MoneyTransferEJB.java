package advanced_java2_deitel.chapter23_ejb.fig14_28;

// Java core libraries

import java.util.*;
import java.sql.*;

// Java standard extensions
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;

// MoneyTransferEJB.java
// MoneyTransferEJB is a stateless session EJB for transferring
// funds from an Account at BankABC to an Account at BankXYZ
// using container-managed transaction demarcation.
public class MoneyTransferEJB implements SessionBean {

    private SessionContext sessionContext;
    private Connection bankOneConnection;
    private Connection bankTwoConnection;
    private PreparedStatement withdrawalStatement;
    private PreparedStatement depositStatement;

    // transfer funds from BankABC to BankXYZ
    public void transfer(double amount) throws EJBException {
        // transfer funds from account in BankABC to account in
        // BankXYZ using container-managed transaction demarcation
        try {
            withdrawalStatement.setDouble(1, amount);
            // withdraw funds from account at BankABC
            withdrawalStatement.executeUpdate();

            depositStatement.setDouble(1, amount);
            // deposit funds in account at BankXYZ
            depositStatement.executeUpdate();
        } catch (SQLException sqlException) {
            // handle exception withdrawing and depositing

            // throw EJBException to indicate transfer failed
            // and roll back container-managed transaction
            throw new EJBException(sqlException);
        }
    }

    // get balance of Account at BankABC
    public double getBankABCBalance() throws EJBException {
        // get balance of Account at BankABC
        try {
            // select balance for Account # 12345
            String select = "SELECT balance FROM Account WHERE accountID = 12345";

            PreparedStatement selectStatement = bankOneConnection.prepareStatement(select);

            ResultSet resultSet = selectStatement.executeQuery();

            // get first record in ResultSet and return balance
            if (resultSet.next())
                return resultSet.getDouble("balance");
            else
                throw new EJBException("Account not found");
        } catch (SQLException sqlException) {
            // handle exception when getting Account balance
            throw new EJBException(sqlException);
        }
    }

    // get balance of Account at BankXYZ
    public double getBankXYZBalance() throws EJBException {
        // get balance of Account at BankXYZ
        try {
            // select balance for Account # 54321
            String select = "SELECT balance FROM Account WHERE accountID = 54321";

            PreparedStatement selectStatement = bankTwoConnection.prepareStatement(select);

            ResultSet resultSet = selectStatement.executeQuery();

            // get first record in ResultSet and return balance
            if (resultSet.next())
                return resultSet.getDouble("balance");
            else
                throw new EJBException("Account not found");
        } catch (SQLException sqlException) {
            // handle exception when getting Account balance
            throw new EJBException(sqlException);
        }
    }

    // set SessionContext
    public void setSessionContext(SessionContext context) throws EJBException {
        sessionContext = context;

        openDatabaseResources();
    }

    // create MoneyTransfer instance
    public void ejbCreate() {
    }

    // remove MoneyTransfer instance
    public void ejbRemove() throws EJBException {
        closeDatabaseResources();
    }

    // passivate MoneyTransfer instance
    public void ejbPassivate() throws EJBException {
        closeDatabaseResources();
    }

    // activate MoneyTransfer instance
    public void ejbActivate() throws EJBException {
        openDatabaseResources();
    }

    // close database Connections and PreparedStatements
    private void closeDatabaseResources() throws EJBException {
        // close database resources
        try {
            // close PreparedStatements
            depositStatement.close();
            depositStatement = null;

            withdrawalStatement.close();
            withdrawalStatement = null;

            // close database Connections
            bankOneConnection.close();
            bankOneConnection = null;

            bankTwoConnection.close();
            bankTwoConnection = null;
        } catch (SQLException sqlException) {
            // handle exception closing database connections
            throw new EJBException(sqlException);
        }
    }

    // open database Connections and create PreparedStatements
    private void openDatabaseResources() throws EJBException {
        // look up the BankABC and BankXYZ DataSources and create Connections for each
        try {
            Context initialContext = new InitialContext();

            // get DataSource reference from JNDI directory
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/BankABC");

            // get Connection from DataSource
            bankOneConnection = dataSource.getConnection();

            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/BankXYZ");

            bankTwoConnection = dataSource.getConnection();

            // prepare withdraw statement for account #12345 at BankABC
            String withdrawal = "UPDATE Account SET balance = balance - ? WHERE accountID = 12345";

            withdrawalStatement = bankOneConnection.prepareStatement(withdrawal);

            // prepare deposit statment for account #54321 at BankXYZ
            String deposit = "UPDATE Account SET balance = balance + ? WHERE accountID = 54321";

            depositStatement = bankTwoConnection.prepareStatement(deposit);
        } catch (NamingException namingException) {
            // handle exception if DataSource not found in directory
            throw new EJBException(namingException);
        } catch (SQLException sqlException) {
            // handle exception getting Connection to DataSource
            throw new EJBException(sqlException);
        }
    }

}
