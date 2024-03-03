package pl.isa.freshmenindustries.managegame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameList;
import pl.isa.freshmenindustries.game.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddNewGame {
    private static GameUtils gameUtils = new GameUtils();

    public static void addingNewGame(List<Game> gameList) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**Adding a new game**");

            System.out.println("Enter game name: ");
            String gameName = scanner.nextLine();

            if (gameExists(gameList, gameName)) {
                System.err.println("Game with that name already exists.");
                continue;
            }

            if (!isValidName(gameName)) {
                System.err.println("Game name cannot be empty and must be up to 50 characters long.");
                continue;
            }

            System.out.println("Enter game description: ");
            String gameDescription = scanner.nextLine();

            if (!isValidDescription(gameDescription)) {
                System.err.println("Game description cannot be empty and must be up to 200 characters long.");
                continue;
            }

            gameList.add(new Game(gameName, gameDescription));
            System.out.println("New game added: " + gameName);
            System.out.println("Do you want to add another game? (yes/no)");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("\n**List of games**");
        gameUtils.displayListOfGames(gameList);
        ManageGameSimulation.startManageOptions();
    }
    private static boolean gameExists(List<Game> gamesList, String gameName) {
        for (Game game : gamesList) {
            if (game.getName().equals(gameName)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isValidName(String name) {
        return !name.isEmpty() && name.length() <= 50;
    }
    private static boolean isValidDescription(String description) {
        return !description.isEmpty() && description.length() <= 200;
    }
}
