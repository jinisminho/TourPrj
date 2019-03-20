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
    <style>
        table { 
            display: table;
            border-collapse: separate;
            border-spacing: 24px;
            border-color: gray;
        }
    </style>
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
    <c:if test="${empty sessionScope.USER}">
        <c:redirect url = "login.jsp"/>
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
            <a class="active" href = "${HistoryLink}">View buy history</a>
        </c:if>
        <c:if test="${!signedin}">
            <a href="login.jsp">Log in!</a>
        </c:if>
        <a href="PrintIndexController">Home</a>
    </div>

    <c:import url="sideBar.jsp"/>

    <div class="login">
        <h1>PENDING TABLE</h1>
        <br/>
        <c:if test="${requestScope.PENDING != null}">
            <c:if test="${not empty requestScope.PENDING}" var="checkData">
                <table border="0">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Tour Name</th>
                            <th>Tour ID</th>
                            <th>Bought Date</th>
                            <th>Quantity</th>
                            <th>Price (1)</th>
                            <th>Cancel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.PENDING}" varStatus="counter">
                            <tr align="center">
                                <td>${counter.count}</td>
                                <td>${dto.tourName}</td>
                                <td>${dto.tourId}</td>
                                <td>${dto.buyDate}</td>
                                <td>${dto.buyQtt}</td>
                                <td>${dto.price}</td>
                                <td>
                                    <c:url var="CancelLink" value="MainController">
                                        <c:param name="action" value="Cancel Order"></c:param>
                                        <c:param name="billId" value="${dto.id}"></c:param>
                                        <c:param name="tourId" value="${dto.tourId}"></c:param>
                                        <c:param name="buyQtt" value="${dto.buyQtt}"></c:param>
                                        <c:param name="username" value="${sessionScope.USER}"></c:param>
                                    </c:url>
                                    <a href="${CancelLink}">Cancel Order</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!checkData}">
                No pending record found
            </c:if>
        </c:if>
    </div>

    <div class="login">
        <h1>SUCCESS TABLE</h1>
        <br/>
        <c:if test="${requestScope.SUCCESS != null}">
            <c:if test="${not empty requestScope.SUCCESS}" var="checkData">
                <table border="0">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Tour Name</th>
                            <th>Tour ID</th>
                            <th>Bought Date</th>
                            <th>Quantity</th>
                            <th>Price (1)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.SUCCESS}" varStatus="counter">
                            <tr align="center">
                                <td>${counter.count}</td>
                                <td>${dto.tourName}</td>
                                <td>${dto.tourId}</td>
                                <td>${dto.buyDate}</td>
                                <td>${dto.buyQtt}</td>
                                <td>${dto.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${!checkData}">
                No SUCCESS record found
            </c:if>
        </c:if>
    </div>
</body>
</html>