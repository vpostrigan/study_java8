package advanced_java2_deitel.chapter24_ejb_with_data.fig15_07;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.*;

// EmployeeEJB.java
// EmployeeEJB is an entity EJB that uses container-managed
// persistence to persist Employee data in a database.
public class EmployeeEJB implements EntityBean {

    private EntityContext entityContext;

    // container-managed fields
    public Integer employeeID;
    public String socialSecurityNumber;
    public String firstName;
    public String lastName;
    public String title;
    public Double salary;

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

    // create new Employee instance
    public Integer ejbCreate(Integer primaryKey) {
        employeeID = primaryKey;

        return null;
    }

    // do post-creation tasks when creating new Employee
    public void ejbPostCreate(Integer primaryKey) {
    }

    // set EntityContext
    public void setEntityContext(EntityContext context) {
        entityContext = context;
    }

    // unset EntityContext
    public void unsetEntityContext() {
        entityContext = null;
    }

    // activate Employee instance
    public void ejbActivate() {
        employeeID = (Integer) entityContext.getPrimaryKey();
    }

    // passivate Employee instance
    public void ejbPassivate() {
        employeeID = null;
    }

    // load Employee instance in database
    public void ejbLoad() {
    }

    // store Employee instance in database
    public void ejbStore() {
    }

    // remove Employee instance from database
    public void ejbRemove() {
    }

}
