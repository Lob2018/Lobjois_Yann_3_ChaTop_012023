# ChaTop API

</br>

This project was generated with :
> [Spring Boot](https://spring.io/projects/spring-boot) version 3.0.1.

> [OpenJDK](https://openjdk.org/projects/jdk/19/) version 19.0.1.

> [Spring Security](https://spring.io/projects/spring-security) version 6.0.0.

</br>

## Start the project (with Eclipse)

</br>

Git clone:

> git clone https://github.com/Lob2018/Lobjois_Yann_3_ChaTop_012023

> Right click on folder > Run as > Maven install (install Maven dependencies)

> Boot dashboard > Select the project > Start the process (start the ChaTop API)

### Properties

> Tomcat port : 3001

> Maximum file and request size is 5MB

*For the picture of the rental, only the name of the file is saved in database.*

</br>

## Ressources

</br>

### MySQL

SQL script for creating the schema is available `src/main/resources/data.sql`

> Schema name : chatop

> Port : 3306

> Username : root

> Password : yann

</br>

### Postman collection

Import the collection `src/main/resources/rental.postman_collection.json`

Postman's documentation to import a collection :

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman

</br>

### Swagger

Swagger UI in HTML is available `http://localhost:3001/swagger-ui/index.html#/`

</br>

## Example to create a user (after installation)

</br>

> MySQL   : Run MySQL

> Eclipse : Run ChaTop API

> Postman : File import `rental.postman_collection.json`

> Postman : auth > register > Send

> MySQL   : The user is created

*Postman : The token is in the Body of the response*


