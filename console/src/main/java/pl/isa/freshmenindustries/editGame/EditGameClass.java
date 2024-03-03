package pl.isa.freshmenindustries.editGame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameList;
import pl.isa.freshmenindustries.managegame.ManageGameSimulation;

import java.util.List;

public class EditGameClass {
    public static void runEditGameClass(List<Game> listOfGames) {
        EditGame editGame = new EditGame();
        editGame.editGameList(listOfGames);
        ManageGameSimulation.startManageOptions();
    }
}
