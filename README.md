# Transform an Integer to English via REST API

Simple REST API for translating any integer from Integer.MIN_VALUE to Integer.MAX_VALUE into English.

For example:

```
intput: 4
output: Four
```

## Getting Started

### Prerequisites

```
java SDK
docker (for publishing and running)
Google Cloud SDK (for deploying to google cloud)
```

### Installing

Clone the repo

```
git clone https://github.com/johnB96/sample.git
```

Building using gradle, after changing to the /sample directory

```
./gradlew build
```

Run the Spring Boot application using gradle, after changing to the /sample directory

```
./gradlew bootRun
```

## Examples

### HTTP

```
POST http://localhost:8080/transform
Content-Type: application/json
{
    "input": 4
}
```

returns
```
{
    "input": 4,
    "output": "Four",
    "language": "English"
}
```

### Curl

```
curl --location --request POST 'http://localhost:8080/transform' \
--header 'Content-Type: application/json' \
--data-raw '{"input": 4}'
```

returns
```
{"input":4,"output":"Four","language":"English"}
```

## Running the tests

Run unit tests and publish jacoco coverage report using gradle
```
./gradlew build test
```

## Deployment

### Google Cloud

Note, the follow sections assumes you have installed Google Cloud SDK. See: https://cloud.google.com/sdk/docs/

To deploy, from the /samples director run, see also: https://www.youtube.com/watch?v=qx_T6-EKkBE

```
gcloud app deploy build/libs/sample-1.0-SNAPSHOT.jar
```

If you are signed into Google Cloud, those credentials will be used, otherwise, you may need to sign in via

```
gcloud auth login
```

After successful deployment, the REST API will be accessible at

```
https://transform-sample.appspot.com/transform
```

### Docker

Note, for deployment to a public cloud, the container registry needs to be visible to the cloud provider.

In other words, push to their private container registry or push to a public one such as docker hub.

### Preparing image for pushing and running locally

To build app.jar and publish the image
```
./gradlew clean build
docker build --build-arg JAR_FILE=build/libs/*.jar -t jbedalov/sample:v1 .
```

To run the image; note -p 8080:8080 is required to expose to the port locally
```
docker run -p 8080:8080 -t jbedalov/sample:v1
```

## Docker Hub - push and pull

To build app.jar and publish the image
```
./gradlew clean build
docker build --build-arg JAR_FILE=build/libs/*.jar -t jbedalov/sample:v1 .
```

Push the image to a container registry (in this case docker hub)
```
docker login && docker push jbedalov/sample:v1
```

To pull from the container registry (CI/CD pipeline can push this image to your cloud provider)
```
docker login && docker pull jbedalov/sample:v1
```

## Built With

* [Spring Initializr](https://start.spring.io/) - Generates folders and basic conventions.
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used.
* [Gradle](https://gradle.org/) - Dependency and build management.

## Authors

* **John Bedalov** - *Initial work* - [johnB96](https://github.com/johnB96)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
