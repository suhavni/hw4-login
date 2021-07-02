<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css-styles/styles.css">
    </head>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out" class="right-aligned">
        </form>
        <h3>Removing ${delete} <br><br></h3>
        <p class="error">${error}</p>
        <p>Put in your password to confirm</p>
        <form action="/remove-user" method="post" class="container">
            Password:<br/>
            <input type="password" name="password" class="in-containter">
            <br><br>
            <input type="submit" value="Confirm" class="right-aligned">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
