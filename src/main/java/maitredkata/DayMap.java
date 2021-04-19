package maitredkata;

import java.time.LocalDate;
import java.util.*;

public class DayMap {
    private Map<LocalDate, List<Reservation>> _storage;

    public DayMap() {
        _storage = new HashMap<>();
    }

    public void addReservation(Reservation r) {
        get(r.getDate()).add(r);
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
