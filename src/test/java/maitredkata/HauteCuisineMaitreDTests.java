package maitredkata;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static maitredkata.DateHelpers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HauteCuisineMaitreDTests {
    @Test
    public void acceptsReservationThatFitsAnyTable() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{2, 4});

        assertTrue(subject.reserve(arbitraryDateTime(), 4));
    }

    @Test
    public void rejectsReservationThatFitsNoTable() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{2, 4});

        assertFalse(subject.reserve(arbitraryDateTime(), 5));
    }

    @Test
    public void rejectsSecondReservationWhenTableUsed() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{2, 4});
        LocalDateTime when = arbitraryDateTime();
        subject.reserve(when, 4);

        assertFalse(subject.reserve(when, 3));
    }

    @Test
    public void acceptsSecondReservationWhenTableAvailalabe() {
        HauteCuisineMaitreD subject1 = new HauteCuisineMaitreD(new int[]{4, 2});
        LocalDateTime when = arbitraryDateTime();
        subject1.reserve(when, 4);

        assertTrue(subject1.reserve(when, 2));

        // The reverse order should also work
        HauteCuisineMaitreD subject2 = new HauteCuisineMaitreD(new int[]{4, 2});
        subject2.reserve(when, 2);

        assertTrue(subject2.reserve(when, 4));
    }

    @Test
    public void handlesMultipleDates() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{4});
        LocalDateTime dt1 = LocalDateTime.of(
                LocalDate.of(2021, 1, 1),
                arbitraryTime()
        );
        LocalDateTime dt2 = LocalDateTime.of(
                LocalDate.of(2021, 1, 2),
                arbitraryTime()
        );
        subject.reserve(dt1, 3);

        assertTrue(subject.reserve(dt2, 2));
        assertFalse(subject.reserve(dt1, 2));
    }

    @Test
    public void ignoresTimePortionOfDate() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{4});
        LocalDate d = arbitraryDate();
        LocalDateTime dt1 = LocalDateTime.of(d, LocalTime.of(1, 0));
        LocalDateTime dt2 = LocalDateTime.of(d, LocalTime.of(2, 0));
        subject.reserve(dt1, 3);

        assertFalse(subject.reserve(dt2, 3));
    }

    @Test
    public void supportsMultipleSeatings() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{4}, 120);
        LocalDate d = arbitraryDate();
        LocalDateTime dt1 = LocalDateTime.of(d, LocalTime.of(18, 0));
        LocalDateTime dt2 = LocalDateTime.of(d, LocalTime.of(20, 0));
        subject.reserve(dt1, 4);

        assertTrue(subject.reserve(dt2, 3));
    }

    @Test
    public void rejectsOverlappingBookingsWithMultipleSeatings() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{4}, 120);
        LocalDate d = arbitraryDate();
        LocalDateTime dt1 = LocalDateTime.of(d, LocalTime.of(18, 0));
        LocalDateTime dt2 = LocalDateTime.of(d, LocalTime.of(19, 59));
        subject.reserve(dt1, 4);

        assertFalse(subject.reserve(dt2, 3));
    }

    @Test
    public void combinesMultipleSeatingsAndTables() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(new int[]{2, 4}, 150);
        LocalDate d = arbitraryDate();
        LocalDateTime dt1 = LocalDateTime.of(d, LocalTime.of(0, 0));
        LocalDateTime dt2 = LocalDateTime.of(d, LocalTime.of(17, 45));
        LocalDateTime dt3 = LocalDateTime.of(d, LocalTime.of(20, 0));
        subject.reserve(dt1, 2);
        subject.reserve(dt2, 2);

        assertTrue(subject.reserve(dt3, 3));
    }
}