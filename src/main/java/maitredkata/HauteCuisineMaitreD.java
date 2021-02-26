package maitredkata;

import java.util.*;
import java.util.stream.Collectors;

public class HauteCuisineMaitreD {
    private final List<Integer> _tableSizes;
    private final DayMap _reservations;

    public HauteCuisineMaitreD(int... tableSizes) {
        _tableSizes = new ArrayList<>();

        for (int t: tableSizes) {
            _tableSizes.add(t);
        }

        _reservations = new DayMap();
    }

    public boolean reserve(Date date, int qty) {
        Reservation reservation = new Reservation(qty);
        if (!canReserve(date, reservation)) {
            return false;
        }

        _reservations.get(date).add(reservation);
        return true;
    }

    private boolean canReserve(Date date, Reservation newReservation) {
        // Sort the table sizes so we assign each party to the smallest table
        // that fits. This avoids e.g. a party of 4 not being able to be seated
        // because a party of 2 was previously seated at the only 4 top when
        // there was a 2 top availabe.
        List<Integer> unassignedTables = _tableSizes.stream()
                .sorted()
                .collect(Collectors.toList());
        List<Reservation> reservationsIncludingNew = new ArrayList<>(_reservations.get(date));
        reservationsIncludingNew.add(newReservation);

        for (Reservation res: reservationsIncludingNew) {
            int i = findFirstGreaterThanOrEqual(unassignedTables, res.getNumDiners());

            if (i == -1) {
                return false;
            } else {
                unassignedTables.remove(i);
            }
        }

        return true;
    }

    private int findFirstGreaterThanOrEqual(List<Integer> haystack, Integer needle) {
        for (int i = 0; i < haystack.size(); i++) {
            if (haystack.get(i) >= needle) {
                return i;
            }
        }

        return -1;
    }
}
