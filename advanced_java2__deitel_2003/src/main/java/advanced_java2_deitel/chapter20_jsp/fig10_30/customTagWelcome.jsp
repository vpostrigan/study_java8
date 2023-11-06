<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- Fig. 10.30: customTagWelcome.jsp -->
<!-- JSP that uses a custom tag to output content. -->

<%-- taglib directive --%>
<%@ taglib uri="advjhtp1-taglib.tld" prefix="advjhtp1" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Simple Custom Tag Example</title>
</head>

<body>
<p>The following text demonstrates a custom tag:</p>
<h1>
    <advjhtp1:welcome/>
</h1>
</body>

</html>
