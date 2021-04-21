package maitredkata;

import java.util.List;

public class ListCommand implements Command {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public int getNumArgs() {
        return 0;
    }

    @Override
    public String getHelpText() {
        return "Usage: list";
    }

    @Override
    public Result execute(String[] words, BoutiqueRestaurant restaurant) {
        List<Reservation> reservations = restaurant.getAllReservations();

        if (reservations.size() == 0) {
            System.out.println("There are no reservations.");
        } else {
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }

        return Result.RAN;
    }
}
