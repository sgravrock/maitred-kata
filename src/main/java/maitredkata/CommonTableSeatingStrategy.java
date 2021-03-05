package maitredkata;

import java.util.List;

public class CommonTableSeatingStrategy implements TableBookingStrategy {
    @Override
    public boolean canAccept(Reservation newReservation, List<Reservation> existingSameDayReservations, int tableCapacity) {
        int numPreviouslyReserved = totalNumDiners(existingSameDayReservations);
        return tableCapacity >= numPreviouslyReserved + newReservation.getNumDiners();
    }

    private static int totalNumDiners(List<Reservation> reservations) {
        return reservations.stream()
                .map(r -> r.getNumDiners())
                .reduce(0, (a, b) -> a + b);
    }
}
