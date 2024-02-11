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

    public void enterName(List<Game> list, int index) {
        while (true) {
            System.out.println(ENTER_NEW_NAME);
            String oldName = list.get(index).getName();
            String newName = scanner.nextLine();
            if (!newName.isEmpty() && !newName.equals(oldName)) {
                list.get(index).setName(newName);
                break;
            } else {
                System.out.println(INPUT_VALUE_CANT_BE_EMPTY);
            }
        }
    }

    public void enterDescription(List<Game> list, int index) {
        while (true) {
            System.out.println(ENTER_NEW_DESCRIPTION);
            String oldDescription = list.get(index).getDescription();
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty() && !newDescription.equals(oldDescription)) {
                list.get(index).setDescription(newDescription);
                break;
            } else {
                System.out.println(INPUT_VALUE_CANT_BE_EMPTY);
            }
        }
    }

    public final void displayFunctionTitleWithLimit(int limit) {
        printOutputMessage(EDIT_TITLE + limit);
    }
}

