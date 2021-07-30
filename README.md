# A basic SpringBoot + Angular RESTful E-Commerce web app hopsted on Heroku 

All of the page sections are Angular components that are populated with data (Product, Product Category, State, Country) from a MySQL database through Spring Data REST JPA calls. 


To view the live site hosted on Heroku please visit https://spring-angular-ecommerce-front.herokuapp.com/products

To view the front end code please visit https://github.com/kawgh1/spring-angular-ecommerce-frontend

To view the API please visit https://springboot-angular-ecommerce.herokuapp.com/api/


This project is based on a course by Chad Darby at https://www.udemy.com/course/full-stack-angular-spring-boot-tutorial/

For the backend I did the heroku deployment including MySQL database set up.

The course and deployment were a lot of fun. I look forward to creating more Angular/React + SpringBoot applications.

#### Notes

To solve the CORS problem I used the solution found here by user abosancic

https://stackoverflow.com/questions/32319396/cors-with-spring-boot-and-angularjs-not-working


#### Development Process for Saving Customer Orders
1. Run database script / create tables and rules - see resources/sqlscripts/create-order-tables.sql
2. Create JPA entities
3. Create DTOs
4. Create repository
5. Create service
6. Create Controller
7. POSTMAN test