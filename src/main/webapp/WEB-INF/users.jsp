<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <h2>Welcome, ${username}</h2>
        <form action="/add-user" method="post">
            <input type="submit" value="Add User">
        </form>
        <form action="/remove-user" method="post">
            <input type="submit" value="Remove User">
        </form>
    </body>
</html>
