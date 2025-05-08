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
            padding: 20px;
            color: #333;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            max-width: 1200px;
            margin: 0 auto;
        }

        .header {
            text-align: center;
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
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

        .products-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .products-table th, .products-table td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        .products-table th {
            background-color: #f2f2f2;
            position: sticky;
            top: 0;
        }

        .products-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .products-table tr:hover {
            background-color: #f1f1f1;
        }

        .product-image {
            max-width: 80px;
            max-height: 80px;
        }

        .action-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
        }

        .action-button:hover {
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
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: black;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <button onclick="location.href='/main'">На главную</button>
        <h1>Каталог товаров</h1>
        <button onclick="location.href='/favourites'">Избранное</button>
    </div>

    <table class="products-table">
        <thead>
        <tr>
            <th>Изображение</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${sessionScope.products.products}">
            <tr>
                <td>
                    <img src="data:image/jpeg;base64,${product.image}"
                         alt="${product.name}"
                         class="product-image">
                </td>
                <td>${product.name}</td>
                <td>$${product.price}</td>
                <td>
                    <button class="action-button"
                            onclick="showDetails(this)"
                            data-name="${product.name}"
                            data-price="$${product.price}"
                            data-description="${product.description}">
                        Подробнее
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h3 id="modal-title"></h3>
        <p id="modal-price"></p>
        <p id="modal-description"></p>
    </div>
</div>

<script>
    function showDetails(button) {
        const name = button.getAttribute('data-name');
        const price = button.getAttribute('data-price');
        const description = button.getAttribute('data-description');

        document.getElementById('modal-title').innerText = name;
        document.getElementById('modal-price').innerText = 'Цена: ' + price;
        document.getElementById('modal-description').innerText = 'Описание: ' + description;

        document.getElementById('modal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('modal').style.display = 'none';
    }

    // Закрытие модального окна при клике вне его
    window.onclick = function(event) {
        const modal = document.getElementById('modal');
        if (event.target == modal) {
            closeModal();
        }
    }
</script>

</body>
</html>