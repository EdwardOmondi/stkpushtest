# Backend Setup
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
### Prerequisites
To run this application, you will need the following installed:
1. Java Development Kit (JDK) version 17 or above
2. Apache Maven version 3.x or above. You can also use the maven wrapper that comes with spring-boot applications.
3. Postgres database instance (development was done with v14). You can install and run it locally or use a remote instance.
### General setup
1. Clone the repository to your local machine:
```
git clone https://projects.technovation.co.ke/git/amims.git
```
2. Update the application.properties file in the src/main/resources directory with your Mpesa and Postgres configuration
```
mpesa.stkpush.url=https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest
mpesa.consumer.key=[YOU_CONSUMER_KEY]
mpesa.consumer.secret=[YOUR_CONSUMER_SECRET]
mpesa.passkey=[YOUR_PASS_KEY]
mpesa.callback.url=[YOUR_CALLBACK_URL]
# if you have no paybill, you can use 174379 to test
mpesa.paybill=[YOUR_PAYBILL]

spring.datasource.url=jdbc:postgresql://[YOUR_POSTGRES_URL]:[YOUR_POSTGRES_PORT]/stkpushtest
spring.datasource.username=stkpushtest
spring.datasource.password=stkpushtest
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create
```
3. Build the application using Maven:
```
mvn clean install
```
or if you dont have maven installed
```
./mvnw clean install
```
4. Run the application using the following command:
```
java -jar target/backend-0.0.1-SNAPSHOT.jar.jar
```
or directly with maven 
```
mvn spring-boot:run
```
or
```
./mvnw spring-boot:run
```
## Usage
Once the application is up and running, you can access it at http://localhost:8080
## Appendix
### Postgres setup
If you already have postgres setup, run the following commands in your terminal
```
psql -U postgres
CREATE USER "stkpushtest" WITH PASSWORD 'stkpushtest';
CREATE database stkpushtest;
\connect stkpushtest;
GRANT CONNECT ON DATABASE stkpushtest TO "stkpushtest";
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO "stkpushtest";
GRANT USAGE ON SCHEMA public TO "stkpushtest";
```
### ngrock
You can use ngrok to expose your apis. Set up instructions can be found [here](https://dashboard.ngrok.com/get-started/setup)
Once setup, you can run it using the command below
```
ngrok http 8080
```
