CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(50) UNIQUE  NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    hash_password VARCHAR(255)        NOT NULL,
    role          VARCHAR(10)         NOT NULL CHECK (role IN ('admin', 'user'))
);

CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    price       DOUBLE PRECISION    NOT NULL,
    quantity    INT                 NOT NULL,
    image       bytea
);

INSERT INTO public.products (id, name, description, price, quantity, image)
VALUES
    (
     DEFAULT,
     'картошка',
     'молодая картошка',
     230.4,
     1000,
     null
    );

SELECT * FROM products;

CREATE TABLE category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE product_category
(
    product_id  INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

SELECT c.id, c.name FROM category c join product_category pc on c.id = pc.category_id WHERE pc.product_id = ?;

CREATE TABLE favorites
(
    id         SERIAL PRIMARY KEY,
    user_id    INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    user_id     INT         NOT NULL REFERENCES users (id),
    product_id  INT         NOT NULL REFERENCES products (id),
    order_date  TIMESTAMP   NOT NULL,
    status_code VARCHAR(50) NOT NULL
);