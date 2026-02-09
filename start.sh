#!/bin/bash

# Daily Quote Service - Quick Start Script

echo "=================================="
echo "Daily Quote Service - Quick Start"
echo "=================================="
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java version must be 17 or higher. Current version: $JAVA_VERSION"
    exit 1
fi

echo "✅ Java version check passed"
echo ""

# Build the project
echo "📦 Building the project..."
./mvnw clean install -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed!"
    exit 1
fi

echo "✅ Build successful"
echo ""

# Run the application
echo "🚀 Starting the application..."
echo "   Access the API at: http://localhost:8080/api/v1/quotes"
echo "   H2 Console at: http://localhost:8080/h2-console"
echo "   Health Check at: http://localhost:8080/actuator/health"
echo ""
echo "Press Ctrl+C to stop the application"
echo ""

./mvnw spring-boot:run
