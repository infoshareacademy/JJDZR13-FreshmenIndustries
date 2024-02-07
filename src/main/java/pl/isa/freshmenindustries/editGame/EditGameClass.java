package pl.isa.freshmenindustries.editGame;

import pl.isa.freshmenindustries.game.GameList;
import pl.isa.freshmenindustries.managegame.ManageGameSimulation;

public class EditGameClass {
    public static void runEditGameClass() {
        EditGame editGame = new EditGame();
        editGame.editGameList(GameList.games());
        ManageGameSimulation.startManageOptions();
    }
}
