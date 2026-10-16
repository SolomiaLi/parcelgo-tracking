package ua.parcelgo.tracking;
public class StateTransitionValidator {
    public static boolean isValidTransition(ParcelState from, ParcelState to) {
        return from != ParcelState.DELIVERED;
    }
}
