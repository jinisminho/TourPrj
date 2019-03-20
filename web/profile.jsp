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
            <a class="active" href="${ProfileLink}">${sessionScope.USER}</a>
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
            <c:if test="${!signedin}">
                <a class="active" href="login.jsp">Log in!</a>
            </c:if>
            <a href="PrintIndexController">Home</a>
        </c:if>
        <c:if test="${!signedin}">
            <a href="login.jsp">Log in!</a>
        </c:if>
    </div>

    <c:import url="sideBar.jsp"/>

    <div class="login">
        <h1>${sessionScope.USER}'s Profile</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="txtUsername" value="${sessionScope.USER}"/>
            Fullname: 
            <font color="red" size="2">${requestScope.INVALID.fullnameErr}</font>
            <input type="text" name="txtFullname" value="${requestScope.USER.fullname}"/>
            DoB (yyyy-m-d): 
            <font color="red" size="2">${requestScope.INVALID.dobErr}</font>
            <input type="text" name="txtDob" value="${requestScope.USER.dob}"/>

            <input type="submit" name="action" value="Update Profile"/>
        </form>
        <h2>
            <font color="red">${requestScope.UPDATED}</font>
        </h2>
        <br/>
    </div>
    <div class="login">
        <h2>Change password</h2>
        <form action="MainController" method="POST">
            <input type="hidden" name="txtFullname" value="${requestScope.USER.fullname}"/>
            <input type="hidden" name="txtDob" value="${requestScope.USER.dob}"/>
            <input type="hidden" name="txtUsername" value="${sessionScope.USER}"/>
            Old Password: 
            <font color="red" size="2">${requestScope.INVALID.oldPassMismatched}</font>
            <input type="password" name="txtOldPassword"/>
            New Password: 
            <font color="red" size="2">${requestScope.INVALID.passErr}</font>
            <input type="password" name="txtPassword"/>
            Confirm Password: 
            <font color="red" size="2">${requestScope.INVALID.passMismatched}</font>
            <input type="password" name="txtConfirm"/>
            <input type="submit" name="action" value="Change Password"/>
        </form
        <h2>
            <font color="red">${requestScope.PASSWORD}</font>
        </h2>
        <br/>
    </div>
</body>
</html>