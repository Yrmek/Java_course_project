<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="catalog.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .product-card { border: 1px solid #ddd; padding: 10px; margin-bottom: 10px; }
        .product-name { font-weight: bold; font-size: 18px; }
        form { margin-top: 10px; }
    </style>
</head>
<body>

<h1><fmt:message key="catalog.title"/></h1>
<p>
    <a href="logout"><fmt:message key="catalog.logout"/></a> | 
    <a href="cart"><fmt:message key="catalog.cart"/></a>
</p>

<c:choose>
    <c:when test="${empty products}">
        <p><fmt:message key="catalog.empty"/></p>
    </c:when>
    <c:otherwise>
        <c:forEach items="${products}" var="p">
            <div class="product-card">
                <div class="product-name">${p.name}</div>
                <div><fmt:message key="catalog.description"/>: ${p.description}</div>
                <div><fmt:message key="catalog.price"/>: 
                    <fmt:formatNumber value="${p.price}" type="currency" currencyCode="RUB"/>
                </div>
                <div><fmt:message key="catalog.stock"/>: 
                    <fmt:formatNumber value="${p.stock}"/> <fmt:message key="catalog.units"/>
                </div>
                
                <form method="post" action="addToCart">
                    <input type="hidden" name="productId" value="${p.id}">
                    <input type="number" name="quantity" value="1" min="1" max="${p.stock}">
                    <input type="submit" value="<fmt:message key='catalog.add_to_cart'/>">
                </form>
                
                <a href="product?id=${p.id}"><fmt:message key="catalog.details"/></a>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>