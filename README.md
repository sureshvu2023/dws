Money Transfer Application  (DEV Challenge)
Overview
This is a simple Spring Boot application that provides a REST API for managing accounts and transferring money between them. The application ensures thread-safe operations, preventing deadlocks and corrupted account states.

Features
Add an account: Allows you to create an account with an ID and initial balance.

Read an account: Retrieves the account details for a given account ID.

Transfer money: Enables you to transfer a positive amount from one account to another. A notification will be sent to both account holders after each successful transfer.

**Setup**
**Clone the repository:**
git clone https://github.com/sureshvu2023/dws.git

**Build the application using Gradle**
./gradlew build

**Run the application:**
./gradlew bootRun

The application will start on **http://localhost:8000.**
Check API endpoints Request,Responses in the following Swagger-UI
**#Swagger-UI**
http://localhost:8000/swagger-ui/index.html

**Test Cases**
The application includes unit tests for the AccountService class to ensure the correctness of money transfers and concurrency handling. The test cases cover the following scenarios:
Successful money transfer between accounts.
Transfer with invalid account IDs.
Transfer with insufficient funds.
Concurrent transfers from multiple threads ensuring correct balance.
**To run the tests, use the following command:**
./gradlew test

**Concurrency Handling**
The application uses synchronized methods in the AccountService to ensure thread safety. When multiple threads attempt to transfer money concurrently, the balance updates are handled correctly, and the application maintains consistency.

**Dependencies**
The application uses the following major dependencies:

**Spring Boot:** Provides the framework for building the application.
**Lombok: **Reduces boilerplate code with annotations.
**Springfox**: Generates OpenAPI documentation and Swagger UI.

**Future Improvements**
In the future, the application could be extended to include features like transaction logging, user authentication, database and additional error handling.


