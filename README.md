# HR System

A comprehensive Human Resources Management System built with Spring Boot.

## Prerequisites

- Java 17 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

## Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/Ashurhan/HR-system.git
cd HR-system
```

2. Create a new file `src/main/resources/application.properties` based on the template:
```bash
cp src/main/resources/application.properties.template src/main/resources/application.properties
```

3. Configure the following environment variables in your `application.properties`:
- `DB_USERNAME`: Your PostgreSQL username
- `DB_PASSWORD`: Your PostgreSQL password
- `JWT_SECRET`: A secure random string for JWT token signing
- `MAIL_USERNAME`: Your email address for sending notifications
- `MAIL_PASSWORD`: Your email password or app-specific password
- `GOOGLE_CLIENT_ID`: Your Google OAuth2 client ID
- `GOOGLE_CLIENT_SECRET`: Your Google OAuth2 client secret
- `RAPIDAPI_KEY`: Your RapidAPI key

4. Create the database:
```sql
CREATE DATABASE hr;
```

5. Build and run the application:
```bash
mvn clean install
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## API Documentation

Once the application is running, you can access the Swagger UI documentation at:
`http://localhost:8080/swagger-ui.html`

## Security

This project uses several security measures:
- JWT-based authentication
- Password encryption
- OAuth2 integration with Google
- Secure session management

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 