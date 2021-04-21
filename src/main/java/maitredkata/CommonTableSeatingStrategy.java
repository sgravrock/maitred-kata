package maitredkata;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommonTableSeatingStrategy implements TableBookingStrategy {
    private List<LocalTime> _seatings;

    public CommonTableSeatingStrategy(List<LocalTime> seatings) throws InvalidSeatingsException {
        if (seatings.size() == 0) {
            throw new InvalidSeatingsException();
        }

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
        return r.getTimeOfDay() == newReservation.getTimeOfDay();
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
