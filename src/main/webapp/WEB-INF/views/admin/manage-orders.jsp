<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
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
    <%
        List<Map<String, Object>> orders = (List<Map<String, Object>>) request.getAttribute("orders");
        for (Map<String, Object> order : orders) {
    %>
    <tr>
        <td><%= order.get("id") %></td>
        <td><%= order.get("email") %></td>
        <td><%= order.get("createdAt") %></td>
        <td><%= order.get("totalPrice") %> руб.</td>
        <td><%= (Boolean) order.get("paid") ? "Оплачен" : "Не оплачен" %></td>
        <td>
            <form method="post" action="update-order">
                <input type="hidden" name="orderId" value="<%= order.get("id") %>">
                <input type="hidden" name="userId" value="<%= order.get("userId") %>">
                <input type="hidden" name="paid" value="<%= !(Boolean) order.get("paid") %>">
                <button type="submit">
                    <%= (Boolean) order.get("paid") ? "Пометить как не оплачен" : "Пометить как оплачен" %>
                </button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

<p><a href="${pageContext.request.contextPath}/admin">
    <fmt:message key="add_product.back"/>
</a></p>
</body>
</html>
