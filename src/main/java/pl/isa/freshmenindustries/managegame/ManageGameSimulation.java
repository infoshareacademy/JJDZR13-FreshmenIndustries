package pl.isa.freshmenindustries.managegame;

import pl.isa.freshmenindustries.validator.Validator;

import java.util.ArrayList;
import java.util.Scanner;

import static pl.isa.freshmenindustries.message.OutputMessage.*;

public class ManageGameSimulation {

    ManageGameMenu manageGameMenu = new ManageGameMenu();
    private final ArrayList<ManageGameOption> gameOptions = manageGameMenu.createManageGameOptions();



    public void startManageOptions() {
        System.out.println(SEPARATOR);
        manageGameMenu.createViewTitle(gameOptions.size());
        manageGameMenu.getOptions(gameOptions);
        System.out.println(SEPARATOR);
        System.out.println(ENTER_OPTION_NUMBER);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if(input !=null && input >= 1 && input <= gameOptions.size()) {
                manageGameMenu.goToTheOption(input);
                break;
            } else {
                System.out.println(INCORRECT_VALUE_PLEASE_TRY_AGAIN);
            }
        }
        scanner.close();
    }

}
