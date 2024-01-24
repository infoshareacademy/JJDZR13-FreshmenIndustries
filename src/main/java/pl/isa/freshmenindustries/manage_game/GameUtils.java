package pl.isa.freshmenindustries.manage_game;

import java.util.List;

public class GameUtils {
<<<<<<< HEAD
    public final void displayListOfGames(List<Game> list) {
=======
    public  final void displayListOfGames(List<Game> list) {
>>>>>>> 4a3bc2f (Refactor)
        for (Game game: list) {
            System.out.println(list.indexOf(game) + 1 + ". " + game.getName());
        }
    }
<<<<<<< HEAD
    public final void removeGameFromList(List<Game> list) {
        list.remove(1);
=======
    public final void removeGameFromList(List<Game> list, int gameIndex) {
        list.remove(gameIndex - 1);
>>>>>>> 4a3bc2f (Refactor)
    }
}
