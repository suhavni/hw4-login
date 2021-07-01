<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <h2>Add User</h2>
        <p>${error}</p>
        <form action="/add-user" method="post">
            Username:<br/>
            <input type="text" name="username"/>
            <br/>
            Password:<br/>
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Submit">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
