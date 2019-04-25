# Expense-Manager-API

This is a sprinboot project (RESTFul web service) and provides the necessary endpoints. 

## Run and deploy the service

**mvn clean install**

**mvn spring-boot:run**

## Swagger Documentation URL

`http://localhost:8089/ExpenseManager/swagger-ui.html`

## Technical Description

**Embedded MongoDB** : Provides an embedded environment along with springboot, no external database or database server is required. Runs as a part of springboot application. It is only for development purpose, not for production. 
To connect to an external database server, a small change is requied in the mongo configuration class.

**Springboot v2** : Provides an integrated and bootstraped platform to devlopment web based application with minimal configuration.

## Swagger Screenshot: 
https://github.com/SubhadeepSen/Expense-Manager  <Expense_Manager_API_Swagger.png>

