package maitredkata;

import org.junit.Test;

import java.util.Date;

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
    public void rejectsSubsequentReservationThatExceedsTableSize() {
        MaitreD subject = new MaitreD(4);
        Date date = arbitraryDate();
        subject.reserve(date, 2);
        assertFalse(subject.reserve(date, 3));
    }

    @Test
    public void acceptsSubsequentReservationThatDoNotExceedTableSize() {
        MaitreD subject = new MaitreD(4);
        Date date = arbitraryDate();
        subject.reserve(date, 2);
        assertTrue(subject.reserve(date, 2));
    }

    private static Date arbitraryDate() {
        return new Date(0);
    }
}