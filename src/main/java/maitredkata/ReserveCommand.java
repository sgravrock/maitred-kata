package maitredkata;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReserveCommand implements Command {
    @Override
    public String getName() {
        return "reserve";
    }

    @Override
    public int getNumArgs() {
        return 2;
    }

    @Override
    public String getHelpText() {
        return "Usage: reserve numDiners date\nExample: reserve 5 2021-04-21";
    }

    @Override
    public Result execute(String[] words, BoutiqueRestaurant restaurant) {
        Reservation reservation;

        try {
            reservation = new Reservation(LocalDate.parse(words[2]), Integer.parseInt(words[1]));
        } catch (NumberFormatException | DateTimeParseException e) {
            return Result.USAGE_ERROR;
        }

        if (restaurant.reserve(reservation)) {
            System.out.println(reservation + " was accepted.");
        } else {
            System.out.println(reservation + " was not accepted.");
        }

        return Result.RAN;
    }
}
