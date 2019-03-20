<%-- 
    Document   : login
    Created on : Feb 28, 2019, 9:12:14 PM
    Author     : Hoang Pham
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Log in</title>
    <link rel="stylesheet" href="style.css">
</head>
<script>
    var prevScrollpos = window.pageYOffset;
    window.onscroll = function () {
        var currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos && currentScrollPos != 0) {
            document.getElementById("sh").style.left = "9px";
        } else {
            document.getElementById("sh").style.left = "-70px";
        }
        prevScrollpos = currentScrollPos;
    };
</script>
<body>
    <c:if test="${sessionScope.USER == 'admin'}">
        <c:redirect url = "admin.jsp"/>
    </c:if>
    <div class="menu">
        <c:if test="${not empty sessionScope.USER}" var="signedin">
            <c:url var="LogoutLink" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${LogoutLink}">Logout</a>
            <c:url var="ProfileLink" value="MainController">
                <c:param name="username" value="${sessionScope.USER}"></c:param>
                <c:param name="action" value="ViewProfile"></c:param>
            </c:url>
            <a href="${ProfileLink}">${sessionScope.USER}</a>
            <c:url var="PrintCartLink" value="MainController">
                <c:param name="action" value="Print Cart"></c:param>
                <c:param name="username" value="${sessionScope.USER}"></c:param>
            </c:url>
            <a href = "${PrintCartLink}">View my cart</a>
            <c:url var="HistoryLink" value="MainController">
                <c:param name="action" value="ShowHistory"></c:param>
                <c:param name="username" value="${sessionScope.USER}"></c:param>
            </c:url>
            <a href = "${HistoryLink}">View buy history</a>
        </c:if>
        <c:if test="${!signedin}">
            <a class="active" href="login.jsp">Log in!</a>
        </c:if>
        <a href="PrintIndexController">Home</a>
    </div>

    <div class="login">
        <c:if test="${sessionScope.USER == null}" var="check">
            <h1>Login Page</h1>
            <form action="MainController" method="POST">
                <font color="red" size="2">${requestScope.INVALID.invalidLogin}</font>
                <br/>
                Username: <input type="text" name="txtUsername" value="${param.txtUsername}"/>
                <font color="red" size="2">${requestScope.INVALID.usernameErr}</font>
                <br/>
                Password: <input type="password" name="txtPassword"/>
                <font color="red" size="2">${requestScope.INVALID.passErr}</font>
                <br/>
                <input type="submit" name="action" value="Login"/>
            </form>
            <br/>
            <c:url var="InsertLink" value="MainController">
                <c:param name="action" value="AddUser"></c:param>
            </c:url>
            <a href="${InsertLink}">Create new account</a>
        </c:if>
        <c:if test="${!check}">
            <c:url var="PrintIndexLink" value="MainController">
                <c:param name="action" value="PrintIndex"></c:param>
            </c:url>
            <c:redirect url = "${PrintIndexLink}"/>
        </c:if>
    </div>
</body>
</html>