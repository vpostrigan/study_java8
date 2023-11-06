package advanced_java2_deitel.chapter8_jdbc.addressbook;

// Java core packages

// Fig. 8.34: AddressBookDataAccess.java
// Interface that specifies the methods for inserting,
// updating, deleting and finding records.
public interface AddressBookDataAccess {

    // Locate specified person by last name. Return AddressBookEntry containing information.
    public AddressBookEntry findPerson(String lastName);

    // Update information for specified person.
    // Return boolean indicating success or failure.
    public boolean savePerson(AddressBookEntry person) throws DataAccessException;

    // Insert a new person. Return boolean indicating success or failure.
    public boolean newPerson(AddressBookEntry person) throws DataAccessException;

    // Delete specified person. Return boolean indicating if success or failure.
    public boolean deletePerson(AddressBookEntry person) throws DataAccessException;

    // close data source connection
    public void close();
}
