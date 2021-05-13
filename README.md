# Problem
Supposed you are working in Marvel and you need to keep updated the writers, editors and colorists list who have been working with the followings Heroes: "Captain America" and "Iron Man" also you want to know how many interactions they have with anothers heros.  
This list need to be updated every day because we want to pay royalties to the workers.  

Create two Rest Services:  
* One service to get the writers, editors and colorists involved in the captain America and Iron Man Comics.  
* A second service to get the hero list related to these heroes in their Comics.  

You can use the Marvel Studios API https://developer.marvel.com to get all the information you want.  

## Solution
![Texto alternativo](img/diagrama.jpeg?Raw=true "Solución")

## Two Components in the project:
* ConsumeApiFillDataBase
* RestApi


The first service called **ConsumeApiFillDataBase** will persist the data to a MySql database from this page every 24 hrs. 
[Source](https://developer.marvel.com/)

Here before persist the data we need to get the relevant data (check the tables below)
 the relevant data from the response are the fields "creators" and  "characters". 

## DataBases
These are the tables:

![](img/sql.PNG?Raw=true "MySql ")

**Technologies**
* Java 8 y Maven
* Intellij Idea, Windows 10 Home
* Plugin Lombok Intellij Idea
* [Docker and Docker Compose(docker tool box)](https://docs.docker.com/toolbox/toolbox_install_windows/) for Windows 10 Home
* [Spring Initialzr](https://start.spring.io/) Used in the RestApi project.
* MySql
* Java 8, Spring Boot 2, Maven, Spring Data, Spring Data, Lombok, Docker Compose, Slf4j and Swagger  

### Settings for Windows users  
First clone the  repository, make sure to install **docker-compose** before.  
Execute the .bat file called **assemble.bat**  and wait to be up.  

If the docker services are up execute **docker ps** and you will see 3 services:  
![](img/servicios.PNG?Raw=true "Servicos ")

### Settings for non-windows users 

First clone the  repository and from command-line execute the following command, make sure to install **docker-compose** before.  
Execute the next command to run MySQL service  (in the root folder):  

```
docker-compose up
```

From command-line go to the folder **ConsumeApiFillDataBase** and execute the command:  
```
docker build --tag=api-store-bd-m:1.0 --rm=true .
```

Here there is another file called docker-compose.yml, execute the command to run the service.  
```
docker-compose up
```

From command-line go to the folder  **RestApi**  and execute the comman:
```
docker build --tag=api-rest-m:1.0 --rm=true .
```
And

```
docker-compose up
```

### Settings user without Docker
The services are basically services done in Java with Maven, so if you don´t want to use Docker, just run the projects  but you need to create a connection with MySql using JDBC in each project and after that you need to execute the script **init.sql** . 
From command-line go to the folder **Target**  in each project and execute the command :  
```
java -jar filldataBase.jar ||  java -jar RestApi-1.0.jar
```

Swagger documentation for the project "RestApi":  

### Swagger  
With  docker make sure you are using the  url to access the containers:  
Here are the endpoints: 
Without Docker  
http://localhost:80/swagger-ui.html  
With Docker  
192.168.99.100:80/swagger-ui.html  
![](img/swagger.PNG?Raw=true "Solución")  

#### EndPoints Examples (without docker):
http://localhost:80/api/comic/characters/ironman?pageNo=1&pageSize=20  
http://localhost:80/api/comic/characters/capamerica?pageNo=1&pageSize=20  
http://localhost:80/api/comic/colaborators/ironman?pageNo=1&pageSize=20  
http://localhost:80/api/comic/colaborators/capamerica?pageNo=1&pageSize=20  

Remember to change the **URL**  depending if you are using docker or not.  
