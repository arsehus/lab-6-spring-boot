package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// @RestController: методы принимают HTTP-запросы, а возвращаемые объекты Spring превращает в JSON.
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private final EventService eventService;
    private final String applicationName;
    private final String course;
    private final String semester;

    // Constructor-based dependency injection:
    // Spring сам создаёт EventService и передаёт его сюда. Мы НЕ пишем new EventService().
    // Значения из application.properties приходят через @Value.
    public RegistrationController(EventService eventService,
                                  @Value("${spring.application.name}") String applicationName,
                                  @Value("${app.course}") String course,
                                  @Value("${app.semester}") String semester) {
        this.eventService = eventService;
        this.applicationName = applicationName;
        this.course = course;
        this.semester = semester;
    }

    // GET /api/hello
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", eventService.getWelcomeMessage());
    }

    // GET /api/events
    @GetMapping("/events")
    public List<EventInfo> getEvents() {
        return eventService.getEvents();
    }

    // GET /api/info
    @GetMapping("/info")
    public Map<String, String> info() {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("application", applicationName);
        result.put("course", course);
        result.put("semester", semester);
        return result;
    }

    // Бонус: GET /api/events/{id}
    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        Optional<EventInfo> event = eventService.findEventById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Event with id " + id + " was not found"));
    }
}
