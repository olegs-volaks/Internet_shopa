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
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    login     VARCHAR(100) NOT NULL,
    password  VARCHAR(100) NOT NULL,
    role      INT DEFAULT 1,
    basket_id BIGINT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS deliveries
(
    id     BIGINT         NOT NULL AUTO_INCREMENT,
    title  VARCHAR(100)   NOT NULL,
    region VARCHAR(100)   NOT NULL,
    price  DECIMAL(18, 2) NOT NULL,
    PRIMARY KEY (id)
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
    PRIMARY KEY (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    login     VARCHAR(100) NOT NULL,
    password  VARCHAR(100) NOT NULL,
    role      INT DEFAULT 1,
    basket_id BIGINT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS deliveries
(
    id     BIGINT         NOT NULL AUTO_INCREMENT,
    title  VARCHAR(100)   NOT NULL,
    region VARCHAR(100)   NOT NULL,
    price  DECIMAL(18, 2) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

USE Shop;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;