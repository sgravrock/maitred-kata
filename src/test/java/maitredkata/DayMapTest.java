package maitredkata;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static maitredkata.DateHelpers.arbitraryDate;
import static maitredkata.DateHelpers.arbitraryTimeOnDate;
import static org.junit.Assert.*;

public class DayMapTest {
    @Test
    public void getInsertsDefaultWhenNotSet() {
        DayMap subject = new DayMap();
        LocalDate k = arbitraryDate();
        List<Reservation> list = subject.get(k);
        assertEquals(Collections.emptyList(), list);
        Reservation r = new Reservation(arbitraryTimeOnDate(k), 42);
        list.add(r);
        assertEquals(Collections.singletonList(r), subject.get(k));
    }

    @Test
    public void getProvidesExistingValueWhenSet() {
        DayMap subject = new DayMap();
        LocalDate k = arbitraryDate();
        Reservation r1 = new Reservation(arbitraryTimeOnDate(k), 42);
        Reservation r2 = new Reservation(arbitraryTimeOnDate(k), 17);

        subject.get(k).add(r1);
        subject.get(k).add(r2);

        List<Reservation> expected = new ArrayList<>();
        expected.add(r1);
        expected.add(r2);
        assertEquals(expected, subject.get(k));
    }

    @Test
    public void addReservationWorksWhenDayListAbsent() {
        DayMap subject = new DayMap();
        LocalDate k = arbitraryDate();
        Reservation r = new Reservation(arbitraryTimeOnDate(k), 42);
        subject.addReservation(r);

        assertEquals(Collections.singletonList(r), subject.get(k));
    }

    @Test
    public void addReservationWorksWhenDayListPresent() {
        DayMap subject = new DayMap();
        LocalDate k = arbitraryDate();
        Reservation r1 = new Reservation(arbitraryTimeOnDate(k), 42);
        Reservation r2 = new Reservation(arbitraryTimeOnDate(k), 17);

        subject.addReservation(r1);
        subject.addReservation(r2);

        List<Reservation> expected = new ArrayList<>();
        expected.add(r1);
        expected.add(r2);
        assertEquals(expected, subject.get(k));
    }

}