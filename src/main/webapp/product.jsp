<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>
        <c:choose>
            <c:when test="${not empty product}">
                <fmt:message key="product.title"/> - ${product.name}
            </c:when>
            <c:otherwise>
                <fmt:message key="product.not_found"/>
            </c:otherwise>
        </c:choose>
    </title>
</head>
<body>

    <div style="float: right;">
        <a href="?id=${param.id}&lang=en">EN</a> | 
        <a href="?id=${param.id}&lang=ru">RU</a>
    </div>

    <c:choose>
        <c:when test="${not empty product}">
            <h1><fmt:message key="product.details"/></h1>
            
            <table border="1">
                <tr>
                    <td><fmt:message key="product.name"/></td>
                    <td>${product.name}</td>
                </tr>
                <tr>
                    <td><fmt:message key="product.description"/></td>
                    <td>${product.description}</td>
                </tr>
                <tr>
                    <td><fmt:message key="product.price"/></td>
                    <td>
                        <fmt:formatNumber value="${product.price}" type="currency"/>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="product.stock"/></td>
                    <td>${product.stock}</td>
                </tr>
            </table>
            
            <form action="addToCart" method="post">
                <input type="hidden" name="productId" value="${product.id}">
                <label>
                    <fmt:message key="product.quantity"/>:
                    <input type="number" name="quantity" value="1" min="1" max="${product.stock}">
                </label>
                <button type="submit"><fmt:message key="product.add_to_cart"/></button>
            </form>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="product.not_found"/></h1>
            <p><fmt:message key="product.check_id"/></p>
        </c:otherwise>
    </c:choose>

    <p>
        <a href="products"><fmt:message key="product.back_to_catalog"/></a>
    </p>

</body>
</html>