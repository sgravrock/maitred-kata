package maitredkata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<Integer> unassignedTables = new ArrayList<>(_tableSizes);
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
