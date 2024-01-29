package pl.isa.freshmenindustries.managegame;

import java.util.ArrayList;
import java.util.List;

public class ManageGameOptionList {

    private static List<ManageGameOption> gameOptions = new ArrayList<>();

    public static final List<ManageGameOption> createManageGameOptions() {
        gameOptions.add(new ManageGameOption("Add new game", 1, false));
        gameOptions.add(new ManageGameOption("Edit game", 2, false));
        gameOptions.add(new ManageGameOption("Delete game", 3, false));
        gameOptions.add(new ManageGameOption("Return", 4, true));
        return gameOptions;
    }
}
