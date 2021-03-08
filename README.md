# Wallet-Payment


The code is written in Java using Spring Boot, Data JPA, Spring Security, Swagger Tool, JUnit Testing, Microservices

There are 3 Microservices and a Discover Registry:
1. Wallet User
2. Wallet Account
3. Wallet Transaction

Steps to access the Services:
1. Run the Discover Registry code followed by all the 3 Microservices
2. To check if all the services are up, type http://localhost:8761 in browser
3. In new tab, type http://localhost:9292/swagger.html to access the Login Page
4. For Login Page, Username = user ;  Password: copy from the Wallet User Console


Note:
- A User can have a single Account with multiple Transactions
- Make the required changes in yml files of all services
- Include the lib folder from Wallet Account in Build Path of all the Microservices 
