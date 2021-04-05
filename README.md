# webflux-redis-example
[![Build](https://github.com/gregwhitaker/webflux-redis-example/actions/workflows/gradle.yml/badge.svg)](https://github.com/gregwhitaker/webflux-redis-example/actions/workflows/gradle.yml)

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

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/webflux-redis-example/issues).

## License
MIT License

Copyright (c) 2021 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
