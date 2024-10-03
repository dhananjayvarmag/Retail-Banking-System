# Retail Banking System
A focused Microservice application to perform a variety of operations such as user authentication, opening accounts, checking balance, transferring funds, and viewing transaction histories.

- Account Microservice, takes care of the functionalities related to customer's account
    1. Get Account Details by Account Id
    2. Create Account for a Customer
    3. Get All Account Details of a Customer
    4. Service Charges
- Authentication Microservice, deals with the functionalities related to customer's authentication
    1. Register New Customer
    2. Login Customer
    3. Validate Customer's Token
- Customer Microservice, manages the customer's personal details
    1. Get Customer Details
    2. Create New Customer
- Account Microservice, takes care of the functionalities related to customer's account
    1. Get Account Details by Account Id
    2. Create Account for a Customer
    3. Get All Account Details of a Customer
    4. Deposit Amount into account
    5. Withdraw Amount from account
    6. Service Charges
    7. Transaction between two accounts
- Rules Microservice, takes care of the validation part, and it enforces the rules and validations to be taken care for user transactions
    1. Evaluate Minimum Balance
    2. Service Charges
- Transaction Microservice, takes care of the account's transactions
    1. Transfer money between two accounts
    2. Deposit Amount
    3. Withdraw Amount
    4. Get All Transactions by Account Id
    5. Service Charges

The User Interface is Developed using Angular JS.

Team Size: 4 ( Myself Dhananjay G, Krishna M, Tejas K N, Harish G). We implemented this project as a proof of concept (POC) during our time at Cognizant, with the primary goal of learning Spring Boot and Java API development. This POC allowed us to explore key functionalities of a retail banking system while gaining hands-on experience with backend technologies.

**Language and Frameworks:** Java, Angular, Spring Boot, Spring JPA, Microservices, Maven, MySQL, Swagger, JWT Authentication

