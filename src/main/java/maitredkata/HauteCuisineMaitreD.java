package maitredkata;

import java.util.*;
import java.util.stream.Collectors;

public class HauteCuisineMaitreD {
    private final List<Integer> _tableSizes;
    private final Map<Date, List<Integer>> _reservations;

    public HauteCuisineMaitreD(int... tableSizes) {
        _tableSizes = new ArrayList<>();

        for (int t: tableSizes) {
            _tableSizes.add(t);
        }

        _reservations = new HashMap<>();
    }

    public boolean reserve(Date date, int qty) {
        date = DateUtil.removeTimePortion(date);
        if (!_reservations.containsKey(date)) {
            _reservations.put(date, new ArrayList<>());
        }

        if (!canReserve(date, qty)) {
            return false;
        }

        _reservations.get(date).add(qty);
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
        List<Integer> reservationsIncludingNew = new ArrayList<>(_reservations.get(date));
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
