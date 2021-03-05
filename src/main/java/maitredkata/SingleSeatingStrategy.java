package maitredkata;

import java.util.List;

public class SingleSeatingStrategy implements TableBookingStrategy {
    @Override
    public boolean canAccept(
            Reservation newReservation,
            List<Reservation> existingSameDayReservations,
            int tableCapacity
    ) {
        return tableCapacity >= newReservation.getNumDiners() &&
                existingSameDayReservations.isEmpty();
    }
}
