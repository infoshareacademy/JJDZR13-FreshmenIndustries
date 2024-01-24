package pl.isa.freshmenindustries;

import pl.isa.freshmenindustries.manage_game.Game;
import pl.isa.freshmenindustries.manage_game.GameUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, String> zarejestrowaniUzytkownicy = new HashMap<>();
        zarejestrowaniUzytkownicy.put("tomek", "1234");
        zarejestrowaniUzytkownicy.put("arleta", "1234");
        zarejestrowaniUzytkownicy.put("michał", "1234");
        zarejestrowaniUzytkownicy.put("krzystof", "1234");
        zarejestrowaniUzytkownicy.put("adrian", "1234");

        ArrayList<Game> listOfGames = new ArrayList<Game>();
        listOfGames.add( new Game("Gra 1", "Desc 1"));
        listOfGames.add(new Game("Gra 2", "Desc 2"));
        listOfGames.add(new Game("Gra 3", "Desc 3"));
        GameUtils gameUtilise = new GameUtils();
        gameUtilise.removeGameFromList(listOfGames);
        gameUtilise.displayListOfGames(listOfGames);

        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("Podaj swoją nazwę użytkownika: ");
            String nazwaUzytkownika = scanner.nextLine().toLowerCase();

            if (zarejestrowaniUzytkownicy.containsKey(nazwaUzytkownika)) {
                System.out.print("Podaj hasło: ");
                String haslo = scanner.nextLine();

                if (haslo.equals(zarejestrowaniUzytkownicy.get(nazwaUzytkownika))) {
                    System.out.println("Witaj " + nazwaUzytkownika + ".");
                    break;
                } else {
                    System.out.println("Nieprawidłowe hasło. Spróbuj ponownie.");
                }
            } else {
                System.out.println("Użytkownik o podanej nazwie nie istnieje. Spróbuj ponownie.");
            }
        }
        scanner.close();
    }
}
