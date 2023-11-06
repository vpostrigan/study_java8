<!-- Fig. 10.15: plugin.jsp -->

<html>

<head>
    <title>Using jsp:plugin to load an applet</title>
</head>

<body>

<jsp:plugin type="applet"
            code="com.deitel.advjhtp1.jsp.applet.ShapesApplet"
            codebase="/advjhtp1/jsp"
            width="400"
            height="400">
    <jsp:params>
        <jsp:param name="red" value="255"/>
        <jsp:param name="green" value="255"/>
        <jsp:param name="blue" value="0"/>
    </jsp:params>
</jsp:plugin>

</body>
</html>
