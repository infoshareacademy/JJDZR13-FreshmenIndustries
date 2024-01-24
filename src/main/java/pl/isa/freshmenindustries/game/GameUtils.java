package pl.isa.freshmenindustries.game;

import java.util.List;
import java.util.Scanner;

public class GameUtils {
    private final String REMOVE_TITLE = "Select the game to remove by entering number from 1 to ";
    private final String REMOVE_CONFIRMATION_TITLE = "Please confirm that you would like to remove game by entering YES or NOT (Y/N)";

    public final void displayListOfGames(List<Game> list) {
        for (Game game : list) {
            System.out.println(list.indexOf(game) + 1 + ". " + game.getName());
        }
    }

    public final void displayFunctionTitleWithLimit(int limit) {
        System.out.println(REMOVE_TITLE + limit);
    }

    public final void removeGameFromList(List<Game> list, int index) {
        list.remove(index - 1);
        System.out.println("Game successfully removed.");
    }

    public final boolean confirmRemoveGame(String name) {
        System.out.println("You are removing game name " + name + "." + REMOVE_CONFIRMATION_TITLE);
        boolean isConfirmed = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("Y")) {
                isConfirmed = true;
                break;
            } else if (input.equalsIgnoreCase("NO") || input.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Incorrect value. Enter Yes or Not (Y?N)");
            }
        }
        return isConfirmed;
    }
}
