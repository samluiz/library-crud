USE bibliotecaifpi;

DROP TABLE IF EXISTS livros_didaticos;
DROP TABLE IF EXISTS livros_literarios;

CREATE TABLE `livros_didaticos` (
	`id` int NOT NULL AUTO_INCREMENT,
	`titulo` varchar(50) NOT NULL,
	`autor` varchar(50) NOT NULL,
	`editora` varchar(50) NOT NULL,
	`isbn` varchar(250) DEFAULT '',
	`assunto` ENUM('TECNOLOGIA', 'CIENCIA', 'QUIMICA', 'FISICA', 'MATEMATICA', 'OUTRO') DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `livros_literarios` (
	`id` int NOT NULL AUTO_INCREMENT,
	`titulo` varchar(50) NOT NULL,
	`autor` varchar(50) NOT NULL,
	`editora` varchar(50) NOT NULL,
	`isbn` varchar(250) DEFAULT '',
	`genero` ENUM('BIOGRAFIA', 'AVENTURA', 'ROMANCE', 'DRAMA', 'INFANTIL', 'TERROR', 'OUTRO') DEFAULT NULL,
	`classificacao_indicativa` ENUM('LIVRE', 'DEZ', 'DOZE', 'QUATORZE', 'DEZESSEIS', 'DEZOITO') DEFAULT NULL,
	PRIMARY KEY (`id`)
);
