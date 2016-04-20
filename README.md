
[![Codeship Status for boennemann/badges](https://codeship.com/projects/e2c45480-e1c6-0133-e6ba-2ebdfba46e8b/status?branch=master)](https://www.codeship.io/projects/145415)
# Vote no Restaurante
This project was built with **Java 8**, **Spring Boot**, **Spring Data JPA** and in the front-end **AngularJS**.

You can find an online version at: http://goo.gl/B2NrLw

## Requirements

* Gradle 2.12+
* Java 8+
* Node JS
* Bower JS

## Installation

First, let's install some Node dependencies:

```
$ npm install
```

Then you will have to install the front-end dependencies using Bower:
```
$ bower install
```

After that you can run Gradle to package the application:
```
$ ./gradlew built
```

Now you can run the Java application easily:
```
$ cd build/libs
$ java -jav vote-no-restaurante-0.0.1-SNAPSHOT.jar
```


## Testing

You can run the front-end tests using:
```
$ karma start
```

To run the back-end tests use Gradle:
```
$ ./gradlew test
```

