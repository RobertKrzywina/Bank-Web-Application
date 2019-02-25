# Bank Account Web Application

## Table of contents
* [General info](#general-info)
* [Facts](#facts)
* [Technologies](#technologies)
* [Database structure](#database-structure)

### General info
This project is a bank account web application.<br>
I used design pattern called 'Facade Pattern'.<br>
Default running port is 8080.<br>
In src/main/resources folder, you will have an application config and a sql script with some fake data.

### Facts
* login and password for admin account are a:a and b:b
* application uses 2 roles, user and admin, that means each account can have 1 or 2 roles 
* anonymous user can create new account
* when creating new account, server will send a verification token to given email
* when user forgot password or login, he can reset it by giving correct email and server will send a verification token to given email
* after successfully created account, server will automatically create random bank account number and balance which equals 0
* application has 2 panels, for user and admin
* in user panel u can change details about your account, send transaction to other user and see list of received/sent transactions
* in admin panel u can make CRUD operations on users

### Technologies
Project is created with:

<b>Back-end<b>
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

<b>Front-end<b>
* Thymeleaf
* Thymeleaf Extras Springsecurity5
* Bootstrap 4
* CSS
* JS

<b>Code editor<b>
* Intellij
* Visual Studio Code

### Database structure
![ScreenShot](database_structure.png)
