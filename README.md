# Sensor's controller

Sensor-controller is a Java based project dedicated to collect data from sensors and alert if the CO2 concentrations reach critical levels.

## Docker

To run the project in a local environment
```
git clone https://github.com/milenaf30/sensor-controller.git
cd sensor-controller
cd images
docker-compose build
docker-compose up
```

This will create a Java instance and a MySQL database instance.

## Local

To run the project in a local environment please change the file `application.properties` in the folder `src` to match the following

```
#spring.datasource.url=jdbc:mysql://database:3306/sensor?characterEncoding=utf8
spring.datasource.url=jdbc:mysql://localhost:3306/sensor?characterEncoding=utf8
```

```
#spring.flyway.url=jdbc:mysql://database:3306/sensor
spring.flyway.url=jdbc:mysql://localhost:3306/sensor
```

## Swagger Documentation

You can find documentation of the endpoints offered by the service in 
- `http://localhost:8090/swagger-ui.html` (docker) or 
- `http://localhost:8080/swagger-ui.html` (local)

## Rest API

### Save data

POST `/api/v1/sensors/{uuid}/measurements`


```json
{
    "co2" : 2000,
    "time" : "2019-02-01T18:55:47+00:00"
}
```

### Get status

GET `/api/v1/sensors/{uuid}`

For getting the status of the sensor with `{uuid}`

```json
Response: 
{
    "status" : "OK" // Possible status OK,WARN,ALERT
}
```

### Get metrics

GET `/api/v1/sensors/{uuid}/metrics`

For getting the max and avg CO2 levels of the last 30 days of the sensor with `{uuid}`

```json
Response: 
{
    "maxLast30Days" : 1200,
    "avgLast30Days" : 900
}
```

## Architectural design

`Package-by-feature`: Each package contains only the items related to that particular feature and is organized in a way that maps a layered architecture.
Having:

- `Controller` handles every request to the api.
- - `App service` used to encapsulate the logic needed to be implement from one endpoint and to enable the communication between the data received and the domain model.
- `Domain service` encapsulate all repositories to handle database.
- `Repository` manages requests to underlaying database.

Each of them communicating only with the layer underneath and returning values to the layer above.

- `Domain` business entities.
- `Dto` Data transfer objects used in communication with external services/users

Are used by all the layers.


## Clean code with lombok

Lombok library for automating the creation of accessors and helping with the implementation of the builder pattern for each class.

https://projectlombok.org/features/all

## Testing

Unit testing for each layer and integration testing for the correctnes with the database.

## Decisions taken

Tried to focus in a objected oriented implementation. That's why 
- There is a Sensor entity
- the measurements are injected to the sensor instead of fetching directly to the db (the last 3).

Metrics per day, is not based in a business entity, that's why is separated from sensor and it only maintains a sensor_id.
And it was meant to easier the avg and max calculation avoiding the need of doing at the worst calculation over 30dia*24h/dia*60m/h = 43200 records.

## Improvements

- Database dedicated for testing
- Population of data in database for having a base when local development
- Improvements en CI (Github actions): run tests when pushing to branch
- Run tests in docker.
- Evaluate not saving all the measurements received since it's not necessary for resolving the GET endpoints.
- Evaluate the posibillity of using a KV database.
- Evaluate deleting all the information deprecated.
- Improve exception handling: map HTTP codes to exceptions and return descriptive messages.
- Usage of assemblers
- Avoid using directly logger imple
- Change the type of the average being returned to a double for more specificity.
