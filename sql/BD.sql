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
   FOREIGN KEY(id_Category) REFERENCES Category(id)
);

-- Listage des données de la table pecheur.account : ~0 rows (environ)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `email`, `role`) VALUES
(1, 'test@test.ch', 'admin'),
(2, 'test2@test.ch', 'none');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Listage des données de la table pecheur.article : ~0 rows (environ)
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` (`id`, `name`, `description`, `quantity`, `price`) VALUES
(1, 'Super Canne', 'Une bonne canne à pêcher les Pokémon.', 100, 100),
(2, 'Canne', 'Canne pour pêcher des Pokémon sauvages dans l\'eau.', 100, 10),
	(3, 'Méga Canne', 'La meilleure canne à pêcher les Pokémon.', 100, 1000);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;

-- Listage des données de la table pecheur.category : ~0 rows (environ)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `name`) VALUES
	(1, 'Canne');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Listage des données de la table pecheur.isdefineby : ~0 rows (environ)
/*!40000 ALTER TABLE `isdefineby` DISABLE KEYS */;
INSERT INTO `isdefineby` (`id_Article`, `id_Category`) VALUES
	(1, 1),
	(2, 1),
	(3, 1);
/*!40000 ALTER TABLE `isdefineby` ENABLE KEYS */;

-- Listage des données de la table pecheur.isincartof : ~0 rows (environ)
/*!40000 ALTER TABLE `isincartof` DISABLE KEYS */;
INSERT INTO `isincartof` (`id_Account`, `id_Article`, `Quantity`) VALUES
	(2, 2, 2);