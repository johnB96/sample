# Transform an Integer to English via REST API

Simple REST API for translating any integer from Integer.MIN_VALUE to Integer.MAX_VALUE into English. For example:

```
intput: 4
output: Four
```

## Getting Started

### Prerequisites

```
java SDK
docker (for publishing and running)
```

### Installing

clone the repo

```
https://github.com/johnB96/sample.git
```

building using gradle

```
./gradlew build
```

run the Spring Boot application using gradle

```
./gradlew bootRun
```

## Examples

### HTTP

```
POST http://localhost:8080/transformToEnglish
Content-Type: application/json
{
    "input": 4
}
```

returns
```
{
    "input": 4,
    "output": "Four"
}
```

### Curl

```
curl --location --request POST 'http://localhost:8080/transformToEnglish' \
--header 'Content-Type: application/json' \
--data-raw '{"input": 4}'
```

returns
```
{"input":4,"output":"Four"}
```

## Running the tests

Run unit tests and publish jacoco coverage report using gradle
```
./gradlew build test
```

## Deployment

TODO deploy

## Built With

* [Spring Initializr](https://start.spring.io/) - Generates folders and basic conventions.
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used.
* [Gradle](https://gradle.org/) - Dependency and build management.

## Authors

* **John Bedalov** - *Initial work* - [johnB96](https://github.com/johnB96)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
