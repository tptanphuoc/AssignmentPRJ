<%-- 
    Document   : error.jsp
    Created on : Sep 28, 2022, 5:02:30 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        
        Error: <h1>${requestScope.ERROR}</h1>
    </body>
</html>
