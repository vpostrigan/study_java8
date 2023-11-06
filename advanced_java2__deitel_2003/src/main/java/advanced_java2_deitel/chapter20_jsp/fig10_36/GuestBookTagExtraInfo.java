package advanced_java2_deitel.chapter20_jsp.fig10_36;

// Java core packages

import javax.servlet.jsp.tagext.*;

// Fig.10.38: GuestBookTagExtraInfo.java
// Class that defines the variable names and types created by custom tag handler GuestBookTag.
public class GuestBookTagExtraInfo extends TagExtraInfo {

    // method that returns information about the variables
    // GuestBookTag creates for use in a JSP
    public VariableInfo[] getVariableInfo(TagData tagData) {
        VariableInfo firstName = new VariableInfo("firstName", "String", true, VariableInfo.NESTED);
        VariableInfo lastName = new VariableInfo("lastName", "String", true, VariableInfo.NESTED);
        VariableInfo email = new VariableInfo("email", "String", true, VariableInfo.NESTED);

        VariableInfo varableInfo[] = {firstName, lastName, email};

        return varableInfo;
    }

}

