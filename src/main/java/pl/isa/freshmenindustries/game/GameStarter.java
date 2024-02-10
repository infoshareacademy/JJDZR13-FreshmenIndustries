package pl.isa.freshmenindustries.game;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class GameStarter {

    private Scanner scanner;
    private List<Game> games;

    public GameStarter(Scanner scanner) {
        this.scanner = scanner;
        this.games = GameList.games(); // Używa listy z klasy GameList
    }

    public void manageGames() {
        System.out.println("Choose an option: 1 - Start New Game, 2 - Add New Game, 3 - Remove Game");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Czyści scanner po pobraniu liczby

        switch (choice) {
            case 1:
                startNewGame();
                break;
            case 2:
                addNewGame();
                break;
            case 3:
                removeGame();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void startNewGame() {
        if (games.isEmpty()) {
            System.out.println("There are no games available.");
            return;
        }

        System.out.println("Available games:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ": " + games.get(i).getName());
        }

        System.out.println("Please enter the number of the game you want to start:");
        int choice = scanner.nextInt();
        while (choice < 1 || choice > games.size()) {
            System.out.println("Invalid choice. Please try again:");
            choice = scanner.nextInt();
        }

        Game selectedGame = games.get(choice - 1);
        System.out.println("Starting the game: " + selectedGame.getName());
        // Tutaj może być uruchomiona logika gry (np. selectedGame.start();)
    }

    private void addNewGame() {
        System.out.println("Enter game name:");
        String name = scanner.nextLine();
        System.out.println("Enter game description:");
        String description = scanner.nextLine();
        games.add(new Game(name, description));
        System.out.println("Game added: " + name);
    }

    private void removeGame() {
        if (games.isEmpty()) {
            System.out.println("No games to remove.");
            return;
        }
        System.out.println("Enter the number of the game you want to remove:");
        int gameIndex = scanner.nextInt();
        if (gameIndex >= 1 && gameIndex <= games.size()) {
            Game removedGame = games.remove(gameIndex - 1);
            System.out.println("Game removed: " + removedGame.getName());
        } else {
            System.out.println("Invalid game number.");
        }
    }
}
