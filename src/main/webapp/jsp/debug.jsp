<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Debug Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            line-height: 1.6;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
        }
        .section {
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .section h2 {
            margin-top: 0;
            color: #333;
        }
        .property {
            margin-bottom: 10px;
        }
        .property-name {
            font-weight: bold;
            display: inline-block;
            width: 150px;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Debug Information</h1>

    <div class="section">
        <h2>Product Data</h2>

        <div class="property">
            <span class="property-name">Name:</span>
            <c:out value="${product.name}" default="[Not provided]"/>
        </div>

        <div class="property">
            <span class="property-name">Description:</span>
            <c:out value="${product.description}" default="[Not provided]"/>
        </div>

        <div class="property">
            <span class="property-name">Price:</span>
            <c:out value="${product.price}" default="[Not provided]"/>
        </div>

        <div class="property">
            <span class="property-name">Quantity:</span>
            <c:out value="${product.quantity}" default="[Not provided]"/>
        </div>

        <div class="property">
            <span class="property-name">Image size:</span>
            <c:choose>
                <c:when test="${empty product.image}">No image</c:when>
                <c:otherwise>${product.image.length} bytes</c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="section">
        <h2>Categories</h2>
        <c:choose>
            <c:when test="${empty categories}">
                <div class="error">No categories selected!</div>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${categories}" var="category">
                        <li><c:out value="${category.name}"/></li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="section">
        <h2>Session Attributes</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>Attribute Name</th>
                <th>Attribute Value</th>
            </tr>
            <c:forEach items="${sessionScope}" var="attr">
                <tr>
                    <td><c:out value="${attr.key}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${attr.key == 'products'}">
                                [Products list - size: ${attr.value.products.size()}]
                            </c:when>
                            <c:otherwise>
                                <c:out value="${attr.value}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="section">
        <h2>Request Parameters</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>Parameter Name</th>
                <th>Parameter Value</th>
            </tr>
            <c:forEach items="${paramValues}" var="parameter">
                <tr>
                    <td><c:out value="${parameter.key}"/></td>
                    <td>
                        <c:forEach items="${parameter.value}" var="value">
                            <c:out value="${value}"/><br>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="section">
        <a href="/admin/products">Back to Products</a>
    </div>
</div>
</body>
</html>