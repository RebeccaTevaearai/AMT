CREATE TABLE Account(
   id INT AUTO_INCREMENT,
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
