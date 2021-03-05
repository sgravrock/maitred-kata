package maitredkata;

public class HauteCuisineMaitreD extends AbstractMaitreD {
    // For restaurants with only one seating.
    public HauteCuisineMaitreD(int[] tableSizes) {
        this(tableSizes, null);
    }

    public HauteCuisineMaitreD(int[] tableSizes, Integer seatingDurationMins) {
        super(
                tableSizes,
                seatingDurationMins == null ? new SingleSeatingStrategy() : new MultipleSeatingStrategy(seatingDurationMins)
        );
    }
}
