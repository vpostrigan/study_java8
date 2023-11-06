<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- Fig. 10.33: customTagAttribute.jsp -->
<!-- JSP that uses a custom tag to output content. -->

<%-- taglib directive --%>
<%@ taglib uri="advjhtp1-taglib.tld" prefix="advjhtp1" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Specifying Custom Tag Attributes</title>
</head>

<body>
<p>Demonstrating an attribute with a string value</p>
<h1>
    <advjhtp1:welcome2 firstName="Paul"/>
</h1>

<p>Demonstrating an attribute with an expression value</p>
<h1>
    <%-- scriptlet to obtain "name" request parameter --%>
    <%
        String name = request.getParameter("name");
    %>

    <advjhtp1:welcome2 firstName="<%= name %>"/>
</h1>
</body>

</html>
