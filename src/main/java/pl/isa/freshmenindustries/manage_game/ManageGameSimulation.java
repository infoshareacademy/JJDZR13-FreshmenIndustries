package pl.isa.freshmenindustries.manage_game;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageGameSimulation {

    ManageGameUtils manageGameService = new ManageGameUtils();
    private final ArrayList<ManageGameOption> gameOptions = manageGameService.createManageGameOptions();
    private String separator = "----------------------------------------------------------------------";
    private String viewTitle = "Enter option number: ";
    private String errorMessage = "Incorrect value. Please try again: ";


    public void startManageOptions() {
        System.out.println(separator);
        manageGameService.createViewTitle(gameOptions);
        manageGameService.getOptions(gameOptions);
        System.out.println(separator);
        System.out.println(viewTitle);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = manageGameService.validateInput(scanner.nextLine());
            if(input !=null && input >= 1 && input <= manageGameService.getOptionsLimit(gameOptions)) {
                manageGameService.goToTheOption(input);
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        scanner.close();
    }

}
