package maitredkata;

public class InvalidSeatingsException extends Exception {
    public InvalidSeatingsException() {
        super("At least one seating must be specified.");
    }
}
