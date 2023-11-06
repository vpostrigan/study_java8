<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- books.jsp -->

<%-- JSP page settings --%>
<%@ page language="java"
         import="advanced_java2_deitel.chapter21_servlet_and_jsp.*, java.util.*"
         session="true"
%>

<!-- begin document -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Book List</title>

    <link rel="stylesheet" href="styles.css" type="text/css"/>
</head>

<body>
<p class="bigFont">Available Books</p>
<p class="bold">Click a link to view book information</p>
<p>
    <%-- begin JSP scriptlet to create list of books --%>
    <%
        TitlesBean titlesBean = new TitlesBean();
        List titles = titlesBean.getTitles();
        BookBean currentBook;

        // store titles in session for further use
        session.setAttribute("titles", titles);

        // obtain an Iterator to the set of keys in the List
        Iterator iterator = titles.iterator();

        // use the Iterator to get each BookBean and create a link to each book
        while (iterator.hasNext()) {
            currentBook = (BookBean) iterator.next();

    %> <%-- end scriptlet to insert literal XHTML and --%>
    <%-- JSP expressions output from this loop     --%>

        <%-- link to a book's information --%>
        <span class="bold">
            <a href="displayBook?isbn=<%= currentBook.getISBN() %>">
               <%= currentBook.getTitle() + ", " + currentBook.getEditionNumber() + "e" %>
            </a>
         </span><br/>

    <% // continue scriptlet
        }   // end while loop
    %> <%-- end scriptlet --%>

</p>
</body>

</html>
