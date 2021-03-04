package maitredkata;

import java.util.*;
import java.util.stream.Collectors;

public class HauteCuisineMaitreD extends AbstractMaitreD {
    private final List<Integer> _tableSizes;
    private Integer _seatingDurationMins;

    // For restaurants with only one seating.
    public HauteCuisineMaitreD(int[] tableSizes) {
        this(tableSizes, null);
    }

    public HauteCuisineMaitreD(int[] tableSizes, Integer seatingDurationMins) {
        super();

        _seatingDurationMins = seatingDurationMins;
        _tableSizes = new ArrayList<>();

        for (int t: tableSizes) {
            _tableSizes.add(t);
        }
    }

    @Override
    protected boolean canReserve(Date date, Reservation newReservation) {
        // Sort the table sizes so we assign each party to the smallest table
        // that fits. This avoids e.g. a party of 4 not being able to be seated
        // because a party of 2 was previously seated at the only 4 top when
        // there was a 2 top availabe.
        List<Table> availableTables = _tableSizes.stream()
                .sorted()
                .map(size -> new Table(size))
                .collect(Collectors.toList());
        List<Reservation> reservationsIncludingNew = new ArrayList<>(_reservations.get(date));
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
            if (capacity < reservation.getNumDiners()) {
                return false;
            } else {
                return !isBookedAt(reservation.getDate());
            }
        }

        private boolean isBookedAt(Date date) {
            if (_seatingDurationMins == null) { // single seating
                return !reservations.isEmpty();
            }

            return reservations.stream()
                    .anyMatch(res -> {
                        Date end = addMinutes(res.getDate(), _seatingDurationMins);
                        return date.compareTo(res.getDate()) >= 0 && date.compareTo(end) < 0;
                    });
        }

        private Date addMinutes(Date date, int minutes) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, minutes);
            return cal.getTime();
        }
    }
}
