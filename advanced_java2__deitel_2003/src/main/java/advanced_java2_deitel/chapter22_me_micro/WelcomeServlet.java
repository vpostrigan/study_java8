package advanced_java2_deitel.chapter22_me_micro;

// Java core package

import java.io.*;

// Java extension packages
import javax.servlet.*;
import javax.servlet.http.*;

// WelcomeServlet.java
// Delivers appropriate "Welcome" screen to client
public class WelcomeServlet extends HttpServlet {

    // respond to get request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // determine User-Agent header
        String userAgent = request.getHeader("User-Agent");

        // send welcome screen to appropriate client
        if (userAgent.indexOf(ClientUserAgentHeaders.IE) != -1)
            sendIEClientResponse(request, response);
        else if (userAgent.indexOf(ClientUserAgentHeaders.WAP) != -1) // WAP
            sendWAPClientResponse(request, response);
        else if (userAgent.indexOf(ClientUserAgentHeaders.IMODE) != -1) // i-mode
            sendIModeClientResponse(request, response);
        else if (userAgent.indexOf(ClientUserAgentHeaders.J2ME) != -1) // J2ME
            sendJ2MEClientResponse(request, response);
    }

    // send welcome screen to IE client
    private void sendIEClientResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        redirect("text/html", "/XHTML/index.html", request, response);
    }

    // send welcome screen to Nokia WAP client
    private void sendWAPClientResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        redirect("text/vnd.wap.wml", "/WAP/index.wml", request, response);
    }

    // send welcome screen to i-mode client
    private void sendIModeClientResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        redirect("text/html", "/iMode/index.html", request, response);
    }

    // send welcome screen to J2ME client
    private void sendJ2MEClientResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // send J2ME client text data
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        // open file to send J2ME client
        BufferedReader bufferedReader = new BufferedReader(new FileReader(getServletContext().getRealPath("j2me/index.txt")));

        String inputString = bufferedReader.readLine();

        // send each line in file to J2ME client
        while (inputString != null) {
            out.println(inputString);
            inputString = bufferedReader.readLine();
        }

        out.close(); // done sending data
    }

    // redirects client request to another page
    private void redirect(String contentType, String redirectPage,
                          HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // set new content type
        response.setContentType(contentType);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectPage);

        // forward user to redirectPage
        dispatcher.forward(request, response);
    }

}

