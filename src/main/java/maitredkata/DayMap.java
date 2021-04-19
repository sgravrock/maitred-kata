package maitredkata;

import java.time.LocalDate;
import java.util.*;

public class DayMap extends DefaultingMap<LocalDate, List<Reservation>> {
    public void addReservation(Reservation r) {
        get(r.getDate()).add(r);
    }

    @Override
    protected List<Reservation> makeDefaultValue() {
        return new ArrayList<>();
    }
}
