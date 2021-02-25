package maitredkata;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class MaitreDTest {
    @Test
    public void acceptsSingleReservationBelowTableSize() {
        MaitreD subject = new MaitreD();
        assertTrue(subject.reserve(arbitraryDate(), 1));
    }

    @Test
    public void acceptsSingleReservationAtTableSize() {
        MaitreD subject = new MaitreD();
        assertTrue(subject.reserve(arbitraryDate(), 12));
    }

    @Test
    public void rejectsSingleReservationAboveTableSize() {
        MaitreD subject = new MaitreD();
        assertFalse(subject.reserve(arbitraryDate(), 13));
    }

    private static Date arbitraryDate() {
        return new Date(0);
    }
}