<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>FreeFly. Registration Form.</title>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/registration.js" type="text/javascript"></script>
</head>

<body onload="GenericCaptcha()">

<h1>Registration new user</h1>
Fill the form, please:
<table>
    <tr>
        <td>Login:</td>
        <td><input type="text" id="login" value="your login"></td>
    </tr>
    <tr>
        <td>E-mail:</td>
        <td><input type="email" id="email" value="your e-mail"></td>
    </tr>
    <tr>
        <td>Name:</td>
        <td><input type="text" id="name" value="your name"></td>
    </tr>
    <tr>
        <td>Last Name:</td>
        <td><input type="text" id="lastName" value="your last name"></td>
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
        <td></td>
        <td>
            <button type="button" onclick="Registration($('#login').val(), $('#email').val(), $('#name').val(),
            $('#lastName').val(), $('#password').val())">registration!
            </button>
        </td>
    </tr>
</table>

<br>
<table>
    <tr>
        <th>Cod</th>
        <th>Insert cod value</th>
    </tr>
    <tr>
        <td>
            <input type="text" id="captcha" disabled="true"/>
        </td>
        <td>
            <input type="text" id="captchaResult">
        </td>
    </tr>
</table>
<br>
----------------------------------------
<div class="panel panel-default">
    <div class="panel-body" id="response"></div>
</div>

</body>

</html>
