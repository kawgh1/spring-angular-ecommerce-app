# A basic SpringBoot + Angular RESTful E-Commerce web app hopsted on Heroku 

All of the page sections are Angular components that are populated with data (Product, Product Category, State, Country) from a MySQL database through Spring Data REST JPA calls. 


- To view the **live site** hosted on Heroku please click [here](https://spring-angular-ecommerce-front.herokuapp.com/products)

- To view the front end code please click [here](https://github.com/kawgh1/spring-angular-ecommerce-frontend)

- To view the API please click [here](https://springboot-angular-ecommerce.herokuapp.com/api/)


- This project is based on a course by [Chad Darby](https://www.udemy.com/course/full-stack-angular-spring-boot-tutorial/)

- For the backend I did the heroku deployment including MySQL database set up. I also fixed a CORS issue but have since updated that fix using the correct method according to Spring documentation

- The course and deployment were a lot of fun. I look forward to creating more Angular/React + SpringBoot applications.

#### Notes

- ##### Deprecated 
    - To solve the CORS problem I used the solution found here by user abosancic
    - https://stackoverflow.com/questions/32319396/cors-with-spring-boot-and-angularjs-not-working

- ##### Update 8/21/2021
    - Updated the CORS configuration the Spring way - see Backend Configuration below
    - **Much Cleaner, More Secure**
    
    
# [Table of Contents](#table-of-contents)
1. [Backend Configuration](#backend-configuration)
2.
3. [Security Login-Logout](#security-login-logout)
    
#### [Backend Configuration](#backend-configuration)
- **Development Process**

1. Fix deprecated method for Spring Data REST

    File: MyDataRestConfig.java
    
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, 
                                                         CorsRegistry cors) {
        
            ...
       }
       
2. Configure CORS mapping for Spring Data REST

    File: MyDataRestConfig.java
        
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, 
                                                             CorsRegistry cors) {
            
                ...
                
                // configure CORS mapping
                cors.addMapping("/api/**".allowedOrigins("http:localhost:4200");
           }
           
    File: application.properties
    
        ...
        spring.data.rest.base-path=/api

3. Configure CORS mapping for @RestController
    - @RestController configuration is separate from Spring Data REST configuration
    
4. Disable HTTP PATCH method
    - Previously we configured the Spring Data REST APIs for read-only
        - Disabled HTTP GET, POST and DELETE
        - Still need to disable HTTP PATCH as users could still PATCH data to that endpoint
5. Modify Spring Data REST Detection Strategy
    - **By Default**, Spring Data REST will expose REST APIs for **all** Spring Data Repositories
    - This is generally convenient ... but we may not want to publicly expose all of our APIs!!
        - For example, we have a **Customer Repository**
            - **BUT** we don't want to expose that repository as a publicly accessible REST API
            - We only want to use it internally behind the server ... like for checking if a new customer already exists in our database
    - REST endpoint **/api/customers/...** is currently exposed - we need to fix that
        - Will use the **ANNOTATED** Spring Data REST Detection Strategy
            - ***ANNOTATED*** - Only exposes Spring Data repositories **explicitly** annotaed with **@RepositoryRestResource**
            
            File: application.properties
            
                ...
                spring.data.rest.detection-strategy=ANNOTED
                ...
                
            File: StateRepository.java
            
                @RepositoryRestResource
                public interface StateRepository extends JpaRepository<State, Integer> {
                
                    ...
                }

[Top](#table-of-contents)


#### [Saving Customer Orders](#saving-customer-orders)
- **Development Process**

1. Run database script / create tables and rules - see resources/sqlscripts/create-order-tables.sql
2. Create JPA entities
    - **Customer, Order, OrderItem, Address**
3. Create DTOs
    - **Purchase, PurchaseResponse**
4. Create repository
    - **CustomerRepository**
5. Create service
    - CheckoutService
        - **CheckoutServiceImpl**
6. Create Controller
    - @RestController
    - @RequestMapping("/api/checkout")
    - **CheckoutController**
7. POSTMAN test
    - Send a JSON Purchase object (like what will be created on the Angular front end) to the /api/checkout/purchase route
    
    Ex)
        
        {
           "customer":{
              "firstName":"John",
              "lastName":"Doe",
              "email":"john.doe@luv2code.com"
           },
           "shippingAddress":{
              "street":"afasa",
              "city":"afasa",
              "state":"Alberta",
              "country":"Canada",
              "zipCode":"afasa"
           },
           "billingAddress":{
              "street":"fsfsf",
              "city":"sfdsf",
              "state":"Acre",
              "country":"Brazil",
              "zipCode":"19111"
           },
           "order":{
              "totalPrice":36.98,
              "totalQuantity":2
           },
           "orderItems":[
              {
                 "imageUrl":"assets/images/products/coffeemugs/coffeemug-luv2code-1000.png",
                 "quantity":1,
                 "unitPrice":18.99,
                 "productId":26
              },
              {
                 "imageUrl":"assets/images/products/mousepads/mousepad-luv2code-1000.png",
                 "quantity":1,
                 "unitPrice":17.99,
                 "productId":51
              }
           ]
        }
        
    - Check SQL database to ensure all the data is correctly populated in each of the related tables (Customer, Order, etc.)
    
[Top](#table-of-contents)


#### [Security Login-Logout](#security-login-logout)
- Resources
    - OAuth 2 - Authorization - www.oauth.net
        - OAuth 2 is an authorization framework that enables applications — such as Facebook, GitHub, and DigitalOcean — to obtain 
        limited access to user accounts on an HTTP service. It works by delegating user authentication to the service that hosts 
        a user account and authorizing third-party applications to access that user account. OAuth 2 provides authorization flows 
        for web and desktop applications, as well as mobile devices.
    - OpenID Connect - Authentication - www.openid.net/connect
        - OpenID Connect is an authentication layer on top of OAuth 2.0, an authorization framework. 
        It allows users to be authenticated by cooperating sites (known as relying parties, or RP) using a third-party service, 
        eliminating the need for webmasters to provide their own ad hoc login systems, and allowing users to log into multiple 
        unrelated websites without having to have a separate identity and password for each. Users create accounts by selecting 
        an OpenID identity provider and then use those accounts to sign onto any website that accepts OpenID authentication. 
        Several large organizations either issue or accept OpenIDs on their websites, according to the OpenID Foundation.
    - JWT - Tokens - www.jwt.io
        - JWT is a proposed Internet standard for creating data with optional signature and/or optional encryption whose payload 
        holds JSON that asserts some number of claims. The tokens are signed either using a private secret or a public/private key. 
        For example, a server could generate a token that has the claim "logged in as admin" and provide that to a client. 
        The client could then use that token to prove that it is logged in as admin. The tokens can be signed by one party's 
        private key (usually the server's) so that party can subsequently verify the token is legitimate. If the other party, 
        by some suitable and trustworthy means, is in possession of the corresponding public key, they too are able to verify 
        the token's legitimacy. The tokens are designed to be compact, URL-safe, and usable especially in a web-browser 
        single-sign-on (SSO) context. JWT claims can typically be used to pass identity of authenticated users between an 
        identity provider and a service provider, or any other type of claims as required by business processes.
        
- What is an Authorization Server?
    - A server that generates tokens: OAuth 2 or OpenID Connect
    - Define access policies for a given application / protected resource
    - Can also serve as Identity Provider using OpenId Connect
    
- What is Okta?
    - okta.com provides a cloud based authorization server + platform
    - Authentication
        - Login widgets - that can be dropped into your site for instance Okta authentication on your site / app
        - Social login - allows your users to use their Facebook, Instagram, etc login to authenticate
    
    - Authorization
        - Role-based access
        - API access policies
    
    - User Management
        - Admin panel
        - Policy assignment
        
    - Supports industry standards: OAuth 2, OpenID Connect, JWT, etc.
    - Free Developer account
        - no credit card required
        - supports up to 1,000 users
        
- Okta - Angular and Java
    - Developing code with OAuth 2, OpenID Connect and JWT can required a lot of low-level boilerplate code
    
    - Okta provides SDKs, for Angular, Java, etc.
        - SDK is  a high level of abstraction ... helps to accelerate the development process
        - Includes login / sign-in widgets ... can customize look and feel with CSS, etc.
        
[Top](#table-of-contents)