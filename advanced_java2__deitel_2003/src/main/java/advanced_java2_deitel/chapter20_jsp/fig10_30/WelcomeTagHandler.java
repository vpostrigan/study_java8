package advanced_java2_deitel.chapter20_jsp.fig10_30;

// Java core packages

import java.io.*;

// Java extension packages
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

// Fig. 10.31: WelcomeTagHandler.java
// Custom tag handler that handles a simple tag.
public class WelcomeTagHandler extends TagSupport {

    // Method called to begin tag processing
    public int doStartTag() throws JspException {
        // attempt tag processing
        try {
            // obtain JspWriter to output content
            JspWriter out = pageContext.getOut();
            out.print("Welcome to JSP Tag Libraries!");
        } catch (IOException ioException) {
            // rethrow IOException to JSP container as JspException
            throw new JspException(ioException.getMessage());
        }
        return SKIP_BODY;  // ignore the tag's body
    }

}
