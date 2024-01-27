package pl.isa.freshmenindustries.managegame;

import java.util.List;

public class RemoveGameSimulation {
    GameUtils gameUtils = new GameUtils();
    private String separator = "----------------------------------------------------------------------";
    private String title = "List of games: ";

    public final void removeGame(List<Game> listOfGames) {

        System.out.println(separator);
        System.out.println(title);
        gameUtils.displayListOfGames(listOfGames);
    }
}
