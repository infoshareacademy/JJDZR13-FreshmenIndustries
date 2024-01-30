package pl.isa.freshmenindustries.managegame;

import pl.isa.freshmenindustries.validator.Validator;

import java.util.List;
import java.util.Scanner;

import static pl.isa.freshmenindustries.message.OutputMessage.*;

public class ManageGameSimulation {

    static List<ManageGameOption> gameOptions = ManageGameOptionList.createManageGameOptions();

    public static void startManageOptions() {
        printOutputMessage(SEPARATOR);
        ManageGameMenu.createViewTitle(gameOptions.size());
        ManageGameMenu.getOptions(gameOptions);
        printOutputMessage(SEPARATOR);
        printOutputMessage(ENTER_OPTION_NUMBER);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if (input != null && input >= 1 && input <= gameOptions.size()) {
                ManageGameMenu.goAndRunTheOption(input);
                break;
            } else {
                printOutputMessage(INCORRECT_VALUE_PLEASE_TRY_AGAIN);
            }
        }
    }
}
