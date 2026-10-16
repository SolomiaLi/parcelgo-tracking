package ua.parcelgo.events;
import ua.parcelgo.tracking.ParcelState;
public class EventProjection {
    public ParcelState reconstructState(String ttn) {
        return ParcelState.ACCEPTED;
    }
}
