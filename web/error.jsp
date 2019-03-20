<%--
    Document   : error
    Created on : Mar 2, 2019, 11:33:01 AM
    Author     : Hoang Pham
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                text-align: right;
            }
        </style>
    </head>
    <body background="picture/butt.gif">
    <center>
        <h1>
            <font color="red" size="40" style="bold">
            Sorry, something went wrong :(
            </font>
        </h1>
        <c:if test="${not empty requestScope.ERROR}">
            Error: ${requestScope.ERROR}
        </c:if>
        <br/>
        <c:if test="${not empty requestScope.OOO}">
            Sorry, but the following tours are out of order:
            <br/>
            <c:forEach var="dto" items="${requestScope.OOO}">
                <font color="red">${dto.id} - ${dto.name}</font>
                <br/>
            </c:forEach>
        </c:if>
        <br/>
        <br/>
        <a href="index.jsp">Back to Index</a>
    </center>
    <footer>
        <p>hotline: 0908 123 4567 (Mr. Hoàn)</p>
        <p>email: asdf123@hello.com</p>
    </footer>
</body>
</html>
