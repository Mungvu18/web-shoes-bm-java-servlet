<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="border: solid 1px">
    <tr>
        <td>Name</td>
        <td>Image</td>
        <td>Description</td>
        <td>Price</td>
        <td>Id_category</td>
        <td>Id_account</td>
    </tr>
    <c:forEach items="${products}" var="o">
        <tr>
            <td>${o.name}</td>
            <td><img src="${o.image}"></td>
            <td>${o.description}</td>
            <td>${o.price}</td>
            <td>${o.id_category}</td>
            <td>${o.id_account}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
