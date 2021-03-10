package maitredkata;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommonTableSeatingStrategy implements TableBookingStrategy {
    private List<LocalTime> _seatings;

    // seatings may be empty, in which case a single seating is assumed and
    // the time of reservations won't be checked.
    public CommonTableSeatingStrategy(List<LocalTime> seatings) {
        _seatings = seatings;
    }

    @Override
    public boolean canAccept(
            Reservation newReservation,
            List<Reservation> existingSameDayReservations,
            int tableCapacity
    ) {
        return isValidTime(newReservation) &&
                hasSeats(newReservation, existingSameDayReservations, tableCapacity);
    }

    private boolean isValidTime(Reservation res) {
        if (_seatings.size() == 0) {
            return true;
        }

        return _seatings.contains(res.getTimeOfDay());
    }

    private boolean hasSeats(
            Reservation newReservation,
            List<Reservation> existingSameDayReservations,
            int tableCapacity
    ) {
        int numPreviouslyReserved = numDinersAtSameSeating(
                existingSameDayReservations,
                newReservation
        );
        return tableCapacity >= numPreviouslyReserved + newReservation.getNumDiners();
    }

    private boolean sameSeating(Reservation r, Reservation newReservation) {
        return _seatings.size() == 0 || r.getTimeOfDay() == newReservation.getTimeOfDay();
    }

    private int numDinersAtSameSeating(
            List<Reservation> reservations,
            Reservation newReservation
    ) {
        return reservations.stream()
                .filter(r -> sameSeating(r, newReservation))
                .map(r -> r.getNumDiners())
                .reduce(0, (a, b) -> a + b);
    }
}
