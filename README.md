# Ballot API

Implements a voting API.



## Build and Run Application

### Build Project

```
./gradlew build

```

### Run Project

```
cd build/libs/
java -jar rgn_data_pipeline_poc-0.0.1-SNAPSHOT.jar
```

## Quick API testing 


After application bootstrap, access Swagger UI in this address: http://localhost:8080/swagger-ui.html to easily test Ballot Application.
Use Swagger hints to discover the API Data Contract.


### 1 - Create a couple users

Use `POST /users` endpoint: http://localhost:8080/swagger-ui.html#/user-controller/createUserUsingPOST.


### 2 - Create an Agenda

Use `POST /agendas` endpoint: http://localhost:8080/swagger-ui.html#/agenda-controller/createAgendaUsingPOST.

### 3 - Vote

Retrieve created user ids `GET /users`: http://localhost:8080/swagger-ui.html#/user-controller/listUsersUsingGET.
Retrieve created agenda id `GET /agendas`: http://localhost:8080/swagger-ui.html#/agenda-controller/listAgendasUsingGET.
For each user, vote `POST /ballot/{agendaId}/vote`: http://localhost:8080/swagger-ui.html#/ballot-controller/voteUsingPOST.

### 4 - Access voting results

Retrieve created agenda id `GET /agendas`: http://localhost:8080/swagger-ui.html#/agenda-controller/listAgendasUsingGET.
Access `GET /ballot/{agendaId}/results`: http://localhost:8080/swagger-ui.html#/ballot-controller/votingResultsUsingGET.

