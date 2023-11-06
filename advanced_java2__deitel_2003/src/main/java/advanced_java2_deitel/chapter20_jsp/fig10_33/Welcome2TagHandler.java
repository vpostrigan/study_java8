package advanced_java2_deitel.chapter20_jsp.fig10_33;

// Java core packages

import java.io.*;

// Java extension packages
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

// Fig. 10.34: Welcome2TagHandler.java
// Custom tag handler that handles a simple tag.
public class Welcome2TagHandler extends TagSupport {
    private String firstName = "";

    // Method called to begin tag processing
    public int doStartTag() throws JspException {
        // attempt tag processing
        try {
            // obtain JspWriter to output content
            JspWriter out = pageContext.getOut();
            out.print("Hello " + firstName + ", <br />Welcome to JSP Tag Libraries!");
        } catch (IOException ioException) {
            // rethrow IOException to JSP container as JspException
            throw new JspException(ioException.getMessage());
        }
        return SKIP_BODY;  // ignore the tag's body
    }

    // set firstName attribute to the users first name
    public void setFirstName(String username) {
        firstName = username;
    }

}
