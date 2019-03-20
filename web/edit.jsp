<%-- 
    Document   : edit
    Created on : Mar 2, 2019, 10:58:36 AM
    Author     : Hoang Pham
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit User: ${requestScope.DTO.username}</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="txtUsername" value="${requestScope.DTO.username}"/>
            Full name: <input type="text" name="txtFullname" value="${requestScope.DTO.fullname}"/>
            <font color="red">${requestScope.INVALID.fullnameErr}</font>
            <br/>
            Date of Birth(yyyy-m-d): <input type="text" name="txtDob" value="${requestScope.DTO.dob}"/>
            <font color="red">${requestScope.INVALID.dobErr}</font>
            <br/>
            Set status: 
            <select name="txtStt">
                <c:if test="${requestScope.DTO.stt=='inactive'} var='check'">
                    <option value="active">Active</option>
                    <option value="inactive" selected>Inactive</option>
                </c:if>
                <c:if test="${!check}">
                    <option value="active" selected>Active</option>
                    <option value="inactive">Inactive</option>
                </c:if>
            </select>
            <font color="red">${requestScope.INVALID.sttErr}</font>
            <br/>
            <input type="hidden" name="txtSearchUsername" value="${requestScope.SEARCH}"/>
            <input type="submit" name="action" value="Update"/>
        </form>
    </body>
</html>
