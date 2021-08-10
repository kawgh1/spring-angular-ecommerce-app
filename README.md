### 8/9/2021 There is some backend issue with Spring and the Web Security Configuration
- I am getting a 404 error when making a purchase, it's acting like the api/checkout/purchase endpoint isn't there when its clearly defined in the CheckoutController
- Could be CORS or CSRF related but nothing seems to work

### This is the error I'm getting in browser console
#### General:
Request URL: https://springboot-angular-ecommerce.herokuapp.com/api/checkout/purchase
Request Method: POST
Status Code: 404 
Referrer Policy: strict-origin-when-cross-origin

#### Response Headers:
Access-Control-Allow-Credentials: true
Access-Control-Allow-Headers: Content-Type, Accept, X-Requested-With, remember-me
Access-Control-Allow-Methods: GET, OPTIONS
Access-Control-Allow-Origin: https://spring-angular-ecommerce-front.herokuapp.com
Access-Control-Max-Age: 3600
Connection: keep-alive
Content-Type: application/json
Date: Tue, 10 Aug 2021 12:58:19 GMT
Server: Cowboy
Transfer-Encoding: chunked
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Via: 1.1 vegur

#### Request Headers:
Accept: application/json, text/plain, */*
Accept-Encoding: gzip, deflate, br
Accept-Language: en-US,en;q=0.9
Connection: keep-alive
Content-Length: 604
Content-Type: application/json
Host: springboot-angular-ecommerce.herokuapp.com
Origin: https://spring-angular-ecommerce-front.herokuapp.com
Referer: https://spring-angular-ecommerce-front.herokuapp.com/
sec-ch-ua: "Chromium";v="92", " Not A;Brand";v="99", "Google Chrome";v="92"
sec-ch-ua-mobile: ?0
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: cross-site

#### Request Payload:  (JSON)
{customer: {firstName: "asdf", lastName: "asdf", email: "asdf@asdf.com"},…}
billingAddress: {street: "asdf", city: "asdf", state: "Andhra Pradesh", zipCode: "asdf", country: "India"}
customer: {firstName: "asdf", lastName: "asdf", email: "asdf@asdf.com"}
order: {totalPrice: 37.98, totalQuantity: 2}
orderItems: [{imageUrl: "assets/images/products/coffeemugs/coffeemug-luv2code-1000.png", quantity: 1,…},…]
shippingAddress: {street: "asdf", city: "asdf", state: "Andhra Pradesh", zipCode: "asdf", country: "India"}


# A basic SpringBoot + Angular RESTful E-Commerce web app hopsted on Heroku 

All of the page sections are Angular components that are populated with data (Product, Product Category, State, Country) from a MySQL database through Spring Data REST JPA calls. 


- To view the **live site** hosted on Heroku please click **[here](https://spring-angular-ecommerce-front.herokuapp.com/products)**

- To view the **Angular front end** code please click **[here](https://github.com/kawgh1/spring-angular-ecommerce-frontend)**

- To view the API please click **[here](https://springboot-angular-ecommerce.herokuapp.com/api/)**


- This project is based on a course by [Chad Darby](https://www.udemy.com/course/full-stack-angular-spring-boot-tutorial/)

- For the backend I did the heroku deployment including MySQL database set up. I also fixed a CORS issue but have since updated that fix using the correct method according to Spring documentation

- The course and deployment were a lot of fun. I look forward to creating more Angular/React + SpringBoot applications.

#### Notes

- ##### Deprecated 
    - To solve the CORS problem I used the solution found here by user abosancic
    - https://stackoverflow.com/questions/32319396/cors-with-spring-boot-and-angularjs-not-working

- ##### Update 8/21/2021
    - Updated the CORS configuration the Spring way - see Backend Configuration below
    - Requires Spring 2.4.0+
    - **Much Cleaner, More Secure**
    
    
# [Table of Contents](#table-of-contents)
1. [Backend Configuration](#backend-configuration)
2. [Saving Customer Orders](#saving-customer-orders)
3. [Order History](#order-history)
4. [Secure Order History](#secure-order-history)
5. [Security Login-Logout](#security-login-logout)
    
#### [Backend Configuration](#backend-configuration)
- **Development Process**

1. Fix deprecated method for Spring Data REST - requires Spring 2.4.0+

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

#### [Order History](#order-history)
- **Development Process**

1. Create OrderRepository REST API
    - Expose endpoint to search for orders by the customer's email address
        - HTTP Method ------------------- endpoint
        - ___ **GET** ___ --------------- **"/api/orders/search/findByCustomerEmail?email=john.doe@gmail.com"**
    - Spring Data REST and Spring Data JPA supports "query methods"
        - Spring will construct a query based on method naming conventions
        
        Ex)
            File: OrderRepository.java
            
            @RepositoryRestResource
            public interface OrderRepository extends JpaRepository<Order, Long> {
               
                Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);
                
            }
        
        - So behind the scenes, with this code, Spring will execute the following SQL query with the method call
            - SELECT * FROM orders LEFT OUTER JOIN customer
            - ON orders.customer_id = customer.id WHERE customer.email = "email"
            
2. Update configs to make OrderRepository REST API read only
    - disable HTTP methods
    - read only is still public
            
[Top](#table-of-contents)

#### [Secure Order History](#secure-order-history)
- #### Development Process
    - Currently, "/api/orders" is wide open - it should only be accessible to logged in users and only for their own personal orders
    - ___ **GET** ___ --------------- **"/api/orders/search/findByCustomerEmail?email=john.doe@gmail.com"**
1. Add Okta Spring Boot Starter to Maven pom.xml
    
    File: pom.xml
        
        ...
        <dependency>
            <groupId>com.okta.spring</groupId>
            <artifactId>okta-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>
        ...
        
2. Create an App at the Okta Developer site
3. In Spring Boot app, set application properties

    File: application.properties
    
        ...
        okta.oauth2.client-id={yourClientId}
        okta.oauth2.client-secret={yourClientSecret}
        okta.oauth2.issuer=https://{yourOktaDomain}/oauth2/default
        ...
        
    - Resource Server
        - The "Resource Server" is the app that is hosting our protected resources
            - the Spring Boot app in this case
        - The "Resource Server" manages security using access tokens (JWT)
        - The access tokens are validated with the "Authorization Server" (Okta)

![OAuth2 Diagram](https://github.com/kawgh1/spring-angular-ecommerce-app/blob/master/src/main/resources/oauth2-diagram.png)

- Client (Angular app) sends the access token as an HTTP request header
    - Authorization: Bearer <token>

4. Protect endpoints in Spring Security configuration class

    File: SecurityConfiguration.java
    
        ...
        @Configuration
        public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        
            @Override
            protected void configure(HttpSecurity http) throws Exception {
            
                // protect endpoint /api/orders
                http.authorizeRequests()
                    .antMatchers("/api/orders/**") // protect the endpoint... only accessible to authenticated users
                    .authenticated()
                    .and()
                    .oauth2ResourceServer() // Configures OAuth2 Resource Server support
                    .jwt(); // enables JWT-encoded bearer token support
                    
                // add CORS filter
                http.cors();
            }
        
        }
        
5. EXTRA NOTE - 
    -Once we implemented Okta Authorization and the Angular HTTP Interceptors, the POST /purchase began to fail giving a 403 error
        - Fails because we are sending checkout request qith HTTP POST
            - By default CSRF is enabled and CSRF performs checks on POST using COOKIES
        - Since we are not using cookies for session tracking, CSRF says request is unauthorized 403
        - **We can resolve this by disabled CSRF**
            - This technique is commonly used for Single-Page Apps (SPA) and REST APIs
            - See the Controller/CheckoutController and Config/SecurityConfiguration files for more info

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