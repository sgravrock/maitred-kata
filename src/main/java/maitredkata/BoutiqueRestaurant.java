package maitredkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoutiqueRestaurant {
    private List<Reservation> _reservations = new ArrayList<>();

    public boolean reserve(Reservation reservation) {
        _reservations.add(reservation);
        return true; // TODO
    }

    public List<Reservation> getAllReservations() {
        return _reservations;
    }
}
