package pl.isa.freshmenindustries.manage_game;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageGameSimulation {

    ManageGameUtils manageGameService = new ManageGameUtils();
    private final ArrayList<ManageGameOption> gameOptions = manageGameService.createManageGameOptions();
    private String SEPARATOR = "----------------------------------------------------------------------";
    private String VIEW_TITLE = "Enter option number: ";
    private String ERROR_MESSAGE = "Incorrect value. Please try again: ";


    public void startManageOptions() {
        System.out.println(SEPARATOR);
        manageGameService.createViewTitle(gameOptions.size());
        manageGameService.getOptions(gameOptions);
        System.out.println(SEPARATOR);
        System.out.println(VIEW_TITLE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if(input !=null && input >= 1 && input <= gameOptions.size()) {
                manageGameService.goToTheOption(input);
                break;
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }
        scanner.close();
    }

}
