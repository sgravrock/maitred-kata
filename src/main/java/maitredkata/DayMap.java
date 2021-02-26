package maitredkata;

import java.util.*;

public class DayMap<TValue> {
    interface ValueCreator<TValue> {
        TValue create();
    }

    private Map<Date, TValue> _storage;
    private ValueCreator<TValue> _valueCreator;

    public DayMap(ValueCreator<TValue> valueCreator) {
        _storage = new HashMap<>();
        _valueCreator = valueCreator;
    }

    public TValue get(Date key) {
        key = removeTimePortion(key);

        if (_storage.containsKey(key)) {
            return _storage.get(key);
        } else {
            TValue value = _valueCreator.create();
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
