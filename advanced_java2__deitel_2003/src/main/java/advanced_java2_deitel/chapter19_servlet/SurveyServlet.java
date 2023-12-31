package advanced_java2_deitel.chapter19_servlet;

import java.io.*;
import java.text.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Fig. 9.27: SurveyServlet.java
// A Web-based survey that uses JDBC from a servlet.
public class SurveyServlet extends HttpServlet {
    private Connection connection;
    private PreparedStatement updateVotes, totalVotes, results;

    // set up database connection and prepare SQL statements
    public void init(ServletConfig config) throws ServletException {
        // attempt database connection and create PreparedStatements
        try {
            Class.forName("COM.cloudscape.core.RmiJdbcDriver");
            connection = DriverManager.getConnection("jdbc:rmi:jdbc:cloudscape:animalsurvey");

            // PreparedStatement to add one to vote total for a specific animal
            updateVotes = connection.prepareStatement("UPDATE surveyresults SET votes = votes + 1 WHERE id = ?");

            // PreparedStatement to sum the votes
            totalVotes = connection.prepareStatement("SELECT sum( votes ) FROM surveyresults");

            // PreparedStatement to obtain surveyoption table's data
            results = connection.prepareStatement("SELECT surveyoption, votes, id FROM surveyresults ORDER BY id");
        } catch (Exception exception) {
            // for any exception throw an UnavailableException to
            // indicate that the servlet is not currently available
            exception.printStackTrace();
            throw new UnavailableException(exception.getMessage());
        }
    }

    // process survey response
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // set up response to client
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DecimalFormat twoDigits = new DecimalFormat("0.00");

        // start XHTML document
        out.println("<?xml version = \"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");

        // head section of document
        out.println("<head>");

        // read current survey response
        int value = Integer.parseInt(request.getParameter("animal"));

        // attempt to process a vote and display current results
        try {
            // update total for current survey response
            updateVotes.setInt(1, value);
            updateVotes.executeUpdate();

            // get total of all survey responses
            ResultSet totalRS = totalVotes.executeQuery();
            totalRS.next();
            int total = totalRS.getInt(1);

            // get results
            ResultSet resultsRS = results.executeQuery();
            out.println("<title>Thank you!</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("<p>Thank you for participating.");
            out.println("<br />Results:</p><pre>");

            // process results
            int votes;

            while (resultsRS.next()) {
                out.print(resultsRS.getString(1));
                out.print(": ");
                votes = resultsRS.getInt(2);
                out.print(twoDigits.format((double) votes / total * 100));
                out.print("%  responses: ");
                out.println(votes);
            }

            resultsRS.close();

            out.print("Total responses: ");
            out.print(total);

            // end XHTML document
            out.println("</pre></body></html>");
            out.close();
        } catch (SQLException sqlException) { // if database exception occurs, return error page
            sqlException.printStackTrace();
            out.println("<title>Error</title>");
            out.println("</head>");
            out.println("<body><p>Database error occurred. ");
            out.println("Try again later.</p></body></html>");
            out.close();
        }
    }

    // close SQL statements and database when servlet terminates
    public void destroy() {
        // attempt to close statements and database connection
        try {
            updateVotes.close();
            totalVotes.close();
            results.close();
            connection.close();
        } catch (SQLException sqlException) { // handle database exceptions by returning error to client
            sqlException.printStackTrace();
        }
    }

}
