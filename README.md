# AMT

## Backlog
[https://nicolas-glassey.gitbook.io/amt-backlog/](https://nicolas-glassey.gitbook.io/amt-backlog/)

<br/>

## Connexions au serveur
### Tunnel ssh et tunnel pour la console d'administration
`ssh -L 23:10.0.1.21:22 -L 8081:10.0.1.21:8080 -L 4849:10.0.1.21:4848 [userName]@16.170.194.237 -i "[pathToDmzSshKey.pem]"`

### Connexion ssh
`ssh [userName]@localhost -p 23 -i "[pathToApplicationServerSshKey.pem]"`

</br>

## Interface web
**Application**
localhost:8081
**Console d'administration**
localhost:4849

</br>

## Installation 



