package maitredkata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reservation {
    private final LocalDateTime _dateTime;
    private final int _numDiners;

    public Reservation(LocalDateTime dateTime, int numDiners) {
        _dateTime = dateTime;
        _numDiners = numDiners;
    }

    public int getNumDiners() {
        return _numDiners;
    }

    public LocalDateTime getDateTime() {
        return _dateTime;
    }

    public LocalDate getDate() {
        return _dateTime.toLocalDate();
    }

    public LocalTime getTimeOfDay() {
        return _dateTime.toLocalTime();
    }

    public boolean overlaps(Reservation other, int durationMins) {
        if (_dateTime.isBefore(other._dateTime)) {
            LocalDateTime end = _dateTime.plusMinutes(durationMins);
            return end.isAfter(other._dateTime);
        } else {
            LocalDateTime otherEnd = other._dateTime.plusMinutes(durationMins);
            return otherEnd.isAfter(_dateTime);
        }
    }
}
