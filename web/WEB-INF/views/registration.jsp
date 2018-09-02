<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>FreeFly. Registration Form.</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
</head>

<script>
    var RestPostAddNewUser = function () {

        JSONObject = {
            "login": document.getElementById("login").value,
            "email": document.getElementById("email").value,
            "firstName": document.getElementById("name").value,
            "lastName": document.getElementById("lastName").value,
            "password": document.getElementById("password").value
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/registration/add/user",
            contentType: "application/json;utf-8",
            data: JSON.stringify(JSONObject),
            dataType: "json",
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };

    var ValidPassword = function () {
        if (document.getElementById("password").value != document.getElementById("password2").value) {
            alert("passwords not equals. Fix and try again");
            return false;
        }
        if (String(document.getElementById("password").value).length < 5) {
            alert("password's length must be more 5. Fix and try again");
            return false;
        }
        return true;
    };

    var Registration = function () {
        if (ValidPassword()) {
            RestPostAddNewUser();
        }
    };
</script>

<body>
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
            <button type="button" onclick="Registration()">registration!</button>
        </td>
    </tr>
</table>

<br>
<div class="panel panel-default">
    <div class="panel-body" id="response"></div>
</div>

</body>

</html>
