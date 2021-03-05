package maitredkata;

import java.util.List;

public interface TableBookingStrategy {
    boolean canAccept(
            Reservation newReservation,
            List<Reservation> existingSameDayReservations,
            int tableCapacity
    );
}
