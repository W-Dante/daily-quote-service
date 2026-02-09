# Setup Guide

This guide will help you set up the Daily Quote Service project on your local machine.

## Prerequisites Installation

### 1. Install Java 17

**Windows:**
1. Download Java 17 from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
2. Run the installer
3. Verify installation: `java -version`

**macOS:**
```bash
brew install openjdk@17
```

**Linux:**
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

### 2. Install Maven (Optional - project includes Maven Wrapper)

**Windows:**
1. Download from [Maven website](https://maven.apache.org/download.cgi)
2. Extract and add to PATH

**macOS:**
```bash
brew install maven
```

**Linux:**
```bash
sudo apt install maven
```

## Project Setup

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/daily-quote-service.git
cd daily-quote-service
```

### Step 2: Build the Project

Using Maven Wrapper (recommended):
```bash
./mvnw clean install  # Linux/Mac
mvnw.cmd clean install  # Windows
```

Or with Maven:
```bash
mvn clean install
```

### Step 3: Run the Application

```bash
./mvnw spring-boot:run  # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

The application will start on `http://localhost:8080`

### Step 4: Verify Installation

1. Check application health:
```bash
curl http://localhost:8080/actuator/health
```

2. Get all quotes:
```bash
curl http://localhost:8080/api/v1/quotes
```

## IDE Setup

### IntelliJ IDEA

1. Open IntelliJ IDEA
2. Select `File -> Open` and choose the project directory
3. IntelliJ will automatically detect it's a Maven project
4. Wait for dependencies to download
5. Enable annotation processing: `Settings -> Build -> Compiler -> Annotation Processors`
6. Run the application using the green play button next to `DailyQuoteServiceApplication`

### Eclipse

1. Open Eclipse
2. Select `File -> Import -> Maven -> Existing Maven Projects`
3. Browse to the project directory
4. Click Finish
5. Wait for dependencies to download
6. Right-click on project -> `Run As -> Spring Boot App`

### VS Code

1. Install Java Extension Pack
2. Install Spring Boot Extension Pack
3. Open the project folder
4. Press `F5` to run or use the Spring Boot Dashboard

## Database Access

Access the H2 Console at: `http://localhost:8080/h2-console`

**Credentials:**
- JDBC URL: `jdbc:h2:mem:quotedb`
- Username: `sa`
- Password: (leave empty)

## Testing the API

### Using cURL

```bash
# Get all quotes
curl http://localhost:8080/api/v1/quotes

# Create a quote
curl -X POST http://localhost:8080/api/v1/quotes \
  -H "Content-Type: application/json" \
  -d '{"text":"Your quote here", "author":"Author Name"}'
```

### Using Postman

1. Import the endpoints manually or use the examples in README.md
2. Set base URL: `http://localhost:8080`
3. Test each endpoint

## Running Tests

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=QuoteControllerIntegrationTest

# Run with coverage
./mvnw test jacoco:report
```

## Common Issues and Solutions

### Issue: Port 8080 already in use

**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Maven dependencies not downloading

**Solution:**
```bash
./mvnw clean install -U
```

### Issue: Lombok not working

**Solution:**
1. Enable annotation processing in your IDE
2. Install Lombok plugin for your IDE

### Issue: Tests failing

**Solution:**
```bash
# Clean and rebuild
./mvnw clean install

# Skip tests during build
./mvnw clean install -DskipTests
```

## GitHub Actions Setup

1. Fork the repository
2. Enable GitHub Actions in your fork
3. Workflows will run automatically on:
   - Push to main/develop branches
   - Daily at scheduled times
   - Manual trigger via Actions tab

## Next Steps

1. Review the [README.md](README.md) for API documentation
2. Check out the code structure
3. Try creating new endpoints
4. Explore the scheduled task functionality
5. Add new features!

## Getting Help

- Check the [README.md](README.md)
- Review existing issues on GitHub
- Create a new issue if needed

Happy coding! 🚀
