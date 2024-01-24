package pl.isa.freshmenindustries.manage_game;

import java.util.ArrayList;

public class ManageGameUtils {
    private final ArrayList<ManageGameOption> gameOptions = new ArrayList<>();
    public final ArrayList<ManageGameOption> createManageGameOptions() {
        gameOptions.add(new ManageGameOption("Add new game", 1, false));
        gameOptions.add(new ManageGameOption("Edit game", 2, false));
        gameOptions.add(new ManageGameOption("Delete game", 3, false));
        gameOptions.add(new ManageGameOption("Return", 4, true));
        return gameOptions;
    }
    public void getOptions(ArrayList<ManageGameOption> manageGameOptions) {
        for (ManageGameOption gameOption : manageGameOptions) {
            if (gameOption.isReturn()) {
                System.out.println("***");
            }
            System.out.println(gameOption.getOrder() + ".  " + gameOption.getName());
        }
    }
    public int getOptionsLimit(ArrayList<ManageGameOption> manageGameOptions) {

        return manageGameOptions.size();
    }
    public void createViewTitle(ArrayList<ManageGameOption> manageGameOptions) {
        System.out.println("Select one of the below option by entering number from 1 to " + this.getOptionsLimit(manageGameOptions) + " : ");
    }
    public Integer validateInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            return null;
        }
    }
    public void goToTheOption(int option) {
        switch (option) {
            case 1: System.out.println("Odpalamy opcje 1");
            case 2: System.out.println("Odpalamy opcje 2");
            case 3: System.out.println("Odpalamy opcje 3");
            case 4: System.out.println("Odpalamy opcje 4");
        }
    }

}
