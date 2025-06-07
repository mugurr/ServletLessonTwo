<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Каталог товаров</title>
    <style>
        /* Стили остаются без изменений */
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

        .content {
            display: flex;
            flex: 1;
        }

        .filter-section {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            padding-right: 20px;
            border-right: 1px solid #ddd;
        }

        .filter-section label {
            margin-bottom: 10px;
            cursor: pointer;
        }

        .products-section {
            flex: 3;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            overflow-y: auto;
            padding: 10px;
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

        .product-item .buttons {
            display: flex;
            gap: 10px;
            margin-top: auto;
        }

        .product-item .buttons button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 0.8rem;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        .product-item .buttons button:hover {
            background-color: #45a049;
        }

        .product-item .buttons button.remove {
            background-color: #f44336;
        }

        .product-item .buttons button.remove:hover {
            background-color: #d32f2f;
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
            font-size: 24px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: #555;
        }

        /* Новые стили для кнопки избранного в модальном окне */
        .modal-favorite-btn {
            margin-top: 15px;
            padding: 8px 16px;
            background-color: #ffc107;
            color: #333;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .modal-favorite-btn.added {
            background-color: #f44336;
            color: white;
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
            <button onclick="location.href='/favorites'">Избранное</button>
        </div>
    </div>

    <!-- Основной контент -->
    <div class="content">
        <div class="filter-section">
            <label>
                <input type="checkbox" class="category-filter" value="all" checked> Все категории
            </label><br/>
            <c:forEach var="category" items="${sessionScope.categories.categories}">
                <label>
                    <input type="checkbox" value="${category.name}" class="category-filter"> ${category.name}
                </label><br/>
            </c:forEach>
        </div>


        <!-- Правая часть: Список продуктов -->
        <div class="products-section">
            <c:forEach var="product" items="${sessionScope.products.products}">

                <c:set var="categoryNames" value="" />
                <c:forEach var="category" items="${product.category}">
                    <c:set var="categoryNames" value="${categoryNames} ${category.name}" />
                </c:forEach>

                <div class="product-item" data-categories="${categoryNames}">
                    <img src="data:image/jpeg;base64,${product.image}" alt="${product.name}">
                    <h3>${product.name}</h3>
                    <p>Цена: $${product.price}</p>
                    <h4 style="display:none;">Описание: ${product.description}</h4>
                    <div class="buttons">
                        <button onclick="showDetails(this)">Подробнее</button>
                        <form method="post" action="toggleProduct" style="display:inline;">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="isFavorite" id="isFavorite_${product.id}" value="${product.isFavorite}">
                            <button type="submit" data-favorite="${product.isFavorite}">
                                <c:choose>
                                    <c:when test="${product.isFavorite}">
                                        Удалить из избранного
                                    </c:when>
                                    <c:otherwise>
                                        Добавить в избранное
                                    </c:otherwise>
                                </c:choose>
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<!-- Модальное окно с кнопкой избранного -->
<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h3 id="modal-title"></h3>
        <p id="modal-description"></p>
        <button id="modal-favorite-btn" class="modal-favorite-btn" onclick="toggleFavoriteModal()"></button>
    </div>
</div>

<script>
    // Глобальные переменные для хранения текущего товара в модальном окне
    let currentProductId = null;
    let currentFavoriteStatus = false;

    // Функция для отображения модального окна
    function showDetails(button) {
        const productItem = button.parentElement.parentElement;
        const title = productItem.querySelector('h3').innerText;
        const description = productItem.querySelector('h4').innerText;
        const price = productItem.querySelector('p').innerText;

        // // Сохраняем ID товара и статус избранного
        // currentProductId = productItem.dataset.productId;
        // const favoriteBtn = productItem.querySelector('.favorite-btn');
        // currentFavoriteStatus = favoriteBtn.classList.contains('remove');

        // Заполняем модальное окно данными
        document.getElementById('modal-title').innerText = title;
        document.getElementById('modal-description').innerText = price + "\n" + description;

        // Обновляем кнопку в модальном окне
        const modalFavoriteBtn = document.getElementById('modal-favorite-btn');
        modalFavoriteBtn.innerText = currentFavoriteStatus ? 'Удалить из избранного' : 'Добавить в избранное';
        modalFavoriteBtn.className = currentFavoriteStatus ? 'modal-favorite-btn added' : 'modal-favorite-btn';

        // Показываем модальное окно
        document.getElementById('modal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('modal').style.display = 'none';
    }

    // Функция для переключения избранного из списка товаров
    function toggleFavorite(event, productId, isFavorite) {
        event.preventDefault();
        event.stopPropagation(); // Предотвращаем всплытие, чтобы не открывалось модальное окно

        fetch('/toggleProduct', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `productId=${productId}&isFavorite=${!isFavorite}`
        })
            .then(response => {
                if (response.ok) {
                    // Обновляем состояние кнопки
                    const button = event.target;
                    button.classList.toggle('remove');
                    button.innerText = button.classList.contains('remove') ? 'Удалить из избранного' : 'Добавить в избранное';

                    // Если это текущий товар в модальном окне, обновляем и его кнопку
                    if (currentProductId == productId) {
                        currentFavoriteStatus = !isFavorite;
                        const modalFavoriteBtn = document.getElementById('modal-favorite-btn');
                        modalFavoriteBtn.innerText = currentFavoriteStatus ? 'Удалить из избранного' : 'Добавить в избранное';
                        modalFavoriteBtn.className = currentFavoriteStatus ? 'modal-favorite-btn added' : 'modal-favorite-btn';
                    }
                }
            })
            .catch(error => console.error('Error:', error));
    }

    // Функция для переключения избранного из модального окна
    function toggleFavoriteModal() {
        const newStatus = !currentFavoriteStatus;

        fetch('/toggleProduct', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `productId=${currentProductId}&isFavorite=${newStatus}`
        })
            .then(response => {
                if (response.ok) {
                    // Обновляем состояние кнопки в модальном окне
                    currentFavoriteStatus = newStatus;
                    const modalFavoriteBtn = document.getElementById('modal-favorite-btn');
                    modalFavoriteBtn.innerText = newStatus ? 'Удалить из избранного' : 'Добавить в избранное';
                    modalFavoriteBtn.className = newStatus ? 'modal-favorite-btn added' : 'modal-favorite-btn';

                    // Обновляем кнопку в основном списке
                    const productItem = document.querySelector(`.product-item[data-product-id="${currentProductId}"]`);
                    if (productItem) {
                        const favoriteBtn = productItem.querySelector('.favorite-btn');
                        favoriteBtn.innerText = newStatus ? 'Удалить из избранного' : 'Добавить в избранное';
                        favoriteBtn.className = newStatus ? 'favorite-btn remove' : 'favorite-btn';
                    }
                }
            })
            .catch(error => console.error('Error:', error));
    }

    // Фильтр по категориям
    document.addEventListener('DOMContentLoaded', function() {
        const checkboxes = document.querySelectorAll('.category-filter');

        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
                const selectedCategories = Array.from(document.querySelectorAll('.category-filter:checked')).map(cb => cb.value);
                const products = document.querySelectorAll('.product-item');

                products.forEach(product => {
                    const productCategories = product.dataset.categories.split(' ');
                    const isVisible = selectedCategories.length === 0 ||
                        selectedCategories.includes('all') ||
                        selectedCategories.some(category => productCategories.includes(category));
                    product.style.display = isVisible ? 'flex' : 'none';
                });
            });
        });
    });
</script>

</body>
</html>