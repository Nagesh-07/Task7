# Task7
DBCRUDApp 

DATABASE Code

CREATE DATABASE bank_db;

USE bank_db;

CREATE TABLE accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    balance DOUBLE NOT NULL
);


# 🗄️ Java JDBC CRUD Application

A simple Java console application that connects to **MySQL/PostgreSQL** database and performs **CRUD (Create, Read, Update, Delete)** operations using **JDBC**.

---

## ✨ Features
- Connect to MySQL or PostgreSQL database
- Add new account records
- View all accounts
- Update account balance
- Delete accounts
- Uses **Connection, PreparedStatement, ResultSet**

---

## 🗂️ Files
- **DBCRUDApp.java** – Contains the code for database connection and CRUD operations
