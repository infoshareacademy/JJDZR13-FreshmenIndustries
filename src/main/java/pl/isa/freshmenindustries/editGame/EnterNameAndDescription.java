package pl.isa.freshmenindustries.editGame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.validator.Validator;

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
        System.out.println("Enter new name of the game and press Enter:");
        String oldName = list.get(index).getName();
        enterInputToEditGame(oldName, list.get(index).setName(scanner.nextLine() + ", "));
    }

    public void enterDescription(List<Game> list, int index) {
        System.out.println("Enter new description of the game and press Enter:");
        String oldDescription = list.get(index).getName();
        enterInputToEditGame(list.get(index).setDescription(scanner.nextLine()), oldDescription);
    }

    public String enterInputToEditGame(String input, String toCompareString) {
        String newInput;
        while (true) {
            if (Validator.validateAndCompareStringInput(input, toCompareString)) {
                newInput = input;
                break;
            } else {
                System.out.println("The value can't be empty or the same.");
            }
        }
        return newInput;
    }

    public final void displayFunctionTitleWithLimit(int limit) {
        printOutputMessage(EDIT_TITLE + limit);
    }
}
