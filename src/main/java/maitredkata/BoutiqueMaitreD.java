package maitredkata;

import java.util.*;


public class BoutiqueMaitreD extends AbstractMaitreD {
    private final int _tableSize;

    public BoutiqueMaitreD(int tableSize) {
        super();
        _tableSize = tableSize;
    }

    @Override
    protected boolean canReserve(Date date, Reservation newReservation) {
        int numReserved = numReservedForDay(date);
        int newNumReserved = numReserved + newReservation.getNumDiners();
        return newNumReserved <= _tableSize;
    }

    private int numReservedForDay(Date date) {
        return _reservations.get(date).stream()
                .map(r -> r.getNumDiners())
                .reduce(0, (a, b) -> a + b);
    }
}