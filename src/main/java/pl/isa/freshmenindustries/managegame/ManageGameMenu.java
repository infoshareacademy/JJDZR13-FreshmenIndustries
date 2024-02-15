package pl.isa.freshmenindustries.managegame;

import pl.isa.freshmenindustries.basicOptions.BasicOptionSimulation;
import pl.isa.freshmenindustries.editGame.EditGame;
import pl.isa.freshmenindustries.editGame.EditGameClass;
import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameList;

import java.util.List;

import static pl.isa.freshmenindustries.message.OutputMessage.printOutputMessage;

public class ManageGameMenu {
    private static List<Game> listOfGames = GameList.games();

    public static void getOptions(List<ManageGameOption> manageGameOptions) {
        manageGameOptions.forEach(o -> {
            if (o.isReturn()) {
                printOutputMessage("***");
            }
            printOutputMessage(o.toStringShort());
        });
    }

    public static void createViewTitle(int manageGameOptions) {
        printOutputMessage("Select one of the below option by entering number from 1 to " + manageGameOptions + " : ");
    }

    public static void goAndRunTheOption(int option) {
        switch (option) {
            case 1:
                AddNewGame.addingNewGame(listOfGames);
                break;
            case 2:
                EditGameClass.runEditGameClass(listOfGames);
                break;
            case 3:
                RemoveGameSimulation.removeGame(listOfGames);
                break;
            case 4:
                BasicOptionSimulation.runBasicOptionSimulation();
                break;
        }
    }
}
