package kz.iitu.registration.model;

// Модель одного мероприятия. record сам создаёт конструктор и геттеры (id(), title(), location()).
public record EventInfo(int id, String title, String location) {
}
