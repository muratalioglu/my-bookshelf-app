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