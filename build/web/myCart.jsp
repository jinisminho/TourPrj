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
            <a class="active" href = "${PrintCartLink}">View my cart</a>
            <c:url var="HistoryLink" value="MainController">
                <c:param name="action" value="ShowHistory"></c:param>
                <c:param name="username" value="${sessionScope.USER}"></c:param>
            </c:url>
            <a href = "${HistoryLink}">View buy history</a>
        </c:if>
        <c:if test="${!signedin}">
            <a href="login.jsp">Log in!</a>
        </c:if>
        <a href="PrintIndexController">Home</a>
    </div>

    <div class="menu2" id="sh">
        <a href="home.html"><i class="fas fa-home"></i></a>
        <a href="game.html"><i class="fas fa-gamepad"></i></a>
        <a href="photo.html"><span class="glyphicon"><i class="fas fa-camera-retro"></i></span></a>
        <a href="Feedback.html"><i class="far fa-comment-alt"></i></a>
        <a href="login.html"><i class="fas fa-sign-in-alt"></i></i></a>
        <a href="signup.html"><i class="fas fa-user-plus"></i></a>
    </div>

    <h1></h1>

    <div class="login">
        <h1>${sessionScope.USER}'s Cart</h1>
        <c:if test="${requestScope.CART != null}">
            <c:if test="${not empty requestScope.CART}" var="checkData">
                TOTAL: ${requestScope.TOTAL} USD
                <form action="MainController" method="POST">
                    <table border="0">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Tour ID</th>
                                <th>Tour Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Info</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${requestScope.CART}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.tour.id}<input type="hidden" name="ids" value="${dto.tour.id}"/></td>
                                    <td>${dto.tour.name}<input type="hidden" name="names" value="${dto.tour.name}"/></td>
                                    <td><input type="number" name="buyQtts" value="${dto.tour.buyQtt}"/></td>
                                    <td>${dto.tour.price}<input type="hidden" name="prices" value="${dto.tour.price}"/></td>
                                    <td>
                                        <c:url var="ItemLink" value="MainController">
                                            <c:param name="action" value="ViewItem"></c:param>
                                            <c:param name="tourId" value="${dto.tour.id}"></c:param>
                                        </c:url>
                                        <a href="${ItemLink}">View</a>
                                    </td>
                                    <td>
                                        <c:url var="RemoveFromCartLink" value="MainController">
                                            <c:param name="action" value="RemoveFromCart"></c:param>
                                            <c:param name="username" value="${sessionScope.USER}"></c:param>
                                            <c:param name="tourId" value="${dto.tour.id}"></c:param>
                                        </c:url>
                                        <a href="${RemoveFromCartLink}">Remove</a>
                                    </td>
                                </tr>
                            </c:forEach>                        
                        </tbody>
                    </table>
                    <input type="hidden" name="username" value="${sessionScope.USER}"/>
                    <input type="submit" name="action" value="Update Cart"/>
                    <br/>
                    <input type="submit" name="action" value="Buy"/>
                </form>
            </c:if>
            <c:if test="${!checkData}">
                <br/>
                Your cart is empty... Add something to your cart?!
            </c:if>
            <a href="PrintTourListController">Back to index</a>
        </c:if>
    </div>
</body>
</html>