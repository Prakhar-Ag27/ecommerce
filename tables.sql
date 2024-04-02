drop database if exists ecommerce;
create database ecommerce;
use ecommerce;

create table Users (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    sex BOOL,
    username VARCHAR(50),
    email VARCHAR(50)
);

create table Shopping_Cart (
    cartID INT PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE
);

create table product (
    productID INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    inventoryQuantity INT NOT NULL,
    price FLOAT(10, 2),
    descr TEXT
);

create table wallet (
	FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE,
    amount FLOAT(10, 2),
    currency VARCHAR(50)
);

create table orders (
    orderID INT PRIMARY KEY AUTO_INCREMENT,
    status Bool,
    totalAmount FLOAT(10, 2)
);

create table orderItem (
	orderItemID INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    FOREIGN KEY (orderID) REFERENCES orders(orderID) ON DELETE CASCADE, 
    FOREIGN KEY (productID) REFERENCES product(productID) ON DELETE CASCADE 
    # Why does Order_Items have User_ID ??
);

create table shoppingCart (
	shoppingCartID INT PRIMARY KEY AUTO_INCREMENT, # Is this necessary if we have the user ID specifying it already
    FOREIGN KEY (userID) REFERENCES users(userID) ON DELETE CASCADE
);

create table cartItems (
	cartItemID INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    FOREIGN KEY (shoppingCartID) REFERENCES shoppingCart(shoppingCartID) ON DELETE CASCADE,
    FOREIGN KEY (productID) REFERENCES product(productID) ON DELETE CASCADE
);