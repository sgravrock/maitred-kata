package maitredkata;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ReservationTest {
    @Test
    public void getTimeOfDayReturnsTimeOfDay() {
        LocalDate d = LocalDate.of(1, 1, 1);
        LocalTime t = LocalTime.of(1, 2, 3);
        LocalDateTime dt = LocalDateTime.of(d, t);
        Reservation subject = new Reservation(dt, 1);

        assertEquals(t, subject.getTimeOfDay());
    }

    @Test
    public void overlapReturnsFalseWhenTimesDoNotOverlap() {
        LocalDate d = LocalDate.of(1, 1, 1);
        LocalDateTime start1 = LocalDateTime.of(d, LocalTime.of(18, 0));
        LocalDateTime start2 = LocalDateTime.of(d, LocalTime.of(20, 0));
        Reservation r1 = new Reservation(start1, 1);
        Reservation r2 = new Reservation(start2, 1);

        assertFalse(r1.overlaps(r2, 120));
        assertFalse(r2.overlaps(r1, 120));
    }

    @Test
    public void overlapReturnsTrueWhenTimesOverlap() {
        LocalDate d = LocalDate.of(1, 1, 1);
        LocalDateTime start1 = LocalDateTime.of(d, LocalTime.of(18, 1));
        LocalDateTime start2 = LocalDateTime.of(d, LocalTime.of(20, 0));
        Reservation r1 = new Reservation(start1, 1);
        Reservation r2 = new Reservation(start2, 1);

        assertTrue(r1.overlaps(r2, 120));
        assertTrue(r2.overlaps(r1, 120));
    }

    @Test
    public void overlapReturnsTrueWhenDatesDiffer() {
        LocalTime start = LocalTime.of(18, 0);
        LocalDate d1 = LocalDate.of(1, 1, 1);
        LocalDate d2 = LocalDate.of(1, 1, 2);
        Reservation r1 = new Reservation(LocalDateTime.of(d1, start), 1);
        Reservation r2 = new Reservation(LocalDateTime.of(d2, start), 1);

        assertFalse(r1.overlaps(r2, 120));
        assertFalse(r2.overlaps(r1, 120));
    }
}