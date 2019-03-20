<%-- 
    Document   : admin
    Created on : Feb 28, 2019, 10:19:01 PM
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
        <c:if test="${sessionScope.USER ne 'admin'}">
            <c:redirect url = "login.jsp"/>
        </c:if>
        <h1>Admin Page</h1>
        Welcome ${sessionScope.USER}!
        <br/>
        <c:url var="LogoutLink" value="MainController">
            <c:param name="action" value="Logout"></c:param>
        </c:url>
        <a href="${LogoutLink}">Logout</a>
        <br/>
        <form action="MainController" method="POST">
            <font color="red">${requestScope.INVALID}</font>
            <br/>
            <input type="text" name="txtSearchTourName" value="${param.txtSearchTourName}" placeholder="Search Tour Name"/>
            <input type="submit" value="Search Tour" name="action"/>
        </form>

        <form action="MainController" method="POST">
            <font color="red">${requestScope.INVALID}</font>
            <br/>
            <input type="text" name="txtSearchUsername" value="${param.txtSearchUsername}" placeholder="Search Username"/>
            <input type="submit" value="Search User" name="action"/>
        </form>

        <form action="MainController" method="POST">
            <br/>
            <input type="submit" value="User List" name="action"/>
            <input type="submit" value="Tour List" name="action"/>
        </form>

        <c:if test="${requestScope.INFO != null}">
            <c:if test="${not empty requestScope.INFO}" var="checkData">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Fullname</th>
                            <th>Date of Birth(YYYY-MM-dd)</th>
                            <th>Status</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.INFO}" varStatus="counter">
                            <tr align="center">
                                <td>${counter.count}</td>
                                <td>${dto.username}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.dob}</td>
                                <td>${dto.stt}</td>
                                <td>
                                    <c:url var="EditLink" value="MainController">
                                        <c:param name="action" value="Edit"></c:param>
                                        <c:param name="username" value="${dto.username}"></c:param>
                                        <c:param name="fullname" value="${dto.fullname}"></c:param>
                                        <c:param name="dob" value="${dto.dob}"></c:param>
                                        <c:param name="stt" value="${dto.stt}"></c:param>
                                        <c:param name="searchValue" value="${param.txtSearchUsername}"></c:param>
                                    </c:url>
                                    <a href="${EditLink}">Edit</a>
                                </td>
                                <td>
                                    <c:url var="DeleteLink" value="MainController">
                                        <c:param name="action" value="Delete"></c:param>
                                        <c:param name="username" value="${dto.username}"></c:param>
                                    </c:url>
                                    <a href="${DeleteLink}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </c:if>
            <c:if test="${!checkData}">
                No User record found
            </c:if>
        </c:if>

        <c:if test="${requestScope.TINFO != null}">
            <c:if test="${not empty requestScope.TINFO}" var="checkData">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Tour ID</th>
                            <th>Tour Name</th>
                            <th>Price</th>
                            <th>Date(YYYY-MM-dd)</th>
                            <th>Cancel Day</th>
                            <th>Category</th>
                            <th>Quantity</th>
                            <th>Status</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.TINFO}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.id}</td>
                                <td>${dto.name}</td>
                                <td>${dto.price}</td>
                                <td>${dto.date}</td>
                                <td>${dto.cancel}</td>
                                <td>${dto.category}</td>
                                <td>${dto.qtt}</td>
                                <td>${dto.stt}</td>
                                <td>
                                    <c:url var="EditLink" value="MainController">
                                        <c:param name="action" value="EditTour"></c:param>
                                        <c:param name="id" value="${dto.id}"></c:param>
                                        <c:param name="name" value="${dto.name}"></c:param>
                                        <c:param name="price" value="${dto.price}"></c:param>
                                        <c:param name="desc" value="${dto.desc}"></c:param>
                                        <c:param name="date" value="${dto.date}"></c:param>
                                        <c:param name="cancel" value="${dto.cancel}"></c:param>
                                        <c:param name="category" value="${dto.category}"></c:param>
                                        <c:param name="qtt" value="${dto.qtt}"></c:param>
                                        <c:param name="stt" value="${dto.stt}"></c:param>
                                        <c:param name="searchValue" value="${param.txtSearchTourName}"></c:param>
                                    </c:url>
                                    <a href="${EditLink}">Edit</a>
                                </td>
                                <td>
                                    <c:url var="DeleteLink" value="MainController">
                                        <c:param name="action" value="DeleteTour"></c:param>
                                        <c:param name="id" value="${dto.id}"></c:param>
                                    </c:url>
                                    <a href="${DeleteLink}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                    <c:url var="InsertLink" value="MainController">
                        <c:param name="action" value="AddTour"></c:param>
                    </c:url>
                    <a href="${InsertLink}">Add new Tour</a>
                    <br/>
                    <c:url var="FolderLink" value="MainController">
                        <c:param name="action" value="Open Photo Folder"></c:param>
                    </c:url>
                    <a href="${FolderLink}">Open Tour Picture Folder</a>
                </table>
            </c:if>
            <c:if test="${!checkData}">
                <c:url var="InsertLink" value="MainController">
                    <c:param name="action" value="AddTour"></c:param>
                </c:url>
                <a href="${InsertLink}">Add new Tour</a> 
                <br/>
                No Tour record found
            </c:if>
        </c:if>
    </body>
</html>
