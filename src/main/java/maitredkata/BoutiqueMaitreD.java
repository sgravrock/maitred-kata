package maitredkata;

import java.util.*;


public class BoutiqueMaitreD {
    private int _tableSize;
    private Map<Date, Integer> _numReservedPerDay;

    public BoutiqueMaitreD(int tableSize) {
        _tableSize = tableSize;
        _numReservedPerDay = new HashMap<>();
    }

    public boolean reserve(Date date, int qty) {
        date = DateUtil.removeTimePortion(date);
        int numReserved = _numReservedPerDay.getOrDefault(date, 0);
        int newNumReserved = numReserved + qty;

        if (newNumReserved <= _tableSize) {
            _numReservedPerDay.put(date, newNumReserved);
            return true;
        } else {
            return false;
        }
    }
}