DROP DATABASE IF EXISTS `pecheur`;
CREATE DATABASE `pecheur`;

USE `pecheur`;

CREATE TABLE Account(
   id INT AUTO_INCREMENT,
   email VARCHAR(255),
   role VARCHAR(50),
   PRIMARY KEY(id)
);

CREATE TABLE Article(
   id INT AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   description VARCHAR(255) NOT NULL,
   quantity INT NOT NULL,
   price DOUBLE NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(description)
);

CREATE TABLE Image(
   id INT AUTO_INCREMENT,
   path VARCHAR(50) NOT NULL,
   id_Article INT,
   PRIMARY KEY(id),
   FOREIGN KEY(id_Article) REFERENCES Article(id)
);

CREATE TABLE Category(
   id INT AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(name)
);

CREATE TABLE isInCartOf(
   id_Account INT,
   id_Article INT,
   Quantity INT NOT NULL,
   PRIMARY KEY(id_Account, id_Article),
   FOREIGN KEY(id_Account) REFERENCES Account(id),
   FOREIGN KEY(id_Article) REFERENCES Article(id)
);

CREATE TABLE isDefineBy(
   id_Article INT,
   id_Category INT,
   PRIMARY KEY(id_Article, id_Category),
   FOREIGN KEY(id_Article) REFERENCES Article(id),
   FOREIGN KEY(id_Category) REFERENCES Category(id) ON DELETE CASCADE
);

ALTER TABLE `Account`;
INSERT INTO `Account` (`id`, `email`, `role`) VALUES
(1, 'test@test.ch', 'admin'),
(2, 'test2@test.ch', 'none');

ALTER TABLE `Article`;
INSERT INTO `Article` (`id`, `name`, `description`, `quantity`, `price`) VALUES
(1, 'Super Canne', 'Une bonne canne à pêcher les Pokémon.', 100, 100),
(2, 'Canne', 'Canne pour pêcher des Pokémon sauvages dans l\'eau.', 100, 10),
(3, 'Méga Canne', 'La meilleure canne à pêcher les Pokémon.', 100, 1000);

ALTER TABLE `Image`;
INSERT INTO `Image` (`id`, `path`, `id_article`) VALUES
(1, '/big/big1.jpg', 1),
(2, '/small/small1.jpg', 1);

ALTER TABLE `Category`;
INSERT INTO `Category` (`id`, `name`) VALUES
	(1, 'Canne'),
	(2, 'Veste');

ALTER TABLE `isDefineBy`;
INSERT INTO `isDefineBy` (`id_Article`, `id_Category`) VALUES
	(1, 1),
	(2, 1),
	(3, 1);

ALTER TABLE `isInCartOf`;
INSERT INTO `isInCartOf` (`id_Account`, `id_Article`, `Quantity`) VALUES
	(2, 2, 2);