package advanced_java2_deitel.chapter20_jsp.fig10_20;

// Fig. 10.20: GuestBean.java
// JavaBean to store data for a guest in the guestbook.
public class GuestBean {
    private String firstName, lastName, email;

    // set the guest's first name
    public void setFirstName(String name) {
        firstName = name;
    }

    // get the guest's first name
    public String getFirstName() {
        return firstName;
    }

    // set the guest's last name
    public void setLastName(String name) {
        lastName = name;
    }

    // get the guest's last name
    public String getLastName() {
        return lastName;
    }

    // set the guest's email address
    public void setEmail(String address) {
        email = address;
    }

    // get the guest's email address
    public String getEmail() {
        return email;
    }

}
