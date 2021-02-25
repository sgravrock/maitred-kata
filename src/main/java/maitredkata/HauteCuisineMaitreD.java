package maitredkata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HauteCuisineMaitreD {
    private final List<Integer> _tableSizes;
    private final List<Integer> _reservations;

    public HauteCuisineMaitreD(List<Integer> tableSizes) {
        _tableSizes = tableSizes;
        _reservations = new ArrayList<>();
    }

    public boolean reserve(Date date, int qty) {
        if (!canReserve(date, qty)) {
            return false;
        }

        _reservations.add(qty);
        return true;
    }

    private boolean canReserve(Date date, int qty) {
        // Sort the table sizes so we assign each party to the smallest table
        // that fits. This avoids e.g. a party of 4 not being able to be seated
        // because a party of 2 was previously seated at the only 4 top when
        // there was a 2 top availabe.
        List<Integer> unassignedTables = _tableSizes.stream()
                .sorted()
                .collect(Collectors.toList());
        List<Integer> reservationsIncludingNew = new ArrayList<>(_reservations);
        reservationsIncludingNew.add(qty);

        for (Integer rq: reservationsIncludingNew) {
            int i = findFirstGreaterThanOrEqual(unassignedTables, rq);

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
