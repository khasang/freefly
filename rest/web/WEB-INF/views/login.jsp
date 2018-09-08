<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    
<head>       
    <title>User Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
    
<body>
<div id="wrapper">
    <div id="login" class="animate form">
        <form action="j_spring_security_check" method="post" autocomplete="on">
            <h3>Sign in to FreeFly</h3>
            <p>
                <label for="username" class="uname" data-icon="u"><span>Your login</span></label>
                <input id="username" name="j_username" required="required" type="text" placeholder="login"/>
            </p>
            <p>
                <label for="password" class="youpasswd" data-icon="p"><span>Your password</span></label>
                <input id="password" name="j_password" required="required" type="password" placeholder="password"/>
            </p>
            <p>
                <c:if test="${not empty error}">
            <div class="error" style="text-align: center;">${error}</div>
            </c:if>
            </p>
            <p class="keeplogin">

            </p>
            <p class="login button">
                <input type="submit" value="Login"/>
            </p>
            <p class="change_link">
                <span>Not a member yet ?</span>
                <a href="#toregister" class="to_register">Register Now</a>
            </p>
        </form>
    </div>
</div>
</div>
</body>
</html>