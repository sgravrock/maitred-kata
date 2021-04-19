package maitredkata;

import java.time.LocalDate;
import java.util.*;

// TODO: Make this more concrete? dayMap.add(reservation) would be nice
public class DayMap extends DefaultingMap<LocalDate, List<Reservation>> {
    @Override
    protected List<Reservation> makeDefaultValue() {
        return new ArrayList<>();
    }
}
