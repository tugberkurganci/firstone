# Apsiyon ServEase

Apsiyon Wallet is a robust and versatile solution designed to streamline and manage various tasks within Apsiyon ServEase. This project leverages modern technologies to deliver a comprehensive system that includes a Spring Boot application for backend services, a Node.js service utilizing the Circle SDK for blockchain-based payment transactions, and a React front-end built with Vite for the user interface. This document provides thorough information on the setup, configuration, APIs, and Docker usage, ensuring you have everything needed to get started and contribute effectively.

## Table of Contents

- [Technologies Used](#technologies-used)
  - [Back-End Technologies Used](#back-end-technologies-used)
  - [Front-End Technologies Used](#front-end-technologies-used)
- [Setup](#setup)
  - [Back-End Setup](#back-end-setup)
  - [Front-End Setup](#front-end-setup)
- [API Documentation](#api-documentation)
  - [User Management](#user-management)
  - [Request Management](#request-management)
  - [Survey Management](#survey-management)
  - [Taxi Stand Information](#taxi-stand-information)
  - [Vehicle Management](#vehicle-management)
  - [Create Wallet](#create-wallet)
  - [Check Balance](#check-balance)
- [Docker Usage](#docker-usage)
  - [Dockerfile](#dockerfile)
  - [Building and Running Docker Image](#building-and-running-docker-image)
- [Deployment](#deployment)
- [Contacts](#contacts)
- [License](#license)

## Technologies Used

### Back-End Technologies Used

- **Java 17**
- **Spring Boot 3.3.1**
- **Spring Data JPA**
- **Spring Web**
- **Spring Security**
- **PostgreSQL**
- **Lombok**
- **OkHttp**
- **JSON**
- **SpringDoc OpenAPI**
- **Node.js 20+**
- **Circle SDK (Node.js)**

### Front-End Technologies Used

- **@reduxjs/toolkit: "^2.2.6"**
- **axios: "^1.7.2"**
- **bootstrap: "^5.3.3"**
- **formik: "^2.4.6"**
- **react: "^18.3.1"**
- **react-bootstrap: "^2.10.4"**
- **react-dom: "^18.3.1"**
- **react-modal: "^3.16.1"**
- **react-redux: "^9.1.2"**
- **react-router-dom: "^6.25.1"**
- **redux-toolkit: "^1.1.2"**
- **yup: "^1.4.0"**

## Setup

### Back-End Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/tugberkurganci/firstone.git
   cd server
   ```

2. **Configure the database:**

   - Create a PostgreSQL database.
   - Update the `application.properties` file with your database credentials:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
     spring.datasource.username=your-username
     spring.datasource.password=your-password

     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
     spring.jpa.hibernate.ddl-auto=update

     google.api.key=your-google-api-key
     ```

3. **Run the backend application:**
   ```bash
   ./mvnw spring-boot:run
   ```

### Front-End Setup

1. **Clone the repository:**

   ```bash
   https://github.com/tugberkurganci/firstone.git
   cd client
   ```

2. **Install the dependencies:**

   ```bash
   npm install
   ```

3. **Start the development server:**

   ```bash
   npm start
   ```

## API Documentation

### User Management

- **URL:** `/api/users`
- **Method:** `POST`
- **Request:**

```json
{
  "email": "user@example.com",
  "password": "SecureP@ssw0rd",
  "role": "e"
}
```

- **Response:**

```json
{
  "id": 1,
  "email": "user@example.com",
  "role": "EMPLOYEE"
}
```

### Request Management

- **URL:** `/api/requests`
- **Method:** `POST`
- **Request:**

```json
{
  "userId": 1,
  "serviceId": 2,
  "startTime": "2024-07-01T10:00:00",
  "endTime": "2024-07-01T12:00:00",
  "started": false,
  "status": "PENDING"
}
```

- **Response:**

```json
{
  "id": 1,
  "userId": 1,
  "serviceId": 2,
  "startTime": "2024-07-01T10:00:00",
  "endTime": "2024-07-01T12:00:00",
  "started": false,
  "status": "PENDING"
}
```

### Survey Management

- **URL:** `/api/surveys`
- **Method:** `POST`
- **Request:**

```json
{
  "surveyTitle": "Satisfaction Survey",
  "surveyDescription": "Are you satisfied with our services?",
  "creationTime": "2024-07-01T09:00:00",
  "creatorId": 1
}
```

- **Response:**

```json
{
  "id": 1,
  "surveyTitle": "Satisfaction Survey",
  "surveyDescription": "Are you satisfied with our services?",
  "creationTime": "2024-07-01T09:00:00",
  "creatorId": 1
}
```

### Taxi Stand Information

- **URL:** `/api/taxi-stands`
- **Method:** `POST`
- **Request:**

```json
{
  "includedTypes": ["taxi_stand"],
  "maxResultCount": 5,
  "locationRestriction": {
    "circle": {
      "center": {
        "latitude": 40.9357,
        "longitude": 29.155
      },
      "radius": 2000.0
    }
  }
}
```

- **Response:**

```json
{
  "places": [
    {
      "nationalPhoneNumber": "0212000000",
      "internationalPhoneNumber": "+902120000000",
      "formattedAddress": "Taxi Stand, Istanbul",
      "location": {
        "latitude": 40.9357,
        "longitude": 29.155
      },
      "googleMapsUri": "http://maps.google.com/?q=40.9357,29.1550",
      "displayName": {
        "text": "Istanbul Taxi Stand",
        "languageCode": "tr"
      }
    }
  ]
}
```

### Vehicle Management

- **URL:** `/api/vehicles`
- **Method:** `POST`
- **Request:**

```json
{
  "userId": 1,
  "vehicleMake": "Toyota",
  "vehicleModel": "Corolla",
  "licensePlate": "34ABC34",
  "registrationTime": "2024-07-01T09:00:00"
}
```

- **Response:**

```json
{
  "id": 1,
  "userId": 1,
  "vehicleMake": "Toyota",
  "vehicleModel": "Corolla",
  "licensePlate": "34ABC34",
  "registrationTime": "2024-07-01T09:00:00"
}
```

### Create Wallet

- **URL:** `/api/wallets`
- **Method:** `POST`
- **Request:**

```json
{
  "userId": "user_id"
}
```

- **Response:**

```json
{
  "walletId": "wallet_id",
  "balance": 0
}
```

### Check Balance

- **URL:** `/api/wallets/{walletId}/balance`
- **Method:** `GET`
- **Response:**

```json
{
  "walletId": "wallet_id",
  "balance": 1000
}
```

## Docker Usage

### Dockerfile

```Dockerfile
FROM maven:3.8.5-openjdk-17 AS build
COPY .. .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/*.jar demo.jar
RUN mkdir /uploads && chmod -R a+rw /uploads
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]
```

### Building and Running Docker Image

**Build the Docker image:**

```sh
docker build -t apsiyon-tb .
```

**Run the Docker container:**

```sh
docker run -p 8080:8080 apsiyon-tb
```

## Deployment

[https://tugberkurganci.github.io/importante/](https://tugberkurganci.github.io/importante/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contacts

### Tuğberk Urgancı

<a href="https://github.com/tugberkurganci" target="_blank">
<img  src=https://img.shields.io/badge/github-%2324292e.svg?&style=for-the-badge&logo=github&logoColor=white alt=github style="margin-bottom: 20px;" />
</a>
<a href = "mailto:tugberkurganci@gmail.com?subject = Feedback&body = Message">
<img src=https://img.shields.io/badge/send-email-email?&style=for-the-badge&logo=microsoftoutlook&color=CD5C5C alt=microsoftoutlook style="margin-bottom: 20px; margin-left:20px" />
</a>
<a href="https://www.linkedin.com/in/tu%C4%9Fberk-urganc%C4%B1-b01855265/" target="_blank">
<img src=https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white alt=linkedin style="margin-bottom: 20px; margin-left:20px" />
</a>

### Bedirhan Balcı

<a href="https://github.com/bedirhanbalci" target="_blank">
<img  src=https://img.shields.io/badge/github-%2324292e.svg?&style=for-the-badge&logo=github&logoColor=white alt=github style="margin-bottom: 20px;" />
</a>
<a href = "mailto:bedirhanbalci@outlook.com?subject = Feedback&body = Message">
<img src=https://img.shields.io/badge/send-email-email?&style=for-the-badge&logo=microsoftoutlook&color=CD5C5C alt=microsoftoutlook style="margin-bottom: 20px; margin-left:20px" />
</a>
<a href="https://www.linkedin.com/in/bedirhanbalci/" target="_blank">
<img src=https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white alt=linkedin style="margin-bottom: 20px; margin-left:20px" />
</a>
