<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="cart.title"/></title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<h1><fmt:message key="cart.header"/></h1>

<c:choose>
    <c:when test="${empty cart}">
        <p><fmt:message key="cart.empty"/></p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th><fmt:message key="cart.product_name"/></th>
                <th><fmt:message key="cart.price"/></th>
                <th><fmt:message key="cart.quantity"/></th>
                <th><fmt:message key="cart.subtotal"/></th>
            </tr>
            
            <c:set var="total" value="0" />
            <c:forEach items="${cart}" var="entry">
                <c:set var="product" value="${entry.key}" />
                <c:set var="quantity" value="${entry.value}" />
                <c:set var="subtotal" value="${product.price * quantity}" />
                <c:set var="total" value="${total + subtotal}" />
                
                <tr>
                    <td>${product.name}</td>
                    <td><fmt:formatNumber value="${product.price}" type="currency" currencyCode="RUB"/></td>
                    <td>${quantity}</td>
                    <td><fmt:formatNumber value="${subtotal}" type="currency" currencyCode="RUB"/></td>
                </tr>
            </c:forEach>
            
            <tr>
                <td colspan="3"><strong><fmt:message key="cart.total"/></strong></td>
                <td><fmt:formatNumber value="${total}" type="currency" currencyCode="RUB"/></td>
            </tr>
        </table>
        
        <form method="post" action="checkout">
            <input type="submit" value="<fmt:message key='cart.checkout'/>">
        </form>
        
        <form method="post" action="clearCart">
            <input type="submit" value="<fmt:message key='cart.clear'/>">
        </form>
    </c:otherwise>
</c:choose>

<p><a href="products"><fmt:message key="cart.back_to_catalog"/></a></p>

</body>
</html>