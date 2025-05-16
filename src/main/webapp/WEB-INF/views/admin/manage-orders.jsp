<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang.language : 'ru'}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="admin.orders.title"/></title>
</head>
<body>
<h1><fmt:message key="admin.orders.header"/></h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th><fmt:message key="user.email"/></th>
        <th><fmt:message key="order.createdAt"/></th>
        <th><fmt:message key="order.total"/></th>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="actions"/></th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.email}</td>
            <td>${order.createdAt}</td>
            <td>${order.totalPrice} руб.</td>
            <td>
                <c:choose>
                    <c:when test="${order.paid}">
                        Оплачен
                    </c:when>
                    <c:otherwise>
                        Не оплачен
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form method="post" action="update-order">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="hidden" name="userId" value="${order.userId}">
                    <input type="hidden" name="paid" value="${!order.paid}">
                    <button type="submit">
                        <c:choose>
                            <c:when test="${order.paid}">
                                Пометить как не оплачен
                            </c:when>
                            <c:otherwise>
                                Пометить как оплачен
                            </c:otherwise>
                        </c:choose>
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/admin">
    <fmt:message key="add_product.back"/>
</a></p>
</body>
</html>
