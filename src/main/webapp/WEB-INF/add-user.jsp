<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css-styles/styles.css">
    </head>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out" class="right-aligned">
        </form>
        <h2>Add User</h2>
        <p class="error">${error}</p>
        <form action="/add-user" method="post" class="container">
            Username:<br/>
            <input type="text" name="username" class="in-containter">
            <br/>
            Password:<br/>
            <input type="password" name="password" class="in-containter">
            <br><br>
            <input type="submit" value="Submit" class="right-aligned">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
