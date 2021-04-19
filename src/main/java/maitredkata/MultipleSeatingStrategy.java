package maitredkata;

import java.time.LocalDateTime;
import java.util.List;

public class MultipleSeatingStrategy implements TableBookingStrategy {
    private int _seatingDurationMins;

    public MultipleSeatingStrategy(int seatingDurationMins) {
        _seatingDurationMins = seatingDurationMins;
    }

    @Override
    public boolean canAccept(
            Reservation newReservation,
            List<Reservation> existingSameDayReservations,
            int tableCapacity
    ) {
        if (tableCapacity < newReservation.getNumDiners()) {
            return false;
        }

        boolean hasConflict = existingSameDayReservations.stream()
                .anyMatch(res -> newReservation.overlaps(res, _seatingDurationMins));

        return !hasConflict;
    }
}
