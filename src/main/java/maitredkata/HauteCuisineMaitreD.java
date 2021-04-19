package maitredkata;

public class HauteCuisineMaitreD extends AbstractMaitreD {
    // For restaurants with only one seating.
    public HauteCuisineMaitreD(int[] tableSizes) {
        this(tableSizes, null);
    }

    // TODO: This doesn't make sense. Seems to imply that each seating is N
    // minutes after whenever the first reservation is booked, but they should
    // be at specified times.
    public HauteCuisineMaitreD(int[] tableSizes, Integer seatingDurationMins) {
        super(
                tableSizes,
                seatingDurationMins == null ? new SingleSeatingStrategy() : new MultipleSeatingStrategy(seatingDurationMins)
        );
    }
}
