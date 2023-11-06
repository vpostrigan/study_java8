<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- Fig. 10.7: include.jsp -->

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Using jsp:include</title>

    <style type="text/css">
        body {
            font-family: tahoma, helvetica, arial, sans-serif;
        }

        table, tr, td {
            font-size: .9em;
            border: 3px groove;
            padding: 5px;
            background-color: #dddddd;
        }
    </style>
</head>

<body>
<table>
    <tr>
        <td style="width: 160px; text-align: center">
            <img src="images/logotiny.png" width="140" height="93" alt="Deitel & Associates, Inc. Logo"/>
        </td>
        <td>
            <%-- include banner.html in this JSP --%>
            <jsp:include page="banner.html" flush="true"/>
        </td>
    </tr>
    <tr>
        <td style="width: 160px">
            <%-- include toc.html in this JSP --%>
            <jsp:include page="toc.html" flush="true"/>
        </td>
        <td style="vertical-align: top">
            <%-- include clock2.jsp in this JSP --%>
            <jsp:include page="clock2.jsp" flush="true"/>
        </td>
    </tr>
</table>
</body>
</html>
