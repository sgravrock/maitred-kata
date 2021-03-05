package maitredkata;

import java.util.Calendar;
import java.util.Date;
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

        Date date = newReservation.getDate();
        boolean hasConflict = existingSameDayReservations.stream()
                .anyMatch(res -> {
                    Date end = addMinutes(res.getDate(), _seatingDurationMins);
                    return date.compareTo(res.getDate()) >= 0 &&
                            newReservation.getDate().compareTo(end) < 0;
                });

        return !hasConflict;
    }

    private Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
}
