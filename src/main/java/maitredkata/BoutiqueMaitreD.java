package maitredkata;

import java.time.LocalTime;
import java.util.Arrays;

public class BoutiqueMaitreD extends AbstractMaitreD {
    public BoutiqueMaitreD(int tableSize, LocalTime... seatings) throws InvalidSeatingsException {
        super(
            new int[]{tableSize},
            new CommonTableSeatingStrategy(
                    Arrays.asList(seatings)
            )
        );
    }
}