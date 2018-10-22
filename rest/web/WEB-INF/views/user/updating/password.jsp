<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FreeFly. Change password.</title>
</head>
<body onload="AuthorizedLogin()">

<h1>Form for update password</h1>

Your login:
<div class="panel-body" id="login"></div>
<br>
<table>
    <tr>
        <td>Enter your current password:</td>
        <td><input type="password" id="currentPassword"></td>
    </tr>
    <tr>
        <td>New password:</td>
        <td><input type="password" id="password"></td>
    </tr>
    <tr>
        <td>Repeat new password:</td>
        <td><input type="password" id="password2"></td>
    </tr>
    <tr>
        <td>
            <button type="button" onclick="UpdatePasswordByUser($('#login').val(), $('#currentPassword').val(), $('#password').val(), $('#password2').val())">submit
            </button>
        </td>
    </tr>
</table>
---------------------------------------------------------
<div class="panel-body" id="response"></div>

<script src="../../js/jquery.min.js" type="text/javascript"></script>
<script src="../../js/updating_password.js" type="text/javascript"></script>
<script src="../../js/registration.js" type="text/javascript"></script>

</body>
</html>
