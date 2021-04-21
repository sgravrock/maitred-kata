package maitredkata;

import java.util.List;

public class HelpCommand implements Command {
    private List<Command> _commands;

    public HelpCommand(List<Command> commands) {
        _commands = commands;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public int getNumArgs() {
        return 0;
    }

    @Override
    public String getHelpText() {
        return "Usage: help";
    }

    @Override
    public Result execute(String[] words, BoutiqueRestaurant restaurant) {
        System.out.println("Available commands:");

        for (Command cmd : _commands) {
            System.out.println(cmd.getName());
        }

        return Result.RAN;
    }
}
