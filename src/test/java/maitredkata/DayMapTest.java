package maitredkata;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DayMapTest {
    @Test
    public void insertsDefaultWhenNotSet() {
        DayMap subject = new DayMap();
        Date k = new Date(0);
        List<Reservation> list = subject.get(k);
        assertEquals(Collections.emptyList(), list);
        Reservation r = new Reservation(arbitraryDate(), 42);
        list.add(r);
        assertEquals(Collections.singletonList(r), subject.get(k));
    }

    @Test
    public void providesExistingValueWhenSet() {
        DayMap subject = new DayMap();
        Date k = new Date(0);
        Reservation r1 = new Reservation(arbitraryDate(), 42);
        Reservation r2 = new Reservation(arbitraryDate(), 17);

        subject.get(k).add(r1);
        subject.get(k).add(r2);

        List<Reservation> expected = new ArrayList<>();
        expected.add(r1);
        expected.add(r2);
        assertEquals(expected, subject.get(k));
    }

    @Test
    public void ignoresTimePortionOfDate() {
        DayMap subject = new DayMap();
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d1.setHours(1);
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d2.setHours(2);
        Reservation r = new Reservation(arbitraryDate(), 42);
        subject.get(d1).add(r);

        assertEquals(Collections.singletonList(r), subject.get(d2));
    }

    private static Date arbitraryDate() {
        return new Date(0);
    }
}