<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewpoint" content="width=device-width, initial-scale=1.0">
    <title>Каталог товаров</title>
    <style>
        body {
            background-image: url('../image/background.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 1200px;
            height: 650px;
            display: flex;
            flex-direction: column;
            position: relative;
        }

        .header {
            text-align: center;
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header .left-buttons,
        .header .right-buttons {
            display: flex;
            gap: 10px;
        }

        .header button {
            background-color: #2196F3;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .header button:hover {
            background-color: #1976D2;
        }

        .products-section {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            overflow-y: auto;
            padding: 20px;
            justify-content: center;
        }

        .product-item {
            flex: 0 0 calc(33.33% - 20px);
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            box-sizing: border-box;
            height: calc(50% - 20px);
            justify-content: space-between;
        }

        .product-item img {
            max-width: 80px;
            max-height: 80px;
            margin-bottom: 10px;
        }

        .product-item h3 {
            font-size: 1rem;
            color: #333;
            margin-bottom: 10px;
        }

        .product-item p {
            font-size: 0.9rem;
            color: #555;
            margin-bottom: 10px;
        }

        .product-item .buttons button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 0.8rem;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 10px;
        }

        .product-item .buttons button:hover {
            background-color: #45a049;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            background-color: white;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            position: relative;
        }

        .close {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: #555;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <div class="left-buttons">
            <button onclick="location.href='/main'">На главную</button>
        </div>
        <h1>Каталог товаров</h1>
        <div class="right-buttons">
            <button onclick="location.href='/favourites'">Избранное</button>
        </div>
    </div>

    <div class="products-section">
        <c:forEach var="product" items="${sessionScope.products.products}">
            <div class="product-item">
                <img src="data:image/jpeg;base64,${product.image}" alt="${product.name}">
                <h3>${product.name}</h3>
                <p>Цена: $${product.price}</p>
                <h4 style="display: none;">Описание: ${product.description}</h4>
                <div class="buttons">
                    <button onclick="showDetails(this)">Подробнее</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h3 id="modal-title"></h3>
        <p id="modal-description"></p>
    </div>
</div>

<script>
    function showDetails(button) {
        const productItem = button.parentElement.parentElement;
        const title = productItem.querySelector('h3').innerText;
        const description = productItem.querySelector('h4').innerText;
        const price = productItem.querySelector('p').innerText;

        document.getElementById('modal-title').innerText = title;
        document.getElementById('modal-description').innerText = price + "\n" + description;
        document.getElementById('modal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('modal').style.display = 'none';
    }
</script>

</body>
</html>