# Daily Quote Service 📝

A RESTful API service built with Spring Boot for managing and serving inspirational quotes. This project demonstrates core Spring Boot concepts including REST APIs, JPA, scheduled tasks, validation, exception handling, and security.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## 🌟 Features

- **CRUD Operations**: Complete Create, Read, Update, Delete functionality for quotes
- **Daily Quote System**: Automatically selects a new quote every day at midnight
- **Random Quote Generator**: Get random inspirational quotes
- **Author Search**: Find quotes by specific authors
- **Input Validation**: Ensures data integrity with Bean Validation
- **Error Handling**: Comprehensive exception handling with meaningful error messages
- **Security**: Spring Security configuration with CORS support
- **In-Memory Database**: H2 database for quick setup and testing
- **Health Monitoring**: Spring Actuator endpoints for application health
- **Automated Testing**: Integration tests included
- **Daily Workflows**: GitHub Actions for maintaining activity

## 🛠️ Tech Stack

- **Java 21** (LTS)
- **Spring Boot 3.3.0**
- **Spring Data JPA** - Data persistence
- **Spring Security** - Security configuration
- **H2 Database** - In-memory database
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management
- **JUnit 5** - Testing framework

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+ (or use the Maven wrapper included)
- Git

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/w-dante/daily-quote-service.git
cd daily-quote-service
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Run Tests

```bash
mvn test
```

## 📡 API Endpoints

### Quote Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/quotes` | Get all quotes |
| GET | `/api/v1/quotes/{id}` | Get quote by ID |
| POST | `/api/v1/quotes` | Create a new quote |
| PUT | `/api/v1/quotes/{id}` | Update existing quote |
| DELETE | `/api/v1/quotes/{id}` | Delete a quote |
| GET | `/api/v1/quotes/daily` | Get the daily quote |
| GET | `/api/v1/quotes/random` | Get a random quote |
| GET | `/api/v1/quotes/author/{author}` | Get quotes by author |
| GET | `/api/v1/quotes/authors` | Get all unique authors |

### Health Check

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/actuator/health` | Application health status |

## 📝 API Examples

### Create a Quote

```bash
curl -X POST http://localhost:8080/api/v1/quotes \
  -H "Content-Type: application/json" \
  -d '{
    "text": "The only way to do great work is to love what you do.",
    "author": "Steve Jobs"
  }'
```

### Get Daily Quote

```bash
curl http://localhost:8080/api/v1/quotes/daily
```

### Get Random Quote

```bash
curl http://localhost:8080/api/v1/quotes/random
```

### Get All Quotes

```bash
curl http://localhost:8080/api/v1/quotes
```

### Get Quotes by Author

```bash
curl http://localhost:8080/api/v1/quotes/author/Steve%20Jobs
```

## 🗂️ Project Structure

```
daily-quote-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/quote/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── exception/       # Custom exceptions & handlers
│   │   │   ├── model/           # Entity classes
│   │   │   ├── repository/      # JPA repositories
│   │   │   ├── service/         # Business logic
│   │   │   └── DailyQuoteServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Test classes
├── .github/workflows/           # GitHub Actions workflows
├── pom.xml                      # Maven configuration
└── README.md
```

## 🔐 Security Features

- **CSRF Protection**: Enabled for all endpoints
- **CORS Configuration**: Configured to allow specific origins
- **Stateless Sessions**: Using JWT-ready stateless session management
- **Input Validation**: Bean validation on all input data
- **SQL Injection Protection**: JPA/Hibernate parameterized queries
- **XSS Protection**: Response headers configured appropriately

## ⏰ Scheduled Tasks

The application includes a scheduled task that runs daily at midnight (00:00) to automatically select a new "Daily Quote" from the database.

```java
@Scheduled(cron = "0 0 0 * * *")
public void updateDailyQuote() {
    // Selects a random quote as the daily quote
}
```

## 🤖 GitHub Actions Workflows

### Daily Activity Workflow
- **Schedule**: Runs every day at 9 AM UTC
- **Purpose**: Maintains repository activity by running tests and updating activity log
- **File**: `.github/workflows/daily-activity.yml`

### CI/CD Pipeline
- **Trigger**: On push to main/develop branches and pull requests
- **Purpose**: Builds project and runs tests automatically
- **File**: `.github/workflows/ci-cd.yml`

## 🔍 H2 Database Console

Access the H2 database console at: `http://localhost:8080/h2-console`

**Connection Details:**
- JDBC URL: `jdbc:h2:mem:quotedb`
- Username: `sa`
- Password: *(leave empty)*

## 📊 Sample Data

The application initializes with 10 inspirational quotes from various authors including:
- Steve Jobs
- Eleanor Roosevelt
- Albert Einstein
- Winston Churchill
- And more!

## 🧪 Testing

The project includes integration tests for the REST API endpoints. Run tests with:

```bash
mvn test
```

Test coverage includes:
- CRUD operations
- Validation scenarios
- Exception handling
- Daily and random quote endpoints

## 🔧 Configuration

Key configuration properties in `application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:h2:mem:quotedb
spring.jpa.hibernate.ddl-auto=update
management.endpoints.web.exposure.include=health,info
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📈 Future Enhancements

- [ ] Add pagination for quote listing
- [ ] Implement user authentication and authorization
- [ ] Add quote categories/tags
- [ ] Create a rating system for quotes
- [ ] Add PostgreSQL support for production
- [ ] Implement caching with Redis
- [ ] Add Swagger/OpenAPI documentation
- [ ] Create a frontend UI

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

Your Name
- GitHub: [@w-dante](https://github.com/w-dante)
- LinkedIn: [Danté Whyte](https://linkedin.com/in/dante-whyte)

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- All the great minds whose quotes inspire us daily

---

⭐ **Star this repository if you find it helpful!**

📧 **Questions?** Feel free to open an issue or reach out!
