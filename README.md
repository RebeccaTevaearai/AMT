[![Total alerts](https://img.shields.io/lgtm/alerts/g/RebeccaTevaearai/AMT.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/RebeccaTevaearai/AMT/alerts/)
[![Language grade: JavaScript](https://img.shields.io/lgtm/grade/javascript/g/RebeccaTevaearai/AMT.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/RebeccaTevaearai/AMT/context:javascript)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/RebeccaTevaearai/AMT.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/RebeccaTevaearai/AMT/context:java)

# AMT 2021-22
## Introduction <br/>
ToDo introduction

<img alt="app img" src="images/app.png" height="400" />

<br/>

## Backlog
https://cloud.icescrum.com/p/PECHEUR/#/backlog/all/story/

<br/>

## Connexions au serveur AWS
### Tunnel ssh et tunnel pour la console d'administration
`ssh -L 23:10.0.1.21:22 -L 8081:10.0.1.21:8080 -L 4849:10.0.1.21:4848 [userName]@16.170.194.237 -i "[pathToDmzSshKey.pem]"`

### Connexion ssh
`ssh [userName]@localhost -p 23 -i "[pathToApplicationServerSshKey.pem]"`

</br>

## Developpement local
### Serveur d'application
Tomcat v9 (https://tomcat.apache.org/download-90.cgi)

**Integration avec IntelliJ**
https://www.jetbrains.com/help/idea/configuring-and-managing-application-server-integration.html

**Configuration de la console d'administration**
Modifier le fichier `<path>/tomcat/apache-tomcat-9.0.54/conf/tomcat-users.xml` </br>
- Décommenter les rôles du manager </br>
- Aouter un mot de passe pour chaque rôle </br>
````xml
  <user username="admin" password="admin" roles="manager-gui"/>
  <user username="robot" password="robot" roles="manager-script"/>
````

**Interfaces web**
- Application: localhost:8080
- Console admin: localhost:8080/manager/html
  - login: admin:admin  

<br/>

### Bases de donnée
MariaDB (https://mariadb.org/download)

Le fichier sql de la base de donnée est sql/DB.sql <br/>

Il faut modifier les paramêtre de connexion (user, mdp) en haut du fichier app/src/main/java/service/DatabaseConnection.java

<br/>

### Javadoc  <br/>
Génération de la javadoc: <br/>
`mvn javadoc:javadoc` <br/>
Le dossier se trouve dans `/target/site/apidocs`

<br/>

### Code coverage <br/>
génération du rapport de code coverage (jacoco) <br/>
`mvn jacoco:report` <br/>
Le rapport se trouve dans `/target/site/apidocs/jacoco` <br/>

<br/>

## MicroService Authentification

https://github.com/RebeccaTevaearai/AuthService

<br/>
 
## MCD
<img src="images/MCD.png" height="200" />
