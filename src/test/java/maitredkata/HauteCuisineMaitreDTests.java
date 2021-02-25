package maitredkata;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HauteCuisineMaitreDTests {
    @Test
    public void acceptsReservationThatFitsAnyTable() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(Arrays.asList(2, 4));

        assertTrue(subject.reserve(arbitraryDate(), 4));
    }

    @Test
    public void rejectsReservationThatFitsNoTable() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(Arrays.asList(2, 4));

        assertFalse(subject.reserve(arbitraryDate(), 5));
    }

    @Test
    public void rejectsSecondReservationWhenTableUsed() {
        HauteCuisineMaitreD subject = new HauteCuisineMaitreD(Arrays.asList(2, 4));
        Date date = arbitraryDate();
        subject.reserve(date, 4);

        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void acceptsSecondReservationWhenTableAvailalabe() {
        HauteCuisineMaitreD subject1 = new HauteCuisineMaitreD(Arrays.asList(4, 2));
        Date date = arbitraryDate();
        subject1.reserve(date, 4);

        assertTrue(subject1.reserve(date, 2));

        // The reverse order should also work
        HauteCuisineMaitreD subject2 = new HauteCuisineMaitreD(Arrays.asList(4, 2));
        subject2.reserve(date, 2);

        assertTrue(subject2.reserve(date, 4));
    }

    private static Date arbitraryDate() {
        return new Date(0);
    }
}
