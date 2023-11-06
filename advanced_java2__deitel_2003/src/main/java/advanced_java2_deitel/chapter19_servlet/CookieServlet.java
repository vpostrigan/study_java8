package advanced_java2_deitel.chapter19_servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

// Fig. 9.20: CookieServlet.java
// Using cookies to store data on the client computer.
public class CookieServlet extends HttpServlet {
    private final Map<String, String> books = new HashMap<>();

    // initialize Map books
    public void init() {
        books.put("C", "0130895725");
        books.put("C++", "0130895717");
        books.put("Java", "0130125075");
        books.put("VB6", "0134569555");
    }

    // receive language selection and send cookie containing recommended book to the client
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String isbn = books.get(language).toString();
        Cookie cookie = new Cookie(language, isbn);
        // cookie.setMaxAge(); default is session

        response.addCookie(cookie);  // must precede getWriter
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // send XHTML page to client

        // start XHTML document
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");

        // head section of document
        out.println("<head>");
        out.println("<title>Welcome to Cookies</title>");
        out.println("</head>");

        // body section of document
        out.println("<body>");
        out.println("<p>Welcome to Cookies! You selected " + language + "</p>");

        out.println("<p><a href = \"/advjhtp1/servlets/CookieSelectLanguage.html\">" +
                "Click here to choose another language</a></p>");
        out.println("<p><a href = \"/advjhtp1/cookies\">" +
                "Click here to get book recommendations</a></p>");
        out.println("</body>");

        // end XHTML document
        out.println("</html>");
        out.close();    // close stream
    }

    // read cookies from client and create XHTML document containing recommended books
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();  // get cookies

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // start XHTML document
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");

        // head section of document
        out.println("<head>");
        out.println("<title>Recommendations</title>");
        out.println("</head>");

        // body section of document
        out.println("<body>");

        // if there are any cookies, recommend a book for each ISBN
        if (cookies != null && cookies.length != 0) {
            out.println("<h1>Recommendations</h1>");
            out.println("<p>");

            // get the name of each cookie
            for (int i = 0; i < cookies.length; i++)
                out.println(cookies[i].getName() + " How to Program. ISBN#: " + cookies[i].getValue() + "<br />");

            out.println("</p>");
        } else {   // there were no cookies
            out.println("<h1>No Recommendations</h1>");
            out.println("<p>You did not select a language.</p>");
        }

        out.println("</body>");

        // end XHTML document
        out.println("</html>");
        out.close();    // close stream
    }

}
