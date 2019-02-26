# Bank Account Web Application

This project is a bank account web application. I used MVC and Facade patterns.

### Stack

<b>Back-end</b>
* Java 8
* Maven
* Spring Boot
* Spring Data
* Spring Validation
* Spring Security
* Hibernate
* PostgreSQL
* Lombok
* Guava: Google Libraries for Java
* SMTP protocol

<b>Front-end</b>
* Thymeleaf
* Thymeleaf Extras Springsecurity5
* Bootstrap 4
* CSS
* JS

<b>Code editor</b>
* Intellij
* Visual Studio Code

### Prerequisites

Application requires running PostgreSQL database with:
* schema name: project
* username: postgres
* password: admin

### Build it with Maven and run Java application

```sh
$ mvn clean install
```
```sh
$ java -jar target/project-0.0.1-SNAPSHOT.jar
```

### About project

* login and password for admin account are a:a and b:b
* application uses 2 roles, user and admin, that means each account can have 1 or 2 roles 
* anonymous user can create new account
* when creating new account, server will send a verification token to given email
* when user forgot password or login, he can reset it by giving correct email and server will send a verification token to given email
* after successfully created account, server will automatically create random bank account number and balance which equals 0
* application has 2 panels, for user and admin
* in user panel u can change details about your account, send transaction to other users and see list of received/sent transactions
* in admin panel u can make CRUD operations on users

### Database structure
![ScreenShot](database_structure.png)
