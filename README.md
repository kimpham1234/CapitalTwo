# CapitalTwo

This is a banking application that provides customers with free service to record & track their daily spending. 
In return, the app sells their customer transaction to business that helps the businesses track their sells and their customer.

## Our Stack
1. Database: MySQL
2. Application: Spring
3. Frontend: ReactJS

## Installation and run instruction (MACOS)

1. Update the package index: sudo apt-get update
2. Install the Java JDK: sudo apt-get install openjdk-8-jdk
3. Set the JAVA_HOME environment
4. Setup Maven: sudo apt-get install maven
5. Setup MySQL
   - Install MySQL Serve
   		sudo apt-get install mysql-server
   - Start MySQL Server
   		sudo service mysql start
   - Start the MySQL command line as root
   		mysql -u root -p
   - In the MySQL command line, create the “springdb” database
		mysql> create database springdb;
   - Create the MySQL user “springdb” at “localhost” with password “123456”
		mysql> create user 'springdb'@'localhost' identified by '123456';
   - Grant all permissions of database “springdb” to user “springdb”
		mysql> grant all on springdb.* to 'springdb'@'localhost';
6. Open a new terminal and change to the root directory of the CapitalTwo project
   - Start the project with a clean package
		mvn clean package spring-boot:run
7. Open another new terminal and change to the root directory of the CapitalTwo project
   - Copy the entire file “./views” to the clipboard
   - On the MySQL command line, paste the contents into the command line, and be sure that you are on the springdb database. This will create the views.
8. Open your web browser and navigate to “localhost:8080” where you will be greeted with the login page

## Useful tutorials
1. Spring with ReactJS and DATA REST (frontend with rest backend)
(https://spring.io/guides/tutorials/react-and-spring-data-rest/)

2. Spring with MySQL (backend only)
(https://spring.io/guides/gs/accessing-data-mysql/)


