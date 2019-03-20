<%-- 
    Document   : insertTour
    Created on : Mar 3, 2019, 2:16:00 PM
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
        <h1>Edit Tour: ${requestScope.DTO.id}</h1>
        <form action="MainController" method="POST">
            Tour ID: <input type="text" name="txtId" value="${param.txtId}"/>
            <font color="red">${requestScope.INVALID.tourIdErr}</font>
            <br/>
            Tour Name: <input type="text" name="txtName" value="${param.txtName}"/>
            <font color="red">${requestScope.INVALID.tourNameErr}</font>
            <br/>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}"/>
            <font color="red">${requestScope.INVALID.priceErr}</font>
            <br/>
            Description: <input type="text" name="txtDesc" value="${param.txtDesc}"/>
            <font color="red">${requestScope.INVALID.descErr}</font>
            <br/>
            Starting date(yyyy-m-d): <input type="text" name="txtDate" value="${param.txtDate}"/>
            <font color="red">${requestScope.INVALID.dateErr}</font>
            <br/>
            Cancel before: <input type="number" name="txtCancel" value="${param.txtCancel}"/>(days)
            <font color="red">${requestScope.INVALID.cancelErr}</font>
            <br/>
            Category: <input type="text" name="txtCategory" value="${param.txtCategory}"/>
            <font color="red">${requestScope.INVALID.categoryErr}</font>
            <br/>
            Quantity: <input type="number" name="txtQtt" value="${param.txtQtt}"/>
            <font color="red">${requestScope.INVALID.qttErr}</font>
            <br/>
            Set status: 
            <select name="txtStt">
                <c:if test="${param.Stt=='inactive'} var='check'">
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
            <input type="submit" name="action" value="Insert Tour"/>
        </form>
    </body>
</html>
