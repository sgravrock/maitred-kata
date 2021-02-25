package maitredkata;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class MaitreDTest {
    @Test
    public void acceptsSingleReservationBelowTableSize() {
        MaitreD subject = new MaitreD(12);
        assertTrue(subject.reserve(arbitraryDate(), 1));
    }

    @Test
    public void acceptsSingleReservationAtTableSize() {
        MaitreD subject = new MaitreD(12);
        assertTrue(subject.reserve(arbitraryDate(), 12));
    }

    @Test
    public void rejectsSingleReservationAboveTableSize() {
        MaitreD subject = new MaitreD(12);
        assertFalse(subject.reserve(arbitraryDate(), 13));
    }

    @Test
    public void usesConfiguredTableSize() {
        MaitreD subject = new MaitreD(13);
        assertTrue(subject.reserve(arbitraryDate(), 13));
    }

    @Test
    public void rejectsSecondReservationThatExceedsTableSize() {
        MaitreD subject = new MaitreD(4);
        Date date = arbitraryDate();
        subject.reserve(date, 2);
        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void acceptsSecondReservationThatDoesNotExceedTableSize() {
        MaitreD subject = new MaitreD(4);
        Date date = arbitraryDate();
        subject.reserve(date, 2);
        assertTrue(subject.reserve(date, 2));
    }

    @Test
    public void handlesMoreThanTwoReservations() {
        MaitreD subject = new MaitreD(10);
        Date date = arbitraryDate();
        assertTrue(subject.reserve(date, 3));
        assertTrue(subject.reserve(date, 2));
        assertTrue(subject.reserve(date, 3));

        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void handlesMultipleDates() {
        MaitreD subject = new MaitreD(4);
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 2).getTime();
        subject.reserve(d1, 3);

        assertTrue(subject.reserve(d2, 2));
        assertFalse(subject.reserve(d1, 2));
    }

    @Test
    public void ignoresTimePortionOfDate() {
        MaitreD subject = new MaitreD(4);
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d1.setHours(1);
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        d2.setHours(2);
        subject.reserve(d1, 3);

        assertFalse(subject.reserve(d2, 3));
    }

    private static Date arbitraryDate() {
        return new Date(0);
    }
}