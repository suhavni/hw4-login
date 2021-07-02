<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <h3>Removing ${delete} <br><br></h3>
        <p>${error}</p>
        <p>Put in your password to confirm</p>
        <form action="/remove-user" method="post">
            Password:<br/>
            <input type="password" name="password"/>
            <br><br>
            <input type="submit" value="Confirm">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
