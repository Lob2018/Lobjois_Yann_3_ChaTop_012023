# ChaTop API

</br>

This project was generated with :
> [Spring Boot](https://spring.io/projects/spring-boot) version 3.0.1.

> [OpenJDK](https://openjdk.org/projects/jdk/19/) version 19.0.1.

> [Spring Security](https://spring.io/projects/spring-security) version 6.0.0.

</br>

## Start the project (with Spring Tool Suite 4 IDE)

</br>

Git clone:

> git clone https://github.com/Lob2018/Lobjois_Yann_3_ChaTop_012023

</br>

Set the user's environment variables (batch file with command prompt)

> Double clic on `CHATOP_CREATE_ENVIRONMENT_VAR.bat`, to set the variables and note their values`*`

[*Those environment variables are needed to use this API*](#user-environment-variables-details) 

(double clic on `CHATOP_DROP_ENVIRONMENT_VAR.bat` if you want to remove them after)

</br>

MySQL version 8.0.31 (MySQL Community Server - GPL) :

> Port : 3306

> Create the username and the password (`*`from noted values)

> Create a new schema named : chatop

> Give rights for this username on the chatop schema

> Execute the queries from the script `src/main/resources/data.sql` for the chatop schema

</br>

Start the API with Spring Tool Suite 4 :

> Right click on folder > Run as > Maven install (install Maven dependencies)

> Boot dashboard > Select the project > Start the process (start the ChaTop API)

</br>

Front-end :

> [With Postman](#postman-collection)
or
> [with the Angular application](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

</br>

### API Properties

> Tomcat port : 3001

> Maximum file and request size is 5MB

*For the picture of the rental, only the name of the file is saved in database.*

</br>

## Ressources

</br>

### MySQL

SQL script for creating the schema is available `src/main/resources/data.sql`

</br>

### Postman collection

Import the collection `src/main/resources/rental.postman_collection.json`

Postman's documentation to import a collection :

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman

</br>

### Swagger

Swagger UI in HTML is available at `http://localhost:3001/swagger-ui/index.html#/`

</br>

## User environment variables details ###

</br>

> Variable for MySQL password : `CHATOP_YL_API_MYSQL_PASSWORD`

> Variable for MySQL username : `CHATOP_YL_API_MYSQL_USERNAME`

> Variable for the JWT issuer : `CHATOP_YL_API_JWTISSUER`

> Variable for the JWT secret : `CHATOP_YL_API_JWTSECRET`

</br>

---

</br>

## Example to create a user (after installation)

</br>

> MySQL   : Run MySQL

> Spring Tool suite : Run ChaTop API

> Postman : File import `src/main/resources/rental.postman_collection.json`

> Postman : auth > register > Send

> Postman : The token is in the Body of the response with HTTP code 200

> MySQL   : The user is created