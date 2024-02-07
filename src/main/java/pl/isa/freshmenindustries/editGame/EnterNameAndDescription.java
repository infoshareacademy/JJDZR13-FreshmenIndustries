package pl.isa.freshmenindustries.editGame;

import pl.isa.freshmenindustries.game.Game;
import java.util.List;
import java.util.Scanner;
import static pl.isa.freshmenindustries.message.OutputMessage.*;
import static pl.isa.freshmenindustries.message.OutputMessage.YES_OR_NO_INFO;

public class EnterNameAndDescription {
    Scanner scanner = new Scanner(System.in);
    public final boolean runAndConfirmEditGame(String name) {
        printOutputMessage("You are about to edit the game " + name + "." + EDIT_CONFIRMATION_TITLE + " " + YES_OR_NO_INFO);
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
    public void enterNameAndDescription(List<Game> list, int index) {
        System.out.println("Enter the new name and press Enter, enter the new description and press Enter.");
        list.set(index, new Game(scanner.nextLine(), ", " + scanner.nextLine()));
    }
    public final void displayFunctionTitleWithLimit(int limit) {
        printOutputMessage(EDIT_TITLE + limit);
    }
}
