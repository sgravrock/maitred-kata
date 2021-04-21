package maitredkata;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        BoutiqueRestaurant restaurant = new BoutiqueRestaurant();

        try {
            mainLoop(stdin, restaurant);
        } catch (NoSuchElementException e) {
            // Got EOF. Exit.
        }
    }

    private static void mainLoop(Scanner stdin, BoutiqueRestaurant restaurant) {
        while (true) {
            System.out.print("> ");
            String input = stdin.nextLine();
            String[] words = input.split(" ");

            if ("quit".equals(words[0])) {
                // Provide an extra way to exit besides EOF, since IntelliJ
                // traps ctrl-d
                break;
            } else if ("reserve".equals(words[0])) {
                doReservationCmd(words, restaurant);
            } else if ("list".equals(words[0])) {
                doListCmd(words, restaurant);
            } else {
                System.out.println("I don't know that command.");
            }
        }
    }

    private static void doReservationCmd(String[] words, BoutiqueRestaurant restaurant) {
        final String usage = "Usage: reserve numDiners date\nExample: reserve 5 2021-04-21";

        if (words.length != 3) {
            System.out.println(usage);
            return;
        }

        Reservation reservation;

        try {
            reservation = new Reservation(LocalDate.parse(words[2]), Integer.parseInt(words[1]));
        } catch (NumberFormatException e) {
            System.out.println(usage);
            return;
        } catch (DateTimeParseException e) {
            System.out.println(usage);
            return;
        }

        if (restaurant.reserve(reservation)) {
            System.out.println(reservation + " was accepted.");
        } else {
            System.out.println(reservation + " was not accepted.");
        }
    }

    private static void doListCmd(String[] words, BoutiqueRestaurant restaurant) {
        final String usage = "Usage: list";

        if (words.length != 1) {
            System.out.println(usage);
            return;
        }

        List<Reservation> reservations = restaurant.getAllReservations();

        if (reservations.size() == 0) {
            System.out.println("There are no reservations.");
        } else {
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }
}
