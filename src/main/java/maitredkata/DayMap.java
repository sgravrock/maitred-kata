package maitredkata;

import java.util.*;

public class DayMap {
    private Map<Date, List<Reservation>> _storage;

    public DayMap() {
        _storage = new HashMap<>();
    }

    public List<Reservation> get(Date key) {
        key = removeTimePortion(key);

        if (_storage.containsKey(key)) {
            return _storage.get(key);
        } else {
            List<Reservation> value = new ArrayList<>();
            _storage.put(key, value);
            return value;
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
