package maitredkata;

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
}
