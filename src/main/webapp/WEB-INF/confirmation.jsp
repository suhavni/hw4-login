<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <h3>Write your password to confirm</h3>
        <p>${error}</p>
        <form action="/confirmation" method="post">
            Password:<br/>
            <input type="password" name="password"/>
            <br><br>
            <input type="submit" value="Submit">
        </form>
        <form action="/remove-user" method="post">
            <input type="submit" value="Back">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
