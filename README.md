# ShoppingWeb

This is a database-driven web application for a shopping website, built with Java and the Spring Boot framework.

## Project Overview

The ShoppingWeb application provides basic e-commerce functionalities, including user registration, login, product browsing, and a shopping cart. It uses Spring Security for authentication and authorization, Spring Data JPA for database interactions, and Thymeleaf for server-side rendering of web pages.

## Technologies Used

*   **Java:** Version 17
*   **Spring Boot:** Version 3.2.5
*   **Frameworks & Libraries:**
    *   Spring Web: For building the web application.
    *   Spring Security: For user authentication and authorization.
    *   Spring Data JPA: For persisting data to a relational database.
    *   Thymeleaf: For creating the user interface.
    *   Hibernate: As the JPA provider.
    *   Maven: For project build and dependency management.
*   **Database:**
    *   MySQL: As the relational database.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 17 or later.
*   Apache Maven.
*   MySQL Server.

### Installation & Setup

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```

2.  **Database Setup:**
    *   Create a MySQL database named `shopping_db`.
    *   The application uses `spring.jpa.hibernate.ddl-auto=update`, so the database schema will be automatically created and updated based on the entity classes.
    *   You can also manually import the SQL scripts located in `src/main/resources/static/Database_import/shopdb4/` to populate the database with initial data.

3.  **Application Configuration:**
    *   Open the `src/main/resources/application.properties` file.
    *   Update the following properties with your MySQL database credentials:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/shopping_db
        spring.datasource.username=<your-username>
        spring.datasource.password=<your-password>
        ```

4.  **Build and Run the application:**
    *   You can run the application using the Maven wrapper:
        ```bash
        ./mvnw spring-boot:run
        ```
    *   The application will start on `http://localhost:8080`.

## Application Structure

*   **`src.main.java.com.dbproject.webapp`**: Root package.
    *   **`ShoppingWebApplication.java`**: The main Spring Boot application class.
    *   **`config`**: Contains security configuration (`SpringSecurity.java`).
    *   **`Controller`**: Handles incoming web requests.
        *   `LoginController`: Manages user registration and login.
        *   `ProductController`: Manages product display and cart operations.
        *   `UserController`: A simple controller for user-specific pages.
    *   **`Model`**: Contains the JPA entity classes (`User`, `Role`, `Product`, `Category`, `Cart`).
    *   **`Repository`**: Contains Spring Data JPA repositories for database access.
    *   **`Services`**: Contains business logic for the application.
    *   **`dto`**: Contains Data Transfer Objects (`UserDto`).
*   **`src.main.resources`**:
    *   **`application.properties`**: Configuration file for the application.
    *   **`static`**: Contains static resources like CSS, images, and SQL scripts.
    *   **`templates`**: Contains Thymeleaf HTML templates for the UI.

## Security Configuration

Spring Security is configured in `SpringSecurity.java` to manage access to different parts of the application.

*   **Publicly Accessible Paths:**
    *   `/`, `/products`, `/category/{id}`: Product browsing.
    *   `/registration/**`: User registration page.
    *   `/login/**`: User login page.
    *   `/h2-console/**`: H2 database console (if used).

*   **User Roles & Permissions:**
    *   `USER`: Can access user-specific pages under `/user/**`, and can add items to the cart.
    *   `ADMIN`: Has all the permissions of a `USER` and can also access admin-specific pages under `/admin/**`.

## Database Schema

The application uses the following main entities:
*   `User`: Stores user information (email, password).
*   `Role`: Stores user roles (e.g., `ROLE_USER`, `ROLE_ADMIN`).
*   `Product`: Stores product details (name, price, description).
*   `Category`: Stores product categories.
*   `Cart`: Represents items in the user's shopping cart.
