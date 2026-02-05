<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<h1 align="center">E-Commerce Backend Application</h1>
<h3 align="center">Java | Spring Boot | MySQL</h3>

<p align="center">
Production-ready backend system for managing products, categories, carts, cart items, images, and pricing logic
</p>

<hr>

<h2>📜 Table of Contents</h2>
<ul>
  <li><a href="#project-overview">📌 Project Overview</a></li>
  <li><a href="#features">✨ Features</a></li>
  <li><a href="#architecture">🏗️ Architecture</a></li>
  <li><a href="#api-endpoints">🔗 API Endpoints</a></li>
  <li><a href="#database">🗄️ Database Design</a></li>
  <li><a href="#configuration">⚙️ Configuration</a></li>
  <li><a href="#technologies">🛠️ Technologies Used</a></li>
</ul>

<hr>

<h2 id="project-overview">📌 Project Overview</h2>

<p>
This project implements a <strong>complete E-Commerce backend system</strong> using
<strong>Java and Spring Boot</strong>.  
It provides RESTful APIs to manage products, categories, shopping carts, cart items,
product images, and centralized price calculation logic.
</p>

<ul>
  <li>Clean layered architecture</li>
  <li>Production-ready REST APIs</li>
  <li>MySQL database with JPA/Hibernate</li>
  <li>Centralized business logic</li>
</ul>

<hr>

<h2 id="features">✨ Features</h2>

<ul>
  <li>Product management with full CRUD operations</li>
  <li>Category management with product association</li>
  <li>Shopping cart and cart item handling</li>
  <li>Real-time cart total calculation</li>
  <li>Multiple images per product</li>
  <li>Centralized pricing and business rules</li>
  <li>Global exception handling with consistent responses</li>
</ul>

<hr>

<h2 id="architecture">🏗️ System Architecture (High Level)</h2>

<pre>
Client
  |
  v
Spring Boot REST Controller
  |
  v
Service Layer (Business Logic)
  |
  v
Repository Layer (JPA)
  |
  v
MySQL Database
</pre>

<p>
<strong>Design principle:</strong><br>
Business logic is centralized in the service layer to ensure
data consistency and maintainability.
</p>

<hr>

<h2 id="api-endpoints">🔗 API Endpoints</h2>

<h3>1️⃣ Product Management</h3>
<pre>
GET    /api/products
POST   /api/products
GET    /api/products/{id}
PUT    /api/products/{id}
DELETE /api/products/{id}
</pre>

<h3>2️⃣ Category Management</h3>
<pre>
GET    /api/categories
POST   /api/categories
</pre>

<h3>3️⃣ Cart Management</h3>
<pre>
POST   /api/carts
GET    /api/carts/{id}
</pre>

<h3>4️⃣ Cart Item Management</h3>
<pre>
POST   /api/cart-items
PUT    /api/cart-items/{id}
DELETE /api/cart-items/{id}
</pre>

<hr>

<h2 id="database">🗄️ Database Design</h2>

<pre>
products (
  id BIGINT PRIMARY KEY,
  name VARCHAR(255),
  price DOUBLE,
  category_id BIGINT
)

categories (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100)
)

carts (
  id BIGINT PRIMARY KEY,
  total_price DOUBLE
)

cart_items (
  id BIGINT PRIMARY KEY,
  cart_id BIGINT,
  product_id BIGINT,
  quantity INT,
  subtotal DOUBLE
)

images (
  id BIGINT PRIMARY KEY,
  product_id BIGINT,
  image_url VARCHAR(255)
)
</pre>

<hr>

<h2 id="configuration">⚙️ Configuration</h2>

<p><strong>application.properties</strong></p>

<pre>
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
</pre>

<hr>

<h2 id="technologies">🛠️ Technologies Used</h2>

<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>Spring Data JPA (Hibernate)</li>
  <li>MySQL</li>
  <li>RESTful APIs</li>
  <li>Maven</li>
  <li>Git & GitHub</li>
</ul>

<hr>

<p align="center">
<strong>Built by Thulasiram</strong>
</p>

</body>
</html>
