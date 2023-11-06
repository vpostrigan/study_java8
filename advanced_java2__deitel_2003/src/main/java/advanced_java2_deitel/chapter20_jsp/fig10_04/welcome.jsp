<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- Fig. 10.4: welcome.jsp -->
<!-- JSP that processes a "get" request containing data. -->

<html xmlns="http://www.w3.org/1999/xhtml">

<!-- head section of document -->
<head>
    <title>Processing "get" requests with data</title>
</head>

<!-- body section of document -->
<body>
<% // begin scriptlet
    String name = request.getParameter("firstName");
    if (name != null) {
%> <%-- end scriptlet to insert fixed template data --%>

<h1>
    Hello <%= name %>, <br/>
    Welcome to JavaServer Pages!
</h1>

<% // continue scriptlet
}  // end if
else {
%> <%-- end scriptlet to insert fixed template data --%>

<form action="welcome.jsp" method="get">
    <p> Type your first name and press Submit</p>

    <p><input type="text" name="firstName"/>
        <input type="submit" value="Submit"/>
    </p>
</form>

<% // continue scriptlet
}  // end else
%> <%-- end scriptlet --%>
</body>

</html>
