package maitredkata;

public class BoutiqueMaitreD extends AbstractMaitreD {
    public BoutiqueMaitreD(int tableSize) {
        super(new int[]{tableSize}, new CommonTableSeatingStrategy());
    }
}