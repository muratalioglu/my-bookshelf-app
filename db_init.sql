DROP DATABASE IF EXISTS bookshelf;

CREATE DATABASE bookshelf;

CREATE TABLE bookshelf.book (
    id INT AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    author VARCHAR(255),
    isbn VARCHAR(255),
    language VARCHAR(255),
    publication_year INT,
    pages INT,
    delete_time TIMESTAMP,
    primary key(id)
);

CREATE TABLE bookshelf.member (
    id INT AUTO_INCREMENT,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    registration_time TIMESTAMP,
    password VARCHAR(255),
    primary key(id)
);

CREATE TABLE bookshelf.member_book (
    id INT AUTO_INCREMENT,
    member_id INT,
    book_id INT,
    status VARCHAR(255),
    current_page INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    delete_time TIMESTAMP,
    primary key(id)
);

CREATE TABLE bookshelf.role (
    id INT AUTO_INCREMENT,
    member_id INT,
    role VARCHAR(255),
    primary key(id)
);
