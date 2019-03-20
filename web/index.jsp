<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Home</title>
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
        <c:if test="${empty requestScope.action}">
            <c:redirect url = "PrintIndexController"/>
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
                <a href="login.jsp">Log in!</a>
            </c:if>
            <a class="active" href="PrintIndexController">Home</a>
        </div>

        <c:import url="sideBar.jsp"/>

        <div class="content">
            <h1>Home Page</h1>

            <form action="MainController" method="POST">
                <font color="red" size="2">${requestScope.INVALID}</font>
                <br/>
                <input type="text" name="txtSearchTourName" value="${param.txtSearchTourName}" placeholder="Search tour by name"/>
                <input type="submit" value="Search Tour index" name="action"/>
            </form>

            <div class="sub-content">
                <c:if test="${requestScope.TINFO != null}">
                    <c:if test="${not empty requestScope.TINFO}" var="checkData">
                        <c:forEach var="dto" items="${requestScope.TINFO}" varStatus="counter">
                            <div class="box">
                                <c:url var="ItemLink" value="MainController">
                                    <c:param name="action" value="ViewItem"></c:param>
                                    <c:param name="tourId" value="${dto.id}"></c:param>
                                </c:url>
                                <a href="${ItemLink}" target="">
                                    <img src="picture/${dto.id}-cover.jpg" alt="">
                                    <h3>${dto.name}</h3>
                                    <h4>$${dto.price}</h4>
                                </a>

                                <form action="MainController" method="POST">
                                    <input type="hidden" name="username" value="${sessionScope.USER}"/>
                                    <input type="hidden" name="tourId" value="${dto.id}"/>
                                    <input type="hidden" name="name" value="${dto.name}"/>
                                    <input type="hidden" name="price" value="${dto.price}"/>
                                    <input type="submit" name="action" value="Add to Cart"/>
                                </form>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!checkData}">
                        <br/>
                        No Tour record found...
                    </c:if>
                </c:if>
            </div>

    </body>
</html>
