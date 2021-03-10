package maitredkata;

import org.junit.Test;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;

public class ReservationTest {
    @Test
    public void getTimeOfDayReturnsTimeOfDay() {
        Date d = new Date(0);
        d.setHours(17);
        d.setMinutes(23);
        Reservation subject = new Reservation(d, 1);

        assertEquals(LocalTime.of(17, 23), subject.getTimeOfDay());
    }
}