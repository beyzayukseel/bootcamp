# THY FINTECH - BOOTCAMP

TKPAY Group Travel Wallet is designed to facilitate joint expenses between group members while traveling. It allows users to create a shared wallet, invite members, pay businesses, and manage expenses effectively.

* Create and manage shared wallets.
* Invite members.
* Making payments to registered businesses.
* Tracking and categorizing expenses.
* Sending real-time notifications.

## Used Technologies & Architecture & Methods:

* Monolithic Architecture
* Java 17
* Spring Boot 3.2.5
* Postgres
* Swagger
* Maven
* Docker
* Logger

## DATABASE SCHEMA:

Created 9 entity for this study.

User: Customer

Account: User's Miles&Smiles Account.

Card: User's card details/information that associated with account.

Wallet: Group Wallet 

GroupWalletInvitation: Group Wallet Invitation Link 

Transaction: Money transfer between Account and Wallet. 

Payment: Money transfer between Wallet and Receiver.

Receiver: Merchants.

MilPoint: Points that user earned. 


![db-modelling](docs/diagrams/UMLClassDiagram.png)


## SWAGGER

Swagger Implementation for API Documentation

``` 
    http://localhost:8086/swagger-ui/index.html#/
 
 ```

For JSON format:

```
    http://localhost:8086/v3/api-docs
    
```

![db-modelling](docs/diagrams/Swagger-1.png)

![db-modelling](docs/diagrams/Swagger-2.png)



## Docker

Docker Compose file contains 2 services. One for Postgres and the other one for Spring Boot Application.

Run the services using Docker Compose command:

     docker-compose up  


