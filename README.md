# Event Registration API (Spring Boot)

First Spring Boot backend for the Event Registration scenario. It returns JSON
from simple GET endpoints. Data is stored in memory (no database, no frontend).

## Requirements
- JDK 17 or later
- Gradle wrapper is included (`gradlew` / `gradlew.bat`), no separate Gradle install needed

## How to run
- In IntelliJ IDEA: open the project and run `EventRegistrationApplication.main()`.
- From terminal: `./gradlew bootRun` (Windows: `gradlew.bat bootRun`)

The server starts on http://localhost:8080.

## Endpoints
| Endpoint | Response |
|---|---|
| `GET /api/hello` | JSON welcome message from `EventService` |
| `GET /api/events` | JSON array with 3 events |
| `GET /api/info` | application name, course and semester from `application.properties` |
| `GET /api/events/{id}` | (bonus) one event, or 404 with an error message if the id does not exist |

## Structure (package `kz.iitu.registration`)
- **EventRegistrationApplication** - main class, starts Spring Boot.
- **controller/RegistrationController** - `@RestController` with base path `/api`. Receives HTTP requests and returns JSON. `EventService` is injected through the constructor.
- **service/EventService** - `@Service` with the welcome message and the in-memory list of events.
- **model/EventInfo** - record with `id`, `title`, `location`.
- **application.properties** - settings `app.course` and `app.semester`, read in the controller with `@Value`.
