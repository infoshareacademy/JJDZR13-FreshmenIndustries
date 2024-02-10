package pl.isa.freshmenindustries.game;

import java.util.List;
import java.util.Scanner;

import static pl.isa.freshmenindustries.message.OutputMessage.*;

public class GameUtils {

    public final void displayListOfGames(List<Game> list) {
        list.forEach(n -> printOutputMessage(list.indexOf(n) + 1 + ". " + n.getName()));
    }
    public final void displayListOfGamesWithDescription(List<Game> list) {
        list.forEach(n -> printOutputMessage(list.indexOf(n) + 1 + ". " + n.getName() + n.getDescription()));
    }


    public final void displayFunctionTitleWithLimit(int limit) {
        printOutputMessage(REMOVE_TITLE + limit);
    }

    public final void removeGameFromList(List<Game> list, int index) {
        list.remove(index - 1);
        printOutputMessage(GAME_REMOVED);
    }

    public final boolean runAndConfirmRemoveGame(String name) {
        printOutputMessage("You are about to remove the game " + name + "." + REMOVE_CONFIRMATION_TITLE + " " + YES_OR_NO_INFO);
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
                printOutputMessage("Incorrect value." + " " + YES_OR_NO_INFO);
            }
        }
        return isConfirmed;
    }
}
