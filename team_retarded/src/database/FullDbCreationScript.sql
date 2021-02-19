# Drop old database
DROP DATABASE IF EXISTS Shop;
DROP DATABASE IF EXISTS testdb;

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 1;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 1;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

# Main database
CREATE SCHEMA IF NOT EXISTS Shop DEFAULT CHARACTER SET utf8;
USE Shop;


CREATE TABLE IF NOT EXISTS product_categories
(
    id   BIGINT       NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    category_id BIGINT,
    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(2000)  NOT NULL,
    price       DECIMAL(18, 2) NOT NULL,
    count       INT DEFAULT 0,
    status      INT DEFAULT 1,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS cart
(
    id     BIGINT NOT NULL AUTO_INCREMENT,
    status INT DEFAULT 1,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    login    VARCHAR(100) NOT NULL,
    password VARCHAR(60)  NOT NULL,
    name     VARCHAR(100),
    surname  VARCHAR(100),
    email    VARCHAR(100),
    cart_id  BIGINT       NOT NULL,
    status   INT DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS role
(
    id     BIGINT        NOT NULL AUTO_INCREMENT,
    name   VARCHAR(100)  NOT NULL,
    status INT DEFAULT 1 NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS roles
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS deliveries
(
    id     BIGINT         NOT NULL AUTO_INCREMENT,
    title  VARCHAR(100)   NOT NULL,
    region VARCHAR(100)   NOT NULL,
    price  DECIMAL(18, 2) NOT NULL,
    status INT DEFAULT 1,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS orders
(
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    cart_id        BIGINT,
    client_name    VARCHAR(100)   NOT NULL,
    client_surname VARCHAR(100)   NOT NULL,
    client_address VARCHAR(100)   NOT NULL,
    delivery_id    BIGINT,
    total_price    DECIMAL(18, 2) NOT NULL,
    user_id        BIGINT,
    status         INT DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
    FOREIGN KEY (`delivery_id`) REFERENCES `deliveries` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS products_in_cart
(
    id            BIGINT        NOT NULL AUTO_INCREMENT,
    cart_id       BIGINT        NOT NULL,
    product_id    BIGINT        NOT NULL,
    product_count INT DEFAULT 1 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

# Test Database

CREATE SCHEMA IF NOT EXISTS Testdb DEFAULT CHARACTER SET utf8;
USE Testdb;


CREATE TABLE IF NOT EXISTS product_categories
(
    id   BIGINT       NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    category_id BIGINT,
    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(2000)  NOT NULL,
    price       DECIMAL(18, 2) NOT NULL,
    count       INT DEFAULT 0,
    status      INT DEFAULT 1,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS cart
(
    id     BIGINT NOT NULL AUTO_INCREMENT,
    status INT DEFAULT 1,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    login    VARCHAR(100) NOT NULL,
    password VARCHAR(60)  NOT NULL,
    name     VARCHAR(100),
    surname  VARCHAR(100),
    email    VARCHAR(100),
    cart_id  BIGINT       NOT NULL,
    status   INT DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS role
(
    id     BIGINT        NOT NULL AUTO_INCREMENT,
    name   VARCHAR(100)  NOT NULL,
    status INT DEFAULT 1 NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS roles
(
    id      BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS deliveries
(
    id     BIGINT         NOT NULL AUTO_INCREMENT,
    title  VARCHAR(100)   NOT NULL,
    region VARCHAR(100)   NOT NULL,
    price  DECIMAL(18, 2) NOT NULL,
    status INT DEFAULT 1,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS orders
(
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    cart_id        BIGINT,
    client_name    VARCHAR(100)   NOT NULL,
    client_surname VARCHAR(100)   NOT NULL,
    client_address VARCHAR(100)   NOT NULL,
    delivery_id    BIGINT,
    total_price    DECIMAL(18, 2) NOT NULL,
    user_id        BIGINT,
    status         INT DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
    FOREIGN KEY (`delivery_id`) REFERENCES `deliveries` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS products_in_cart
(
    id            BIGINT        NOT NULL AUTO_INCREMENT,
    cart_id       BIGINT        NOT NULL,
    product_id    BIGINT        NOT NULL,
    product_count INT DEFAULT 1 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


USE Shop;

INSERT INTO shop.role (id, name, status)
VALUES (1, 'ROLE_USER', 1);

INSERT INTO shop.role (id, name, status)
VALUES (2, 'ROLE_ADMIN', 1);

INSERT INTO shop.cart (id, status)
VALUES (1, 1);

INSERT INTO shop.users (id, login, password, name, surname, email, cart_id, status)
VALUES (1, 'Admin123', '$2a$10$hQpGy3rV2kdlq5MVUERUCeNbNyz6EHRBzLFaYkjX43RF7M84y4grW', 'Admin123', 'Admin123',
        'admin@mail.com', 1, 1);

INSERT INTO shop.roles (id, user_id, role_id)
VALUES (1, 1, 1);

INSERT INTO shop.roles (id, user_id, role_id)
VALUES (2, 1, 2);

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;