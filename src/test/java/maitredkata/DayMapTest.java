package maitredkata;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DayMapTest {
    @Test
    public void storesAndRetrievesValues() {
        DayMap<String> subject = new DayMap<>("");
        Date k = new Date(0);
        subject.put(k, "foo");
        assertEquals("foo", subject.get(k));
    }

    @Test
    public void insertsDefaultWhenNotSet() {
        DayMap<List<String>> subject = new DayMap<>(() -> new ArrayList());
        Date k = new Date(0);
        List<String> list = subject.get(k);
        assertEquals(Collections.emptyList(), list);
        list.add("foo");
        assertEquals(Collections.singletonList("foo"), subject.get(k));
    }

    @Test
    public void ignoresTimePortionOfDate() {
        DayMap<String> subject = new DayMap<>("");
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d1.setHours(1);
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d2.setHours(2);
        subject.put(d1, "foo");

        assertEquals("foo", subject.get(d2));
    }
}