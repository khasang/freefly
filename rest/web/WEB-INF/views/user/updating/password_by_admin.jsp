<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FreeFly. Change password</title>
</head>
<body>
<h1>Form for change user's password</h1>
<table>
    <tr>
        <td>Enter user login:</td>
        <td><input type="text" id="login"></td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><input type="password" id="password"></td>
    </tr>
    <tr>
        <td>Repeat password:</td>
        <td><input type="password" id="password2"></td>
    </tr>
    <tr>
        <td>
            <button type="button" onclick="UpdatePassword($('#login').val(), $('#password').val(), $('#password2').val())">submit</button>
        </td>
    </tr>
</table>

------------------------------------------------
<div class="panel panel-default">
    <div class="panel-body" id="response"></div>
</div>

<script src="../../js/updating_password.js" type="text/javascript"></script>
<script src="../../js/registration.js" type="text/javascript"></script>
<script src="../../js/jquery.min.js" type="text/javascript"></script>

</body>
</html>
