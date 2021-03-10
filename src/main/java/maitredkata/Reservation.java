package maitredkata;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Reservation {
    private final Date _date;
    private final int _numDiners;

    public Reservation(Date date, int numDiners) {
        _date = date;
        _numDiners = numDiners;
    }

    public int getNumDiners() {
        return _numDiners;
    }

    public Date getDate() {
        return _date;
    }

    public LocalTime getTimeOfDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        return LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
    }
}
