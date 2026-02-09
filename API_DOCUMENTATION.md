# API Documentation

## Base URL
```
http://localhost:8080/api/v1
```

## Authentication
Currently, the API does not require authentication for most endpoints. Security can be enhanced in future versions.

---

## Endpoints

### 1. Get All Quotes

**GET** `/quotes`

Returns a list of all quotes in the system.

**Response Example:**
```json
[
  {
    "id": 1,
    "text": "The only way to do great work is to love what you do.",
    "author": "Steve Jobs",
    "createdAt": "2024-02-09T10:00:00",
    "isDailyQuote": true
  }
]
```

**Status Codes:**
- `200 OK` - Success

---

### 2. Get Quote by ID

**GET** `/quotes/{id}`

Returns a specific quote by its ID.

**Path Parameters:**
- `id` (Long) - The quote ID

**Response Example:**
```json
{
  "id": 1,
  "text": "The only way to do great work is to love what you do.",
  "author": "Steve Jobs",
  "createdAt": "2024-02-09T10:00:00",
  "isDailyQuote": true
}
```

**Status Codes:**
- `200 OK` - Success
- `404 NOT FOUND` - Quote not found

**Error Response:**
```json
{
  "timestamp": "2024-02-09T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Quote not found with id: 999"
}
```

---

### 3. Create Quote

**POST** `/quotes`

Creates a new quote.

**Request Body:**
```json
{
  "text": "Your inspirational quote here. Must be 10-500 characters.",
  "author": "Author Name"
}
```

**Validation Rules:**
- `text`: Required, 10-500 characters
- `author`: Required, max 100 characters

**Response Example:**
```json
{
  "id": 11,
  "text": "Your inspirational quote here. Must be 10-500 characters.",
  "author": "Author Name",
  "createdAt": "2024-02-09T10:00:00",
  "isDailyQuote": false
}
```

**Status Codes:**
- `201 CREATED` - Success
- `400 BAD REQUEST` - Validation error

**Validation Error Response:**
```json
{
  "timestamp": "2024-02-09T10:00:00",
  "status": 400,
  "error": "Validation Failed",
  "errors": {
    "text": "Quote must be between 10 and 500 characters",
    "author": "Author cannot be empty"
  }
}
```

---

### 4. Update Quote

**PUT** `/quotes/{id}`

Updates an existing quote.

**Path Parameters:**
- `id` (Long) - The quote ID

**Request Body:**
```json
{
  "text": "Updated quote text here.",
  "author": "Updated Author"
}
```

**Response Example:**
```json
{
  "id": 1,
  "text": "Updated quote text here.",
  "author": "Updated Author",
  "createdAt": "2024-02-09T10:00:00",
  "isDailyQuote": false
}
```

**Status Codes:**
- `200 OK` - Success
- `404 NOT FOUND` - Quote not found
- `400 BAD REQUEST` - Validation error

---

### 5. Delete Quote

**DELETE** `/quotes/{id}`

Deletes a quote by ID.

**Path Parameters:**
- `id` (Long) - The quote ID

**Response:** No content

**Status Codes:**
- `204 NO CONTENT` - Success
- `404 NOT FOUND` - Quote not found

---

### 6. Get Daily Quote

**GET** `/quotes/daily`

Returns the current quote of the day. This quote is automatically updated every day at midnight.

**Response Example:**
```json
{
  "id": 1,
  "text": "The only way to do great work is to love what you do.",
  "author": "Steve Jobs",
  "createdAt": "2024-02-09T10:00:00",
  "isDailyQuote": true
}
```

**Status Codes:**
- `200 OK` - Success
- `404 NOT FOUND` - No daily quote available

---

### 7. Get Random Quote

**GET** `/quotes/random`

Returns a random quote from the database.

**Response Example:**
```json
{
  "id": 5,
  "text": "It is during our darkest moments that we must focus to see the light.",
  "author": "Aristotle",
  "createdAt": "2024-02-09T10:00:00",
  "isDailyQuote": false
}
```

**Status Codes:**
- `200 OK` - Success
- `404 NOT FOUND` - No quotes available

---

### 8. Get Quotes by Author

**GET** `/quotes/author/{author}`

Returns all quotes by a specific author.

**Path Parameters:**
- `author` (String) - Author name (URL encoded if contains spaces)

**Example Request:**
```
GET /quotes/author/Steve%20Jobs
```

**Response Example:**
```json
[
  {
    "id": 1,
    "text": "The only way to do great work is to love what you do.",
    "author": "Steve Jobs",
    "createdAt": "2024-02-09T10:00:00",
    "isDailyQuote": true
  },
  {
    "id": 2,
    "text": "Innovation distinguishes between a leader and a follower.",
    "author": "Steve Jobs",
    "createdAt": "2024-02-09T10:00:00",
    "isDailyQuote": false
  }
]
```

**Status Codes:**
- `200 OK` - Success (returns empty array if no quotes found)

---

### 9. Get All Authors

**GET** `/quotes/authors`

Returns a list of all unique authors in the database.

**Response Example:**
```json
[
  "Albert Einstein",
  "Aristotle",
  "Eleanor Roosevelt",
  "Steve Jobs",
  "Winston Churchill"
]
```

**Status Codes:**
- `200 OK` - Success

---

## Health Check Endpoints

### Application Health

**GET** `/actuator/health`

Returns the health status of the application.

**Response Example:**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "H2",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP"
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

**Status Codes:**
- `200 OK` - Application is healthy
- `503 SERVICE UNAVAILABLE` - Application is unhealthy

---

## Testing with cURL

### Create a Quote
```bash
curl -X POST http://localhost:8080/api/v1/quotes \
  -H "Content-Type: application/json" \
  -d '{
    "text": "Life is what happens when you are busy making other plans.",
    "author": "John Lennon"
  }'
```

### Get All Quotes
```bash
curl http://localhost:8080/api/v1/quotes
```

### Get Quote by ID
```bash
curl http://localhost:8080/api/v1/quotes/1
```

### Update a Quote
```bash
curl -X PUT http://localhost:8080/api/v1/quotes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "text": "Updated quote text goes here.",
    "author": "Updated Author"
  }'
```

### Delete a Quote
```bash
curl -X DELETE http://localhost:8080/api/v1/quotes/1
```

### Get Daily Quote
```bash
curl http://localhost:8080/api/v1/quotes/daily
```

### Get Random Quote
```bash
curl http://localhost:8080/api/v1/quotes/random
```

### Get Quotes by Author
```bash
curl http://localhost:8080/api/v1/quotes/author/Steve%20Jobs
```

### Get All Authors
```bash
curl http://localhost:8080/api/v1/quotes/authors
```

---

## Common HTTP Status Codes

| Code | Meaning | Description |
|------|---------|-------------|
| 200 | OK | Request successful |
| 201 | Created | Resource created successfully |
| 204 | No Content | Request successful, no content returned |
| 400 | Bad Request | Invalid request data or validation error |
| 404 | Not Found | Resource not found |
| 500 | Internal Server Error | Server error |

---

## Data Models

### Quote Request (Input)
```json
{
  "text": "string (10-500 chars, required)",
  "author": "string (max 100 chars, required)"
}
```

### Quote Response (Output)
```json
{
  "id": "long",
  "text": "string",
  "author": "string",
  "createdAt": "datetime (ISO 8601)",
  "isDailyQuote": "boolean"
}
```

### Error Response
```json
{
  "timestamp": "datetime (ISO 8601)",
  "status": "integer",
  "error": "string",
  "message": "string"
}
```
