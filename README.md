# kisesis-client

This project is a PoC for demonstrate usage of different messaging services, like Kafka and Kinesis.

### How to use

- Build: `./gradlew clean docker`
- Start: `docker-compose up`

It is configured to start a LocalStack instance, and start one instance of the *kinesis-client*.

- Open in browser: http://localhost:8080/swagger-ui.html

With this controller, you can put records to the fast lane.
Kinesis will start processing these requests.

Each request will fail after 2 or more iteration, and goes to the *Ignored.log* file.
This behaviour can be configured with `messaging.minimumIterations` property.

### Additional feature

There is a switch `messaging.implementation` in the application.yml.
This decide which messaging implementation will be used (Kafka/Kinesis).

This project has a dummy Kafka implementation, which only writes each requests to the console.
However the feature is there, and maybe I will implement that part as well :)
