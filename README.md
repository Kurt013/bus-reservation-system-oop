# Bus Reservation System

A desktop-based **Bus Reservation System** developed using **Java** and **Spring JDBC**. The application streamlines bus reservation management by allowing users to manage passengers, buses, routes, schedules, and ticket bookings through an intuitive desktop interface.

---

## 🚧 Project Status

**This project is currently under active development.**

While the core features are functional, the codebase is being continuously improved to enhance maintainability, scalability, and overall software quality.

Current development efforts include:

- Refactoring the codebase to improve readability and maintainability
- Restructuring the project following clean architecture principles
- Reducing code duplication and improving modularity
- Enhancing exception handling and input validation
- Optimizing database interactions
- Migrating the database from **MySQL** to **SQLite**

### Why SQLite?

The project is transitioning from **MySQL** to **SQLite** to better suit a desktop application environment. SQLite is a lightweight, serverless, file-based database that eliminates the need to install and configure a separate database server. This simplifies deployment, reduces setup time, and makes the application easier to distribute and use across different machines.

---

## Stable Version

The `main` branch is currently under active development and may contain incomplete features or breaking changes due to ongoing refactoring and the migration from MySQL to SQLite.

If you are looking for the latest fully functional and stable version of the application, please switch to the **`old-main`** branch:

```bash
git checkout old-main
```

The `old-main` branch contains the complete MySQL-based implementation of the Bus Reservation System prior to the current architectural improvements.


---

## Features

- Passenger registration and management
- Bus management
- Route management
- Schedule management
- Seat reservation and booking
- Ticket generation
- Reservation cancellation
- Search and filter reservations
- Database-driven data persistence
- User-friendly desktop interface

---

## Technologies Used

- Java
- Spring Framework (Spring JDBC)
- JDBC
- MySQL *(currently being migrated to SQLite)*
- Maven
- Java Swing

---

## Architecture

The application follows a layered architecture to promote separation of concerns.

```
Presentation Layer (Swing UI)
        │
        ▼
Business Logic / Service Layer
        │
        ▼
Data Access Layer (Spring JDBC)
        │
        ▼
Database (MySQL → SQLite)
```

---

## Prerequisites

Before running the project, ensure the following are installed:

- Java JDK 17 or later
- Maven 3.x
- MySQL Server *(current version only)*
- Git

> **Note:** Once the SQLite migration is complete, MySQL will no longer be required.

---

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/bus-reservation-system.git
```

### 2. Navigate to the project directory

```bash
cd bus-reservation-system
```

### 3. Configure the database

Create a MySQL database:

```sql
CREATE DATABASE bus_reservation_system;
```

Import the provided SQL file into the database.

Update your database credentials in the application's configuration.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bus_reservation_system
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

Launch the application from your IDE or execute the generated JAR file.

---

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dao/
│   │   ├── model/
│   │   ├── service/
│   │   ├── util/
│   │   └── ui/
│   └── resources/
├── test/
```

---

## Functional Modules

- Passenger Management
- Bus Management
- Route Management
- Schedule Management
- Reservation Management
- Ticket Management

---

## Planned Improvements

- Complete migration from MySQL to SQLite
- Improved application architecture
- Better code documentation
- Enhanced validation and exception handling
- PDF ticket generation
- Reservation history
- Reporting and analytics dashboard
- User authentication and role-based access control

---

## Screenshots

Add screenshots of the application here.

```
/screenshots
├── login.png
├── dashboard.png
├── reservation.png
├── buses.png
└── schedules.png
```

---

## License

This project was developed for educational and learning purposes.