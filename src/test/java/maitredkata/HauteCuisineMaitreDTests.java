package maitredkata;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HauteCuisineMaitreDTests {
    @Test
    public void acceptsReservationThatFitsAnyTable() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(2, 4);

        assertTrue(subject.reserve(arbitraryDate(), 4));
    }

    @Test
    public void rejectsReservationThatFitsNoTable() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(2, 4);

        assertFalse(subject.reserve(arbitraryDate(), 5));
    }

    @Test
    public void rejectsSecondReservationWhenTableUsed() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(2, 4);
        Date date = arbitraryDate();
        subject.reserve(date, 4);

        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void acceptsSecondReservationWhenTableAvailalabe() {
        HauteCuisineMaitreD subject1 = new HauteCuisineMaitreD(4, 2);
        Date date = arbitraryDate();
        subject1.reserve(date, 4);

        assertTrue(subject1.reserve(date, 2));

        // The reverse order should also work
        HauteCuisineMaitreD subject2 = new HauteCuisineMaitreD(4, 2);
        subject2.reserve(date, 2);

        assertTrue(subject2.reserve(date, 4));
    }

    @Test
    public void handlesMultipleDates() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(4);
        Date d1 = new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime();
        Date d2 = new GregorianCalendar(2021, Calendar.JANUARY, 2).getTime();
        subject.reserve(d1, 3);

        assertTrue(subject.reserve(d2, 2));
        assertFalse(subject.reserve(d1, 2));
    }

    @Test
    public void ignoresTimePortionOfDate() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(4);
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
