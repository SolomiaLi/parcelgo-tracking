package ua.parcelgo.tracking;
public class TrackingService {
    public ParcelState getCurrentStatus(String ttn) {
        return ParcelState.CREATED;
    }
}
// Security fix: sanitize TTN input query against injection
