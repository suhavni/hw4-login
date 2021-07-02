<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out">
        </form>
        <h2>Editing User, ${username}</h2>
        <p>${error}</p>
        <p>Please leave unchanged fields blank</p>
        <form action="/edit-user" method="post">
            New Username:<br/>
            <input type="text" name="new_username"/>
            <br/>
            New Password:<br/>
            <input type="password" name="new_password">
            <br/>
            First Name:<br/>
            <input type="text" name="firstname">
            <br/>
            Last Name:<br/>
            <input type="text" name="lastname">
            <br/>
            Input Old Password To Confirm:<br/>
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Confirm">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
