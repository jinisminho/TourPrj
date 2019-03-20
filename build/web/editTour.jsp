<%-- 
    Document   : editTour
    Created on : Mar 2, 2019, 3:05:52 PM
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
        <form action="MainController" id="updateForm" method="POST">
            <input type="hidden" name="txtId" value="${requestScope.DTO.id}"/>
            Tour Name: <input type="text" name="txtName" value="${requestScope.DTO.name}"/>
            <font color="red">${requestScope.INVALID.tourNameErr}</font>
            <br/>
            Price: <input type="text" name="txtPrice" value="${requestScope.DTO.price}"/>
            <font color="red">${requestScope.INVALID.priceErr}</font>
            <br/>
            Description: 
            <br/>
            <textarea rows="4" cols="50" name="txtDesc" form="updateForm">${requestScope.DTO.desc}</textarea>
            <font color="red">${requestScope.INVALID.descErr}</font>
            <br/>
            Starting date(yyyy-m-d): <input type="text" name="txtDate" value="${requestScope.DTO.date}"/>
            <font color="red">${requestScope.INVALID.dateErr}</font>
            <br/>
            Cancel before: <input type="number" name="txtCancel" value="${requestScope.DTO.cancel}"/>(days)
            <font color="red">${requestScope.INVALID.cancelErr}</font>
            <br/>
            Category: <input type="text" name="txtCategory" value="${requestScope.DTO.category}"/>
            <font color="red">${requestScope.INVALID.categoryErr}</font>
            <br/>
            Quantity: <input type="number" name="txtQtt" value="${requestScope.DTO.qtt}"/>
            <font color="red">${requestScope.INVALID.qttErr}</font>
            <br/>
            Set status: 
            <select name="txtStt">
                <c:if test="${requestScope.DTO.stt=='inactive'}" var="check">
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
            <input type="hidden" name="txtSearchTourName" value="${requestScope.SEARCH}"/>
            <input type="submit" name="action" value="Update Tour"/>
        </form>
    </body>
</html>
