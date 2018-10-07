<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>FreeFly. Updating registration data</title>
    <script src="js/updating_user.js" type="text/javascript"></script>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/registration.js" type="text/javascript"></script>
</head>

<body onload="GettingInfoAboutUser()">

<h1><div class="panel-body" id="login"></div></h1>
<table>
    <tr>
        <td>E-mail:</td>
        <td><input type="email" id="email" onchange="OnChange()"></td>
    </tr>
    <tr>
        <td>Name:</td>
        <td><input type="text" id="name" onchange="OnChange()"></td>
    </tr>
    <tr>
        <td>Last Name:</td>
        <td><input type="text" id="lastName" onchange="OnChange()"></td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="button" id = "updateButton" onclick="UpdateUser($('#email').val(), $('#name').val(), $('#lastName').val())">update!
            </button>
        </td>
    </tr>
</table>

--------------------------------------------
<div class="panel-body" id="response"></div>
</body>
</html>
