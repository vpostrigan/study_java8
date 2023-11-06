package advanced_java2_deitel.chapter19_servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

// Fig. 9.5: WelcomeServlet.java
// A simple servlet to process get requests.
public class WelcomeServlet extends HttpServlet {

    // process "get" requests from clients
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send XHTML page to client

        // start XHTML document
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");

        // head section of document
        out.println("<head>");
        out.println("<title>A Simple Servlet Example</title>");
        out.println("</head>");

        // body section of document
        out.println("<body>");
        out.println("<h1>Welcome to Servlets!</h1>");
        out.println("</body>");

        // end XHTML document
        out.println("</html>");
        out.close();  // close stream to complete the page
    }

}
