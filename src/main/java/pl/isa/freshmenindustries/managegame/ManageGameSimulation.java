package pl.isa.freshmenindustries.managegame;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageGameSimulation {

    ManageGameMenu manageGameService = new ManageGameMenu();
    private final ArrayList<ManageGameOption> gameOptions = manageGameService.createManageGameOptions();
    private final String SEPARATOR = "----------------------------------------------------------------------";
    private final String VIEW_TITLE = "Enter option number: ";
    private final String ERROR_MESSAGE = "Incorrect value. Please try again: ";


    public void startManageOptions() {
        System.out.println(SEPARATOR);
        manageGameService.createViewTitle(gameOptions);
        manageGameService.getOptions(gameOptions);
        System.out.println(SEPARATOR);
        System.out.println(VIEW_TITLE);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = manageGameService.validateInput(scanner.nextLine());
            if(input !=null && input >= 1 && input <= manageGameService.getOptionsLimit(gameOptions)) {
                manageGameService.goToTheOption(input);
                break;
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }
        scanner.close();
    }

}
