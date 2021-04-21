package maitredkata;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static maitredkata.DateHelpers.*;
import static org.junit.Assert.*;

public class BoutiqueMaitreDTests {
    @Test
    public void acceptsSingleReservationBelowTableSize() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(12);
        assertTrue(subject.reserve(arbitraryDateTime(), 1));
    }

    @Test
    public void acceptsSingleReservationAtTableSize() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(12);
        assertTrue(subject.reserve(arbitraryDateTime(), 12));
    }

    @Test
    public void rejectsSingleReservationAboveTableSize() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(12);
        assertFalse(subject.reserve(arbitraryDateTime(), 13));
    }

    @Test
    public void usesConfiguredTableSize() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(13);
        assertTrue(subject.reserve(arbitraryDateTime(), 13));
    }

    @Test
    public void rejectsSecondReservationThatExceedsTableSize() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        LocalDateTime when = arbitraryDateTime();
        subject.reserve(when, 2);
        assertFalse(subject.reserve(when, 3));
    }

    @Test
    public void acceptsSecondReservationThatDoesNotExceedTableSize() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        LocalDateTime when = arbitraryDateTime();
        subject.reserve(when, 2);
        assertTrue(subject.reserve(when, 2));
    }

    @Test
    public void handlesMoreThanTwoReservations() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(10);
        LocalDateTime when = arbitraryDateTime();
        assertTrue(subject.reserve(when, 3));
        assertTrue(subject.reserve(when, 2));
        assertTrue(subject.reserve(when, 3));

        assertFalse(subject.reserve(when, 3));
    }

    @Test
    public void handlesMultipleDates() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        LocalDate d1 = LocalDate.of(2021, 1, 1);
        LocalDate d2 = LocalDate.of(2021, 1, 2);
        subject.reserve(arbitraryTimeOnDate(d1), 3);

        assertTrue(subject.reserve(arbitraryTimeOnDate(d2), 2));
        assertFalse(subject.reserve(arbitraryTimeOnDate(d1), 2));
    }

    @Test
    public void ignoresTimePortionOfDate() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        LocalDate d = LocalDate.of(2021, 1, 1);
        LocalDateTime t1 = LocalDateTime.of(d, LocalTime.of(1, 0));
        LocalDateTime t2 = LocalDateTime.of(d, LocalTime.of(2, 0));
        subject.reserve(t1, 3);

        assertFalse(subject.reserve(t2, 3));
    }

    @Test
    public void ctorThrowsIfNoSeatingsProvided() {
        try {
            new BoutiqueMaitreD(1);
            fail("Expected an InvalidSeatingsException but none was thrown");
        } catch(InvalidSeatingsException e) {
            // ok
        }
    }

    @Test
    public void rejectsTimesThatDontMatchAnySeating() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4, LocalTime.of(19, 0));
        LocalDateTime ok = LocalDateTime.of(arbitraryDate(), LocalTime.of(19, 0));
        LocalDateTime bad = LocalDateTime.of(arbitraryDate(), LocalTime.of(18, 0));
        assertTrue(subject.reserve(ok, 1));
        assertFalse(subject.reserve(bad, 1));
    }

    @Test
    public void evaluatesEachSeatingSeparately() throws Exception {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(
                4,
                LocalTime.of(19, 0),
                LocalTime.of(20, 30)
        );
        LocalDate d = arbitraryDate();
        LocalDateTime t1 = LocalDateTime.of(d, LocalTime.of(19, 0));
        LocalDateTime t2 = LocalDateTime.of(d, LocalTime.of(20, 30));

        assertTrue(subject.reserve(t1, 3));
        assertFalse(subject.reserve(t1, 2));
        assertTrue(subject.reserve(t2, 4));
    }
}