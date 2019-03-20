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

    <c:import url="sideBar.jsp"/>

    <div class="login">
        <h1>Create new account</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="txtUsername" value="${param.txtUsername}"/> 
            <font color="red" size="2">${requestScope.INVALID.usernameErr}</font>
            <br/>
            Password: <input type="password" name="txtPassword"/>
            <font color="red" size="2">${requestScope.INVALID.passErr}</font>
            <br/>
            Confirm Password: <input type="password" name="txtConfirm"/>
            <font color="red" size="2">${requestScope.INVALID.passMismatched}</font>
            <br/>
            Fullname: <input type="text" name="txtFullname" value="${param.txtFullname}"/>
            <font color="red" size="2">${requestScope.INVALID.fullnameErr}</font>
            <br/>
            Birthday(yyyy-m-d): <input type="text" name="txtDob" value="${param.txtDob}"/>
            <font color="red" size="2">${requestScope.INVALID.dobErr}</font>
            <br/>
            <input type="submit" name="action" value="Insert User"/>
        </form>
    </div>
</body>
</html>