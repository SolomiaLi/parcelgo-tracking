package ua.parcelgo.events;
import java.util.List;
public interface EventStoreRepository {
    void appendEvent(DomainEvent event);
    List<DomainEvent> getHistory(String ttn);
}
