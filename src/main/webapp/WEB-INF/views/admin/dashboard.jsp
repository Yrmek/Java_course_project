<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="admin.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; }
        button { padding: 5px 10px; }
    </style>
</head>
<body>
    <h1><fmt:message key="admin.header"/></h1>
    <p>
        <p>
    <a href="${pageContext.request.contextPath}/logout"><fmt:message key="admin.logout"/></a> |
    <a href="${pageContext.request.contextPath}/admin/add-product"><fmt:message key="admin.add_product"/></a> |
    <a href="${pageContext.request.contextPath}/admin/orders"><fmt:message key="admin.manage_orders"/></a> |
    <a href="${pageContext.request.contextPath}/admin/report"><fmt:message key="admin.download_report"/></a>
</p>
    </p>

    <h2><fmt:message key="admin.users"/></h2>
    <c:choose>
        <c:when test="${empty users}">
            <p><fmt:message key="admin.no_users"/></p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th><fmt:message key="admin.id"/></th>
                        <th><fmt:message key="admin.email"/></th>
                        <th><fmt:message key="admin.role"/></th>
                        <th><fmt:message key="admin.status"/></th>
                        <th><fmt:message key="admin.action"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.blocked}">
                                        <fmt:message key="admin.blocked"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:message key="admin.active"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/admin/user-status">
                                    <input type="hidden" name="userId" value="${user.id}" />
                                    <input type="hidden" name="block" value="${!user.blocked}" />
                                    <button type="submit">
                                        <c:choose>
                                            <c:when test="${user.blocked}">
                                                <fmt:message key="admin.unblock"/>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message key="admin.block"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>