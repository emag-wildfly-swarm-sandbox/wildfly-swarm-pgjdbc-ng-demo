# wildfly-swarm-pgjdbc-ng-demo

## Setup PostgreSQL

``` sh
$ docker run -it --rm \
    --name test-db \
    -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test \
    -p 5432:5432 \
    postgres:9.5.3
```

## Run the app
 
``` sh
$ ./mvnw clean wildfly-swarm:run
```

## Access to API

``` sh
$ curl localhost:8080
```