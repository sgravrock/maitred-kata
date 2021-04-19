package maitredkata;

import java.time.LocalDate;
import java.util.*;

// TODO: Might make this more abstract? DefaultingMap<K, V> w/ default builder?
// Or more concrete? dayMap.add(reservation) would be nice
public class DayMap {
    private Map<LocalDate, List<Reservation>> _storage;

    public DayMap() {
        _storage = new HashMap<>();
    }

    public List<Reservation> get(LocalDate key) {
        if (_storage.containsKey(key)) {
            return _storage.get(key);
        } else {
            List<Reservation> value = new ArrayList<>();
            _storage.put(key, value);
            return value;
        }
    }
}
