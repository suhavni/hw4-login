<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css-styles/styles.css">
</head>
<body>
        <form action="/logout" method="post" class="right-aligned">
            <input type="submit" value="Log Out">
        </form>
        <h2>Welcome, ${username}</h2>
        <div class="bordered">
            <h3>Your Information:</h3>
            ${information}
        </div>
        <br/><br/>
        <form action="/add-user" method="post">
            <input type="submit" value="Add User">
        </form>
        <p>${table}</p>
    </body>
</html>
