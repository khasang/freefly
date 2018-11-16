<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Font Styles -->
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"/>

    <!-- Favicon -->
    <link href="favicon/favicon.ico" rel="shortcut icon" type="image/x-icon">

    <title>Freefly</title>
</head>
<body>

<header>
    <div class="header">
        <nav>
            <a href="/"><img src="images/logo.png" alt="logo"/></a>
        </nav>
        <div class="header__section">
            <div class="header__item headerButton"><a>Hello, ${author}!</a></div>
            <div class="header__item headerButton"><a href="/registration">Регистрация</a></div>
            <div class="header__item headerButton"><a href="/login">Войти</a></div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div class="header__item headerButton"><a href="/logout">Выйти</a></div>
            </c:if>
        </div>
    </div>
</header>


<section>


</section>





<footer class="footer">
    <div class="container text-right">
        <a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
        <a href="https://twitter.com/"><i class="fa fa-twitter"></i></a>
        <a href="https://www.linkedin.com/"><i class="fa fa-linkedin"></i></a>
        <a href="https://www.skype.com/"><i class="fa fa-skype"></i></a>
    </div>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>


