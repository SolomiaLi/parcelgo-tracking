package ua.parcelgo.events;
import java.time.Instant;
public record DomainEvent(String eventId, String ttn, String type, Instant timestamp, String operatorId) {}
