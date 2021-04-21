package maitredkata;

import java.time.LocalDate;

public class Reservation {
    public final LocalDate date;
    public final int numDiners;

    public Reservation(LocalDate date, int numDiners) {
        this.date = date;
        this.numDiners = numDiners;
    }

    @Override
    public String toString() {
        return "Reservation for " + numDiners +  " diners on " + date;
    }
}
