package maitredkata;

import java.util.List;

public class SingleSeatingStrategy implements TableBookingStrategy {
    @Override
    public boolean canAccept(
            Reservation newReservation,
            List<Reservation> existingSameDayReservations
    ) {
        return existingSameDayReservations.isEmpty();
    }
}
