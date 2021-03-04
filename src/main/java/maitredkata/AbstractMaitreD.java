package maitredkata;

import java.util.Date;
import java.util.List;

public abstract class AbstractMaitreD {
    protected final DayMap _reservations;

    public AbstractMaitreD() {
        _reservations = new DayMap();
    }

    public boolean reserve(Date date, int qty) {
        Reservation reservation = new Reservation(date, qty);
        if (!canReserve(date, reservation)) {
            return false;
        }

        _reservations.get(date).add(reservation);
        return true;
    }

    abstract protected boolean canReserve(Date date, Reservation newReservation);
}
