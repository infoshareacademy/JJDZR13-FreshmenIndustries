package pl.isa.freshmenindustries.newGame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameList;

import java.util.List;
import java.util.Scanner;

public class NewGame {

    private GameList games;

    public NewGame(GameList gameList) {
        GameList GameList = new GameList();
        this.games = GameList;
    }

    public void addNewGame() {
        // Pobranie danych od użytkownika
        System.out.println("Podaj nazwę gry:");
        String name = ScannerUtils.nextLine();

        System.out.println("Podaj opis gry:");
        String description = ScannerUtils.nextLine();

        // Walidacja danych
        if (name.isEmpty() || name.length() > 50) {
            System.err.println("Nazwa gry musi mieć od 1 do 50 znaków!");
            return;
        }

        if (description.isEmpty() || description.length() > 200) {
            System.err.println("Opis gry musi mieć od 1 do 200 znaków!");
            return;
        }

        // Sprawdzenie, czy gra o takiej nazwie już istnieje
        if (games.containsGame(name)) {
            System.err.println("Gra o nazwie " + name + " już istnieje!");
            return;
        }

        // Utworzenie nowej gry
        Game game = new Game(name, description);

        // Dodanie gry do listy
        games.add(game);

        // Wyświetlenie informacji o dodaniu gry
        System.out.println("Gra " + name + " została dodana!");
    }

    private static class ScannerUtils {

        private static Scanner scanner = new Scanner(System.in);

        public static String nextLine() {
            return scanner.nextLine();
        }
    }
}
