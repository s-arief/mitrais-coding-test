# Description

Mitrais Coding Test : Arief Suharsono

## Stack Used
* Backend : Java (Spring), Hibernate ORM
* Frontend : HTML, Bootstrap, jQuery
* Database : MySQL

## Prerequisite Before Installation
* Java 8 Development Kit Installed
* Maven 3.6.0 Installed
* MySQL Server Installed
* Internet Connection

## Installation: Backend

* Prepare **empty** database with user and password access
* Go To Backend Folder
* Copy configuration example in src/main/resources/application.properties.example to src/main/resources/application.properties
* Edit configuration in src/main/resources/application.properties
* Install Java Dependencies And Build Project

```
mvn install
```
* Run Server

```
java -jar ./target/mitraiscodingtest-1.0-SNAPSHOT.jar
```

## Testing : Backend

* Prepare **empty** database with user and password access (use separate database for testing purpose, don't use same database for running purpose)
* Go To Backend Folder
* Copy configuration example in src/test/resources/application.properties.example to src/test/resources/application.properties
* Edit configuration in src/test/resources/application.properties
* Run Test

```
mvn test
```
## Installation: Frontend

* Go To Frontend Folder
* Copy configuration example in config.js.example to config.js
* Edit configuration in config.js
