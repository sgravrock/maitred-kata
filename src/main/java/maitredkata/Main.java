package maitredkata;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        BoutiqueRestaurant restaurant = new BoutiqueRestaurant();
        List<Command> commands = new ArrayList<>();
        commands.add(new ListCommand());
        commands.add(new ReserveCommand());
        commands.add(new HelpCommand(commands));

        try {
            mainLoop(stdin, restaurant, commands);
        } catch (NoSuchElementException e) {
            // Got EOF. Exit.
        }
    }

    private static void mainLoop(
            Scanner stdin,
            BoutiqueRestaurant restaurant,
            List<Command> commands
    ) {
        while (true) {
            System.out.print("> ");
            String input = stdin.nextLine();
            String[] words = input.split(" ");

            if ("quit".equals(words[0])) {
                // Provide an extra way to exit besides EOF, since IntelliJ
                // traps ctrl-d
                return;
            }

            Command cmd = findCmdByName(words[0], commands);

            if (cmd == null) {
                System.out.println("Unrecognized command: " + words[0]);
                findCmdByName("help", commands).execute(new String[]{}, restaurant);
            } else if (cmd.getNumArgs() != words.length - 1
                    || cmd.execute(words, restaurant) == Command.Result.USAGE_ERROR) {
                System.out.println(cmd.getHelpText());
            }
        }
    }

    private static Command findCmdByName(String name, List<Command> commands) {
        for (Command cmd : commands) {
            if (cmd.getName().equals(name)) {
                return cmd;
            }
        }

        return null;
    }
}