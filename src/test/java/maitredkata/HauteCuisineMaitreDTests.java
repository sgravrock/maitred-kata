package maitredkata;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
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

    private static Date arbitraryDate() {
        return new Date(0);
    }
}
