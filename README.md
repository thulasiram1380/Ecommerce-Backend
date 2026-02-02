<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h1>E-Commerce Backend Application (Java)</h1>
    
    <p>A robust, scalable Java-based E-Commerce Backend application built with Spring Boot and RESTful APIs. Focuses on core e-commerce functionality including product management, category organization, shopping carts, and image handling.</p>
    
    <p>
        <img src="https://img.shields.io/badge/Java-17%2B-blue.svg" alt="Java">
        <img src="https://img.shields.io/badge/Spring%20Boot-3.x-green.svg" alt="Spring Boot">
        <img src="https://img.shields.io/badge/MySQL-8.0-purple.svg" alt="MySQL">
    </p>

    <h2>Project Overview</h2>
    <p>This backend-only application manages essential e-commerce operations with clean architecture, ensuring scalability and maintainability. No frontend integration - pure REST API backend.</p>

    <h2>Technology Stack</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>Category</th>
                <th>Technologies</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><strong>Language</strong></td>
                <td>Java 17+</td>
            </tr>
            <tr>
                <td><strong>Framework</strong></td>
                <td>Spring Boot 3.x, Spring Data JPA</td>
            </tr>
            <tr>
                <td><strong>Database</strong></td>
                <td>MySQL 8.0, Hibernate ORM</td>
            </tr>
            <tr>
                <td><strong>Build Tool</strong></td>
                <td>Maven</td>
            </tr>
            <tr>
                <td><strong>API</strong></td>
                <td>RESTful APIs (JSON)</td>
            </tr>
            <tr>
                <td><strong>Version Control</strong></td>
                <td>Git & GitHub</td>
            </tr>
        </tbody>
    </table>

    <h2>Core Features</h2>

    <h3>Product Management</h3>
    <ul>
        <li>CRUD operations for products</li>
        <li>Dynamic price calculation</li>
        <li>Product-category associations</li>
        <li>Multiple image support per product</li>
    </ul>

    <h3>Category Management</h3>
    <ul>
        <li>Full CRUD for product categories</li>
        <li>Hierarchical organization</li>
        <li>Category-specific business rules</li>
    </ul>

    <h3>Cart Management</h3>
    <ul>
        <li>Create and manage shopping carts</li>
        <li>Add/remove cart items</li>
        <li>Update quantities dynamically</li>
        <li>Real-time cart total calculation</li>
    </ul>

    <h3>Image Management</h3>
    <ul>
        <li>Store product images</li>
        <li>Associate multiple images per product</li>
        <li>Handle image metadata</li>
    </ul>

    <h3>Advanced Features</h3>
    <ul>
        <li>Global exception handling</li>
        <li>Data consistency across operations</li>
        <li>Layered architecture (Controller → Service → Repository)</li>
    </ul>

    <h2>Database Schema</h2>
    <p><strong>MySQL Relational Database</strong> with proper entity relationships:</p>
    <pre>Products ↔ Categories (Many-to-One)
Products ↔ Images (One-to-Many)
Carts ↔ CartItems (One-to-Many)
CartItems ↔ Products (Many-to-One)</pre>

    <h2>Project Structure</h2>
    <pre>src/main/java/com/ecommerce/
├── controller/     # REST API endpoints
├── service/        # Business logic
├── repository/     # Data access layer
├── entity/         # Domain models
├── exception/      # Custom exceptions
└── dto/           # Data transfer objects</pre>

    <h2>Quick Start</h2>

    <h3>Prerequisites</h3>
    <ul>
        <li>Java 17+</li>
        <li>MySQL 8.0</li>
        <li>Maven 3.8+</li>
    </ul>

    <h3>1. Clone & Setup</h3>
    <pre><code>git clone https://github.com/yourusername/ecommerce-backend.git
cd ecommerce-backend</code></pre>

    <h3>2. Database Configuration</h3>
    <p>Update <code>application.properties</code>:</p>
    <pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update</code></pre>

    <h3>3. Run Application</h3>
    <pre><code>mvn clean install
mvn spring-boot:run</code></pre>

    <h3>4. API Base URL</h3>
    <pre>http://localhost:8080/api/v1</pre>

    <h2>API Endpoints</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>Resource</th>
                <th>Method</th>
                <th>Endpoint</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Products</td>
                <td>GET</td>
                <td>/products</td>
                <td>List all products</td>
            </tr>
            <tr>
                <td></td>
                <td>POST</td>
                <td>/products</td>
                <td>Create product</td>
            </tr>
            <tr>
                <td></td>
                <td>PUT</td>
                <td>/products/{id}</td>
                <td>Update product</td>
            </tr>
            <tr>
                <td></td>
                <td>DELETE</td>
                <td>/products/{id}</td>
                <td>Delete product</td>
            </tr>
            <tr>
                <td>Categories</td>
                <td>GET/POST/PUT/DELETE</td>
                <td>/categories</td>
                <td>Full CRUD</td>
            </tr>
            <tr>
                <td>Carts</td>
                <td>GET/POST/PUT/DELETE</td>
                <td>/carts</td>
                <td>Cart operations</td>
            </tr>
            <tr>
                <td>Cart Items</td>
                <td>GET/POST/PUT/DELETE</td>
                <td>/carts/{cartId}/items</td>
                <td>Cart item management</td>
            </tr>
        </tbody>
    </table>

    <h2>Testing</h2>
    <pre><code># Run unit tests
mvn test

# Run integration tests
mvn test -P integration-test</code></pre>

    <h2>Environment Variables</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>Variable</th>
                <th>Description</th>
                <th>Default</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>SPRING_DATASOURCE_URL</td>
                <td>MySQL connection URL</td>
                <td>jdbc:mysql://localhost:3306/ecommerce</td>
            </tr>
            <tr>
                <td>SPRING_DATASOURCE_USERNAME</td>
                <td>Database username</td>
                <td>-</td>
            </tr>
            <tr>
                <td>SPRING_DATASOURCE_PASSWORD</td>
                <td>Database password</td>
                <td>-</td>
            </tr>
            <tr>
                <td>SERVER_PORT</td>
                <td>Application port</td>
                <td>8080</td>
            </tr>
        </tbody>
    </table>

    <h2>Key Highlights</h2>
    <ul>
        <li><strong>Clean Architecture</strong>: Strict separation of concerns</li>
        <li><strong>Scalable Design</strong>: Ready for production deployment</li>
        <li><strong>Data Consistency</strong>: Robust price calculation and validation</li>
        <li><strong>RESTful APIs</strong>: Industry-standard practices</li>
        <li><strong>Error Handling</strong>: Global exception management</li>
    </ul>

    <h2>Future Enhancements</h2>
    <ul>
        <li>User authentication (JWT/OAuth2)</li>
        <li>Payment integration</li>
        <li>Swagger API documentation</li>
        <li>Redis caching</li>
        <li>Docker deployment</li>
        <li>Kubernetes orchestration</li>
    </ul>

    <h2>Support</h2>
    <p><strong>Author</strong>: Thulasiram<br>
    <strong>Email</strong>: your.email@example.com<br>
    <strong>GitHub</strong>: <a href="https://github.com/yourusername">yourusername</a></p>

    <h2>License</h2>
    <p>This project is open source and available under the <a href="LICENSE">MIT License</a>.</p>

    <hr>
    <div style="text-align: center;">
        <strong>Built with ❤️ for scalable e-commerce solutions</strong>
    </div>

</body>
</html>
