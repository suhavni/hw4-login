<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css-styles/styles.css">
    </head>
    <body>
        <form action="/logout" method="post">
            <input type="submit" value="Log Out" class="right-aligned">
        </form>
        <h2>Editing User, ${username}</h2>
        <p class="error">${error}</p>
        <p>Please leave unchanged fields blank</p>
        <form action="/edit-user" method="post" class="container">
            New Username:<br/>
            <input type="text" name="new_username" class="in-containter">
            <br/>
            New Password<br/>
            <input type="password" name="new_password" class="in-containter">
            <br/>
            First Name:<br/>
            <input type="text" name="firstname" class="in-containter">
            <br/>
            Last Name:<br/>
            <input type="text" name="lastname"class="in-containter">
            <br/><br/>
            Input Old Password To Confirm<br/>
            <input type="password" name="password" class="in-containter">
            <br><br>
            <input type="submit" value="Confirm" class="right-aligned">
        </form>
        <form action="/users" method="post">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
