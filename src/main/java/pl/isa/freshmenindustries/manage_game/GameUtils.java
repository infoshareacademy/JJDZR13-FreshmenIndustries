package pl.isa.freshmenindustries.manage_game;

import java.util.List;

public class GameUtils {
    public final void displayListOfGames(List<Game> list) {
        for (Game game: list) {
            System.out.println(list.indexOf(game) + 1 + ". " + game.getName());
        }
    }
    public final void removeGameFromList(List<Game> list) {
        list.remove(1);
    }
}
