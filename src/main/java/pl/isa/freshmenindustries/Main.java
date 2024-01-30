package pl.isa.freshmenindustries;

import pl.isa.freshmenindustries.basicOptions.BasicOptions;
import pl.isa.freshmenindustries.game.GameList;

import java.util.HashMap;
import java.util.InputMismatchException;
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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Podaj swoją nazwę użytkownika: ");
            String nazwaUzytkownika = scanner.nextLine().toLowerCase();

            if (zarejestrowaniUzytkownicy.containsKey(nazwaUzytkownika)) {
                System.out.print("Podaj hasło: ");
                String haslo = scanner.nextLine();

                if (haslo.equals(zarejestrowaniUzytkownicy.get(nazwaUzytkownika))) {
                    System.out.println("Witaj " + nazwaUzytkownika + ".");
                    BasicOptions basicOptions = new BasicOptions();
                    basicOptions.displayOptions();
                    basicOptions.enterNumber();
                    try {
                        basicOptions.enterInput(GameList.games());
                    } catch (InputMismatchException exc) {
                        System.out.println("Invalid value. You can only enter integer number. Enter 1 or 2:");
                        basicOptions.enterInput(GameList.games());
                    }

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
