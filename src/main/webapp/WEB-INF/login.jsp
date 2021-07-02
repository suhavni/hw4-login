<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css-styles/styles.css">
    </head>
    <body>
        <h2>Login</h2>
        <p class="error">${error}</p>
        <form action="/login" method="post" class="container">
            Username:<br/>
            <input type="text" name="username" class="in-containter">
            <br/>
            Password:<br/>
            <input type="password" name="password" class="in-containter">
            <br><br>
            <input type="submit" value="Submit" class="right-aligned">
        </form> 
    </body>
</html>
