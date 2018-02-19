Introduction
====
Java 8 sdk should be installed on your computer.

How to run the solution:
* Open a command line screen
* Go to the solution folder
* run the following command: 'mvnw clean package'
* Go to the folder target
* Run the following command: 'java -jar expense-0.0.1-SNAPSHOT.jar'
* The app should run in port 8080
* Open a browser and go to "http://locahost:8080"

Important
====
The app uses a embedded H2 database by default but we can change the database creating a file on the same folder of the jar

The name of the file should be 'application.properties' with the following database information:

server.port=8080

spring.profiles.active=integration
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=our_username
spring.datasource.password=our_password

The database should have the expense table.


Technology
====
* Java 1.8
* Maven 3
* Spring boot 1.5
* Tomcat as the server
* H2 as the dev database
* mysql as the integration database
* Jersey as RESTful Web services 

NOTES
====
I wanted to have the backend in different server than the front but I was not able to change the host and port of the XHR request in restalchemy.js


IMPORTANT
====
To avoid unconscious bias, we aim to have your submission reviewed anonymously by one of our engineering team. Please try and avoid adding personal details to this document such as your name, or using pronouns that might indicate your gender.