package advanced_java2_deitel.chapter20_jsp.fig10_36;

// Java core packages

import advanced_java2_deitel.chapter20_jsp.fig10_20.GuestBean;
import advanced_java2_deitel.chapter20_jsp.fig10_20.GuestDataBean;

import java.io.*;
import java.util.*;

// Java extension packages
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

// Fig. 10.37: GuestBookTag.java
// Custom tag handler that reads information from the guestbook
// database and makes that data available in a JSP.
public class GuestBookTag extends BodyTagSupport {
    private String firstName;
    private String lastName;
    private String email;

    private GuestDataBean guestData;
    private GuestBean guest;
    private Iterator iterator;

    // Method called to begin tag processing
    public int doStartTag() throws JspException {
        // attempt tag processing
        try {
            guestData = new GuestDataBean();

            List list = guestData.getGuestList();
            iterator = list.iterator();

            if (iterator.hasNext()) {
                processNextGuest();

                return EVAL_BODY_TAG;    // continue body processing
            } else
                return SKIP_BODY;     // terminate body processing
        } catch (Exception exception) {
            // if any exceptions occur, do not continue processing tag's body
            exception.printStackTrace();
            return SKIP_BODY;   // ignore the tag's body
        }
    }

    // process body and determine if body processing should continue
    public int doAfterBody() {
        // attempt to output body data
        try {
            bodyContent.writeOut(getPreviousOut());
        } catch (IOException ioException) {
            // if exception occurs, terminate body processing
            ioException.printStackTrace();
            return SKIP_BODY;  // terminate body processing
        }

        bodyContent.clearBody();

        if (iterator.hasNext()) {
            processNextGuest();

            return EVAL_BODY_TAG;    // continue body processing
        } else
            return SKIP_BODY;     // terminate body processing
    }

    // obtains the next GuestBean and extracts its data
    private void processNextGuest() {
        // get next guest
        guest = (GuestBean) iterator.next();

        pageContext.setAttribute("firstName", guest.getFirstName());
        pageContext.setAttribute("lastName", guest.getLastName());
        pageContext.setAttribute("email", guest.getEmail());
    }

}
