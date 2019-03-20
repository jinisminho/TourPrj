<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>${requestScope.DTO.name}</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <style media="screen">
        .display{
            color: white;
            display: none;
        }
    </style>
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

        <div>
            <center><h1>${dto.name}</h1></center>

            <center>
                <img src="picture/${requestScope.DTO.id}-cover.jpg" alt="" style="width: auto;" onerror="this.style.display='none'">
            </center>
            
            <br>
            <center>
                <button type="button" name="button" onclick="showDivs(0)">DESCRITPTION</button>
                <button type="button" name="button" onclick="showDivs(1)">DETAILS</button>
                <button type="button" name="button" onclick="showDivs(2)">PHOTOS</button>
                <c:if test="${not empty sessionScope.USER}" var="check">
                    <c:url var="Add2CartLink" value="MainController">
                        <c:param name="action" value="Add to Cart"></c:param>
                        <c:param name="username" value="${sessionScope.USER}"></c:param>
                        <c:param name="tourId" value="${requestScope.DTO.id}"></c:param>
                        <c:param name="name" value="${requestScope.DTO.name}"></c:param>
                        <c:param name="price" value="${requestScope.DTO.price}"></c:param>
                    </c:url>
                    <button type="button" name="button" onclick="window.open('${Add2CartLink}')">ADD TO CART</button>
                </c:if>
                <c:if test="${!check}">
                    <button type="button" name="button" onclick="window.open('login.jsp')">ADD TO CART</button>
                </c:if>
            </center>
            <br>

            <div class="display" id="about">
                <h3>ABOUT THE TOUR</h3>
                <hr><br>
                <p>
                    ${requestScope.DTO.desc}
                </p>
            </div>

            <div class="display" id="specs">
                <center>
                    <font size="3">
                    <b>TOUR ID: </b> ${requestScope.DTO.id}<br/>
                    <b>TOUR NAME: </b>${requestScope.DTO.name}<br/>
                    <b>TOUR CATEGORY: </b>${requestScope.DTO.category}<br/>
                    <b>TOUR PRICE: </b>$${requestScope.DTO.price}<br/>
                    <b>GO DATE: </b>${requestScope.DTO.date}<br/>
                    <b>CANCEL BEFORE: </b>${requestScope.DTO.cancel} (days)<br/>
                    <b>TICKETS LEFT: </b>${requestScope.DTO.qtt}<br/>
                    </font>
                </center>
            </div>

            <div class="display" style="margin:auto;">
                <center>
                    <c:forEach var = "count" begin = "1" end = "20">
                        <img src="picture/${requestScope.DTO.id}-${count}.jpg" alt="" style="width: auto;" onerror="this.style.display='none'">
                    </c:forEach>
                </center>
            </div>

        </div>

    </body>

    <script>
        // var slideIndex = 1;
        // showDivs(slideIndex);
        //
        // function plusDivs(n) {
        //   showDivs(slideIndex += n);
        // }

        function showDivs(n) {
            var i;
            var x = document.getElementsByClassName("display");
            // if (n > x.length) {slideIndex = 1}
            // if (n < 1) {slideIndex = x.length}
            for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
            }
            x[n].style.display = "block";
        }
    </script>
</html>
