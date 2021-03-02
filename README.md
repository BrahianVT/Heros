# Heros

## Solución Realizada
![Texto alternativo](img/diagrama.jpeg?Raw=true "Solución")

## Dos Compenentes en el proyecto:
* ConsumeApiFillDataBase
* RestApi


El primer servicio llamado **ConsumeApiFillDataBase** persistirá en una base de datos MySql desde esta página 
[Fuente](https://developer.marvel.com/) , los datos se actualizan cada 24 horas.

Aquí se realizan algunos pasos antes de guardar en la base de datos, necesitamos filtrar la información relevante (consulte las tablas a continuación)
 los datos relevantes que estan en los campos "creators" y "characters" del response.  

## Base de datos
Básicamente esta son  las tablas:

![](img/sql.PNG?Raw=true "MySql ")

**Tecnologías**
* Java 8 y Maven
* Intellij Idea, Windows 10 Home
* Plugin Lombok Intellij Idea
* [Docker and Docker Compose(docker tool box)](https://docs.docker.com/toolbox/toolbox_install_windows/) para Windows 10 Home
* [Spring Initialzr](https://start.spring.io/) Usado en el proyecto llamado RestApi.
* MySql bases de datos
* Java 8, Spring Boot 2, Maven, Spring Data, Spring Data, Lombok, Docker Compose, Slf4j y Swagger

### Ajustes Usuarios con Windows
Primero clone este repositorio, asegúrese de haber instalado antes **docker-compose**.  
Ejecute el archivo .bat llamado **assemble.bat** y espere a que levante.  

Si los servicios levantaron correctamente ejecute **docker ps** y vera los tres servicios arriba:
![](img/servicios.PNG?Raw=true "Servicos ")

### Ajustes Usuarios que no utilizan Windows

Primero clone este repositorio y desde la terminal ejecute el siguiente comando, asegúrese de haber instalado antes **docker-compose**.  
Ejecute este comando para ejecutar el servicio MySQL (esto en la carpeta raíz):  

```
docker-compose up
```

En la terminal ve a la carpeta **ConsumeApiFillDataBase** y ejecuta el comando:
```
docker build --tag=api-store-bd-m:1.0 --rm=true .
```

Aquí hay otro archivo llamado docker-compose.yml, ejecute este comando para ejecutar el servicio.  
```
docker-compose up
```

En la terminal ve a la carpeta **RestApi** y ejecuta:
```
docker build --tag=api-rest-m:1.0 --rm=true .
```
Y

```
docker-compose up
```

### Ajustes Usuarios que no utilizan Docker
Estos servicios son proyectos Java utilizando Maven, por lo que si no desea utilizar contenedores Docker, puede ejecutar los proyectos pero tendría que tener instalado MySql
y debera ejecutar manualmente el script **init.sql** y configurar debidamente la conexion a la base de datos en ambos componentes y finalmente ejecutar los proyectos Java. 
Vaya a la carpeta **Target**  de cada proyecto y ejecute el comando:  
```
java -jar filldataBase.jar ||  java -jar RestApi-1.0.jar
```

La documentación de los servicios del componente "RestApi":  

### Swagger  
Con docker asegurese poner la url correspondiente varia dependiendo el sistema operativo:  
Aquí están los endPoints: 
Sin Docker  
http://localhost:80/swagger-ui.html  
Docker  
192.168.99.100:80/swagger-ui.html  
![](img/swagger.PNG?Raw=true "Solución")  

#### EndPoints Exemplos (sin docker):
http://localhost:80/api/comic/characters/ironman?pageNo=1&pageSize=20  
http://localhost:80/api/comic/characters/capamerica?pageNo=1&pageSize=20  
http://localhost:80/api/comic/colaborators/ironman?pageNo=1&pageSize=20  
http://localhost:80/api/comic/colaborators/capamerica?pageNo=1&pageSize=20  

Recuerde cambiar la **URL** dependiendo de si está usando Docker o no.  
