package maitredkata;

import java.util.*;


public class BoutiqueMaitreD {
    private final int _tableSize;
    private final DayMap<Integer> _numReservedPerDay;

    public BoutiqueMaitreD(int tableSize) {
        _tableSize = tableSize;
        _numReservedPerDay = new DayMap<>(0);
    }

    public boolean reserve(Date date, int qty) {
        int numReserved = _numReservedPerDay.get(date);
        int newNumReserved = numReserved + qty;

        if (newNumReserved <= _tableSize) {
            _numReservedPerDay.put(date, newNumReserved);
            return true;
        } else {
            return false;
        }
    }
}