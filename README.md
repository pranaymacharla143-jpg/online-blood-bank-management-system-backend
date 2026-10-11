# Online Blood Bank Management System — Backend

## 📌 Project Overview

The **Online Blood Bank Management System** is a Java-based backend application developed using Spring Boot. It provides REST APIs to manage blood donors, patients, blood donations, blood requests, and blood inventory.

The backend connects to a MySQL database using Spring Data JPA and Hibernate. It also provides administrator authentication using Spring Security and JSON Web Tokens (JWT).

The application helps maintain blood inventory records, process donation and blood request approvals, and provide blood availability information to the frontend.

## 🚀 Features

- **Donor Management:** Register, retrieve, update, and delete donor records.
- **Patient Management:** Register and manage patient information.
- **Blood Inventory Management:** Add, retrieve, update, and delete blood stock records.
- **Blood Availability Search:** Search inventory by blood group.
- **Donation Management:** Create and manage blood donation records.
- **Donation Approval:** Approve or reject submitted donations.
- **Blood Request Management:** Create and manage patient blood requests.
- **Blood Request Approval:** Approve or reject blood requests.
- **Automatic Inventory Updates:** Increase inventory after donation approval and decrease inventory after an approved blood request, subject to available stock.
- **Admin Management:** Manage administrator records.
- **JWT Authentication:** Authenticate administrators and protect secured API endpoints.
- **REST API Integration:** Communicate with the React frontend using HTTP requests and JSON.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Backend programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access |
| Hibernate | ORM and entity management |
| MySQL | Relational database |
| Spring Security | API access control |
| JWT | Token-based authentication |
| BCrypt | Password hashing |
| Maven | Dependency management and build |
| React.js | Frontend integration |

## 📂 Project Structure

```text
blood-bank-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── blood_bank_management/
│   │   │       ├── controller/
│   │   │       │   ├── DonorController.java
│   │   │       │   ├── PatientController.java
│   │   │       │   ├── BloodInventoryController.java
│   │   │       │   ├── DonationController.java
│   │   │       │   ├── BloodRequestController.java
│   │   │       │   ├── AdminController.java
│   │   │       │   └── LoginController.java
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── entity/
│   │   │       ├── dto/
│   │   │       ├── config/
│   │   │       └── util/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

*Note: This structure represents the main project packages and files. Your local project may contain additional files.*

## ⚙️ Prerequisites

Install the following software:

- JDK 17
- MySQL Server
- Visual Studio Code, IntelliJ IDEA, or another Java IDE
- Maven, if not using the included Maven Wrapper
- Postman for optional API testing

## 🗄️ Database Configuration

Create the MySQL database:

```sql
CREATE DATABASE blood_bank_db;
```

Select the database:

```sql
USE blood_bank_db;
```

Configure the connection in `src/main/resources/application.properties`:

```properties
spring.application.name=blood-bank-management

spring.datasource.url=jdbc:mysql://localhost:3306/blood_bank_db
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

**Important:** Replace the database username and password with your own MySQL credentials. The configuration above uses the local development credentials and should not be used unchanged in a public production deployment.

Hibernate creates or updates mapped database tables when the application starts, based on the configured entity classes.

## 📥 Installation

**1. Clone the backend repository**

```bash
git clone YOUR_BACKEND_GITHUB_REPOSITORY_URL
```

Replace the placeholder with your actual backend repository URL.

**2. Navigate into the project**

```bash
cd blood-bank-management
```

**3. Build the project**

On Windows:

```cmd
mvnw.cmd clean package
```

Alternatively, if Maven is installed:

```bash
mvn clean package
```

## ▶️ Run the Backend

On Windows, run:

```cmd
mvnw.cmd spring-boot:run
```

Alternatively:

```bash
mvn spring-boot:run
```

After successful startup, the backend runs at:

```text
http://localhost:8080
```

The base API URL is:

```text
http://localhost:8080/api
```

Ensure that MySQL is running and the database credentials are correct.

## 🔗 REST API Endpoints

The following endpoints are implemented in the project.

### 1. Donor Management

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/donors` | Register a donor |
| GET | `/api/donors` | Retrieve all donors |
| GET | `/api/donors/{id}` | Retrieve a donor by ID |
| PUT | `/api/donors/{id}` | Update donor details |
| DELETE | `/api/donors/{id}` | Delete a donor |

### 2. Patient Management

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/patients` | Register a patient |
| GET | `/api/patients` | Retrieve all patients |
| GET | `/api/patients/{id}` | Retrieve a patient by ID |
| PUT | `/api/patients/{id}` | Update patient details |
| DELETE | `/api/patients/{id}` | Delete a patient |

### 3. Blood Inventory

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/inventory` | Add inventory |
| GET | `/api/inventory` | Retrieve all inventory |
| GET | `/api/inventory/{id}` | Retrieve inventory by ID |
| GET | `/api/inventory/search/{bloodGroup}` | Search by blood group |
| PUT | `/api/inventory/{id}` | Update inventory |
| DELETE | `/api/inventory/{id}` | Delete inventory |

### 4. Donation Management

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/don
