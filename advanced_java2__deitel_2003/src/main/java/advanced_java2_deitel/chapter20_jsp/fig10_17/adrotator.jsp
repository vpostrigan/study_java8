<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- Fig. 10.18: adrotator.jsp -->

<jsp:useBean id="rotator" scope="session"
             class="advanced_java2_deitel.chapter20_jsp.fig10_17.Rotator"/>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>AdRotator Example</title>

    <style type="text/css">
        .big {
            font-family: helvetica, arial, sans-serif;
            font-weight: bold;
            font-size: 2em;
        }
    </style>

    <%-- update advertisement --%>
    <% rotator.nextAd(); %>
</head>

<body>
<p class="big">AdRotator Example</p>

<p>
    <a href="<jsp:getProperty name = "rotator" property = "link" />">
        <img src="<jsp:getProperty name = "rotator" property = "image" />" alt="advertisement"/>
    </a>
</p>
</body>
</html>
