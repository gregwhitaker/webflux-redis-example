# webflux-redis-example
Example of retrieving data from [Redis](https://redis.io/) in a [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html) application.

## Building the Example
Run the following command to build the example application:

    ./gradlew clean build

## Running the Example
Follow the steps below to run the example:

1. Start the application and Redis database by running the following command:

        ./gradlew bootRunLocal

2. In a terminal run the following command to call the service and retrieve data for a product:

        curl "http://localhost:8080/products/1"

    If successful, you will see the following response in the terminal:

         {"id":"1","name":"Product1","description":"Product1 Description","active":true,"start_date":"2021-01-01T00:00Z","end_date":"2021-01-31T00:00Z"}