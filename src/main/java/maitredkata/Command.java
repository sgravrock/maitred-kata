package maitredkata;

public interface Command {
    public enum Result {
        RAN,
        USAGE_ERROR
    }

    public String getName();
    public int getNumArgs();
    public String getHelpText();
    public Result execute(String[] words, BoutiqueRestaurant restaurant);
}
