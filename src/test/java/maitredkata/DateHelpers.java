package maitredkata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateHelpers {
    public static LocalDate arbitraryDate() {
        return LocalDate.of(0, 1, 1);
    }

    public static LocalTime arbitraryTime() {
        return LocalTime.of(1, 1);
    }

    public static LocalDateTime arbitraryDateTime() {
        return LocalDateTime.of(arbitraryDate(), arbitraryTime());
    }

    public static LocalDateTime arbitraryTimeOnDate(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.of(1, 1));
    }
}
