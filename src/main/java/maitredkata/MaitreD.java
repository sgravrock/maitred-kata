package maitredkata;

import java.util.*;


public class MaitreD {
    private int _tableSize;
    private Map<Date, Integer> _numReservedPerDay;

    public MaitreD(int tableSize) {
        _tableSize = tableSize;
        _numReservedPerDay = new HashMap<>();
    }

    public boolean reserve(Date date, int qty) {
        date = removeTimePortion(date);
        int numReserved = _numReservedPerDay.getOrDefault(date, 0);
        int newNumReserved = numReserved + qty;

        if (newNumReserved <= _tableSize) {
            _numReservedPerDay.put(date, newNumReserved);
            return true;
        } else {
            return false;
        }
    }

    private static Date removeTimePortion(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }
}