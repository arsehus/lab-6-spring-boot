package kz.iitu.registration.service;

import kz.iitu.registration.model.EventInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service говорит Spring: "создай объект этого класса сам и храни его".
// Здесь лежат данные и логика, контроллер только вызывает эти методы.
@Service
public class EventService {

    // Список событий хранится в памяти (без базы данных)
    private final List<EventInfo> events = List.of(
            new EventInfo(1, "Web Design Workshop", "Room 101"),
            new EventInfo(2, "JavaScript Quiz", "Room 203"),
            new EventInfo(3, "IT Career Talk", "Main Hall")
    );

    public String getWelcomeMessage() {
        return "Welcome to the Event Registration API";
    }

    public List<EventInfo> getEvents() {
        return events;
    }

    // Optional: либо событие найдено, либо пусто (для бонуса /api/events/{id})
    public Optional<EventInfo> findEventById(int id) {
        for (EventInfo event : events) {
            if (event.id() == id) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }
}
