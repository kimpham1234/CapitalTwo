# CapitalTwo

This is a banking application that provides customers with free service to record & track their daily spending. 
In return, the app sells their customer transaction to business that helps the businesses track their sells and their customer.

## Our Stack
1. Database: MySQL
2. Application: Spring
3. Frontend: ReactJS

## Installation and run instruction (MACOS)

1. Update the package index
	```sudo apt-get update```
2. Install the Java JDK
	```sudo apt-get install openjdk-8-jdk```
3. Set the JAVA_HOME environment
4. Setup Maven
	```sudo apt-get install maven```
5. Install MySQL Server
	```sudo apt-get install mysql-server```
6. Start MySQL Server
	```sudo service mysql start```
7. Start the MySQL command line as root
	```mysql -u root -p```
8. In the MySQL command line, create the “springdb” database
	```mysql> create database springdb;```
9. Create the MySQL user “springdb” at “localhost” with password “123456”
	```mysql> create user 'springdb'@'localhost' identified by '123456';```
10. Grant all permissions of database “springdb” to user “springdb”
	```mysql> grant all on springdb.* to 'springdb'@'localhost';```
11. Open a new terminal and change to the root directory of the CapitalTwo project
12. Start the project with a clean package
	```mvn clean package spring-boot:run```
	*Note: This will take some time as it will auto-generate about 2,500 transactions
13. Open another new terminal and change to the root directory of the CapitalTwo project
14. Copy the entire file “./views” to the clipboard
15. On the MySQL command line, paste the contents into the command line, and be sure that you are on the springdb database. This will create the views.
16. Open your web browser and navigate to “localhost:8080” where you will be greeted with the login page

Login with only these two accounts to test it:
Type | Username | Password
--- | --- | --- |
Customer | therealdonald@gmail.com | 123456
Business | google@gmail.com | 123456

Note: Our authentication uses both MySQL and Firebase to generate sessions, so create a user is not synchronized to work offline, and it was not a focal point of the project. Also, remember to select Customer or Business, otherwise the front end will not generate data.

## Useful tutorials
1. Spring with ReactJS and DATA REST (frontend with rest backend)
(https://spring.io/guides/tutorials/react-and-spring-data-rest/)

2. Spring with MySQL (backend only)
(https://spring.io/guides/gs/accessing-data-mysql/)


