<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>FreeFly. Rename login.</title>
</head>

<body onload="AuthorizedLogin()">

<h1>Rename login</h1>
Your current login:
<h2>
    <div class="panel-body" id="login"></div>
</h2>

</body>

<script src="../../js/updating_login.js" type="text/javascript"></script>
<script src="../../js/jquery.min.js" type="text/javascript"></script>

Your new login:
<br>
<input type="text" id="newLogin">
<br>
After rename, you must log in again
<br>
<button type="button" onclick="UpdateLogin($('#newLogin').val())">Rename login!</button>
<br>
-------------------------------------------
<div class="panel-body" id="response"></div>

</body>
</html>
