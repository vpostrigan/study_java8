
<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- customTagBody.jsp -->

<%-- taglib directive --%>
<%@ taglib uri = "advjhtp1-taglib.tld" prefix = "advjhtp1" %>

<html xmlns = "http://www.w3.org/1999/xhtml">

<head>
    <title>Guest List</title>

    <style type = "text/css">
        body {
            font-family: tahoma, helvetica, arial, sans-serif
        }

        table, tr, td, th {
            text-align: center;
            font-size: .9em;
            border: 3px groove;
            padding: 5px;
            background-color: #dddddd
        }
    </style>
</head>

<body>
<p style = "font-size: 2em">Guest List</p>

<table>
    <thead>
        <th style = "width: 100px">Last name</th>
        <th style = "width: 100px">First name</th>
        <th style = "width: 200px">Email</th>
    </thead>
    <%-- guestlist custom tag --%>
    <advjhtp1:guestlist>
        <tr>
            <td><%= lastName %></td>
            <td><%= firstName %></td>
            <td><a href = "mailto:<%= email %>"><%= email %></a></td>
        </tr>
    </advjhtp1:guestlist>
</table>
</body>

</html>
