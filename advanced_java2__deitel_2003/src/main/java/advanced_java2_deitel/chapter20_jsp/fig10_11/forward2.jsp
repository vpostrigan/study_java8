<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- forward2.jsp -->

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Processing a forwarded request</title>

    <style type="text/css">
        .big {
            font-family: tahoma, helvetica, arial, sans-serif;
            font-weight: bold;
            font-size: 2em;
        }
    </style>
</head>

<body>
<p class="big">
    Hello <%= request.getParameter("firstName") %>, <br/>
    Your request was received <br/> and forwarded at
</p>

<table style="border: 6px outset;">
    <tr>
        <td style="background-color: black;">
            <p class="big" style="color: cyan;">
                <%= request.getParameter("date") %>
            </p>
        </td>
    </tr>
</table>
</body>

</html>

