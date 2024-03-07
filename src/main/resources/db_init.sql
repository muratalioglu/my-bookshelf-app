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

CREATE TABLE bookshelf.member_role (
    id INT AUTO_INCREMENT,
    member_id INT,
    role_id INT,
    primary key(id)
);

INSERT INTO bookshelf.book
VALUES
	(NULL,'Bir Bilim Adamının Romanı',NULL,'Oğuz Atay','9789754700671','tr',NULL,'283',NULL),
	(NULL,'Kırmızı Pazartesi',NULL,'Gabriel Garcia Marquez','9789750721571','tr',NULL,'120',NULL),
	(NULL,'Yaşar Ne Yaşar Ne Yaşamaz',NULL,'Aziz Nesin','9789759038472','tr',NULL,'343',NULL);
;

INSERT INTO bookshelf.member_book
VALUES
    ('1','1','1',NULL,NULL,'2024-03-07 09:07:52','0',NULL),
    ('2','1','2',NULL,NULL,'2024-03-07 09:07:52','0',NULL)
;

INSERT INTO bookshelf.member
VALUES
    (NULL, 'admin@mybookshelf', 'Murat Deniz', 'Alioglu', '2023-01-01 00:00:00', '$2a$10$he9ZDpaPdLhdIlI4vk8qQOtC4JNl0QPD0FqbzKUMa.cxXgLtRGSRe')
;

INSERT INTO bookshelf.member_role
VALUES
    (NULL,1,1)
;

USE bookshelf;