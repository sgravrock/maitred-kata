package maitredkata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractMaitreD {
    private final DayMap _reservations;
    private final List<Integer> _tableSizes;
    private final TableBookingStrategy _tableBookingStrategy;

    public AbstractMaitreD(
            int[] tableSizes,
            TableBookingStrategy tableBookingStrategy
    ) {
        _reservations = new DayMap();
        _tableBookingStrategy = tableBookingStrategy;

        _tableSizes = new ArrayList<>();

        for (int t: tableSizes) {
            _tableSizes.add(t);
        }
    }

    public boolean reserve(LocalDateTime dateTime, int qty) {
        Reservation reservation = new Reservation(dateTime, qty);
        if (!canReserve(reservation)) {
            return false;
        }

        _reservations.addReservation(reservation);
        return true;
    }

    private boolean canReserve(Reservation newReservation) {
        // Sort the table sizes so we assign each party to the smallest table
        // that fits. This avoids e.g. a party of 4 not being able to be seated
        // because a party of 2 was previously seated at the only 4 top when
        // there was a 2 top availabe.
        List<Table> availableTables = _tableSizes.stream()
                .sorted()
                .map(size -> new Table(size))
                .collect(Collectors.toList());
        List<Reservation> reservationsIncludingNew = new ArrayList<>(
                _reservations.get(newReservation.getDate())
        );
        reservationsIncludingNew.add(newReservation);

        for (Reservation res: reservationsIncludingNew) {
            Table t = findFirstUsableTable(availableTables, res);

            if (t == null) {
                return false;
            } else {
                t.reservations.add(res);
            }
        }

        return true;
    }

    private Table findFirstUsableTable(List<Table> tables, Reservation reservation) {
        for (int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            if (t.canAccept(reservation)) {
                return t;
            }
        }

        return null;
    }

    private class Table {
        int capacity;
        List<Reservation> reservations;

        Table(int capacity) {
            this.capacity = capacity;
            this.reservations = new ArrayList<>();
        }

        boolean canAccept(Reservation reservation) {
            return _tableBookingStrategy.canAccept(reservation, this.reservations, this.capacity);
        }
    }
}
