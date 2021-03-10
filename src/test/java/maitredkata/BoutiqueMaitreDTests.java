package maitredkata;

import org.junit.Test;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class BoutiqueMaitreDTests {
    @Test
    public void acceptsSingleReservationBelowTableSize() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(12);
        assertTrue(subject.reserve(arbitraryDate(), 1));
    }

    @Test
    public void acceptsSingleReservationAtTableSize() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(12);
        assertTrue(subject.reserve(arbitraryDate(), 12));
    }

    @Test
    public void rejectsSingleReservationAboveTableSize() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(12);
        assertFalse(subject.reserve(arbitraryDate(), 13));
    }

    @Test
    public void usesConfiguredTableSize() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(13);
        assertTrue(subject.reserve(arbitraryDate(), 13));
    }

    @Test
    public void rejectsSecondReservationThatExceedsTableSize() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        Date date = arbitraryDate();
        subject.reserve(date, 2);
        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void acceptsSecondReservationThatDoesNotExceedTableSize() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        Date date = arbitraryDate();
        subject.reserve(date, 2);
        assertTrue(subject.reserve(date, 2));
    }

    @Test
    public void handlesMoreThanTwoReservations() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(10);
        Date date = arbitraryDate();
        assertTrue(subject.reserve(date, 3));
        assertTrue(subject.reserve(date, 2));
        assertTrue(subject.reserve(date, 3));

        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void handlesMultipleDates() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 2).getTime();
        subject.reserve(d1, 3);

        assertTrue(subject.reserve(d2, 2));
        assertFalse(subject.reserve(d1, 2));
    }

    @Test
    public void ignoresTimePortionOfDate() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4);
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d1.setHours(1);
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d2.setHours(2);
        subject.reserve(d1, 3);

        assertFalse(subject.reserve(d2, 3));
    }

    @Test
    public void withMultipleSeatingTimes_rejectsTimesThatDontMatchAnySeating() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(4, LocalTime.of(19, 0));
        Date okDate = arbitraryDate();
        okDate.setHours(19);
        okDate.setMinutes(0);
        assertTrue(subject.reserve(okDate, 1));
        Date badDate = arbitraryDate();
        badDate.setHours(18);
        assertFalse(subject.reserve(badDate, 1));
    }

    @Test
    public void withMultipleSeatingTimes_evaluatesEachSeatingSeparately() {
        BoutiqueMaitreD subject = new BoutiqueMaitreD(
                4,
                LocalTime.of(19, 0),
                LocalTime.of(20, 30)
        );
        Date d1 = arbitraryDate();
        Date d2 = (Date) d1.clone();
        d1.setHours(19);
        d1.setMinutes(0);
        d2.setHours(20);
        d2.setMinutes(30);

        assertTrue(subject.reserve(d1, 3));
        assertFalse(subject.reserve(d1, 2));
        assertTrue(subject.reserve(d2, 4));
    }

    private static Date arbitraryDate() {
        // Any time that's >= 1 day after epoch
        // (so we don't underflow when setting hours) will do.
        return new Date(1577931236);
    }
}