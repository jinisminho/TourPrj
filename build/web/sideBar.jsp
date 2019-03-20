<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.USER}" var="signedin">
    <c:url var="LogoutLink" value="MainController">
        <c:param name="action" value="Logout"></c:param>
    </c:url>
    <c:url var="ProfileLink" value="MainController">
        <c:param name="username" value="${sessionScope.USER}"></c:param>
        <c:param name="action" value="ViewProfile"></c:param>
    </c:url>
    <c:url var="PrintCartLink" value="MainController">
        <c:param name="action" value="Print Cart"></c:param>
        <c:param name="username" value="${sessionScope.USER}"></c:param>
    </c:url>
    <c:url var="HistoryLink" value="MainController">
        <c:param name="action" value="ShowHistory"></c:param>
        <c:param name="username" value="${sessionScope.USER}"></c:param>
    </c:url>
</c:if>
<div class="menu2" id="sh">
    <c:if test="${!signedin}">
        <a href="login.jsp" title="Log in"><i class="fas fa-sign-in-alt"></i></i></a>
        <a href="insertUser.jsp" title="Register"><i class="fas fa-user-plus"></i></a>
        </c:if>

    <c:if test="${signedin}">
        <a href="index.jsp" title="Home"><i class="fas fa-home"></i></a>
        <a href="${HistoryLink}" title="Order History"><i class="fas fa-file-invoice-dollar"></i></a>
        <a href="${PrintCartLink}" title="My Cart"><i class="fas fa-shopping-cart"></i></a>
        <a href="${ProfileLink}" title="My Profile"><i class="fas fa-user"></i></a>
        <a href="LogoutController" title="Log out"><i class="fas fa-sign-out-alt"></i></i></a>
    </c:if>
</div>