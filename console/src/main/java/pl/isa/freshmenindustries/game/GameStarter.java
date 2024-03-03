package pl.isa.freshmenindustries.game;

import java.util.List;
import java.util.Scanner;

public class GameStarter {

    static private List<Game> games = GameList.games();
    private static GameUtils gameUtils = new GameUtils();

    public static void startNewGame() {
        if (games.isEmpty()) {
            System.out.println("There are no games available.");
            return;
        }
        System.out.println("Available games:");
        gameUtils.displayListOfGames(games);

        System.out.println("Please enter the number of the game you want to start:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while (choice < 1 || choice > games.size()) {
            System.out.println("Invalid choice. Please try again:");
            choice = scanner.nextInt();
        }

        Game selectedGame = games.get(choice - 1);
        System.out.println("Starting the game: " + selectedGame.getName());
        // Tutaj może być uruchomiona logika gry (np. selectedGame.start();)
    }
}
