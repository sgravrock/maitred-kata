package maitredkata;

import java.util.*;


public class BoutiqueMaitreD {
    private final int _tableSize;
    private final DayMap<List<Reservation>> _reservations;

    public BoutiqueMaitreD(int tableSize) {
        _tableSize = tableSize;
        _reservations = new DayMap<>(() -> new ArrayList<>());
    }

    public boolean reserve(Date date, int qty) {
        Reservation reservation = new Reservation(qty);
        if (!canReserve(date, reservation)) {
            return false;
        }

        _reservations.get(date).add(new Reservation(qty));
        return true;
    }

    private boolean canReserve(Date date, Reservation newReservation) {
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