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

#### Security Login/Logout
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