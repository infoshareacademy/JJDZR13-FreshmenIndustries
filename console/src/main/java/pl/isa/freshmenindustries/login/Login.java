package pl.isa.freshmenindustries.login;

import pl.isa.freshmenindustries.basicOptions.BasicOptionSimulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {

    private Map<String, String> zarejestrowaniUzytkownicy;

    public Login() {
        zarejestrowaniUzytkownicy = new HashMap<>();
        zarejestrowaniUzytkownicy.put("tomek", "1234");
        zarejestrowaniUzytkownicy.put("arleta", "1234");
        zarejestrowaniUzytkownicy.put("michał", "1234");
        zarejestrowaniUzytkownicy.put("krzysztof", "1234");
        zarejestrowaniUzytkownicy.put("adrian", "1234");
    }

    public void zaloguj() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Podaj swoją nazwę użytkownika: ");
            String nazwaUzytkownika = scanner.nextLine().toLowerCase();

            if (zarejestrowaniUzytkownicy.containsKey(nazwaUzytkownika)) {
                System.out.print("Podaj hasło: ");
                String haslo = scanner.nextLine();

                if (haslo.equals(zarejestrowaniUzytkownicy.get(nazwaUzytkownika))) {
                    System.out.println("Witaj " + nazwaUzytkownika + ".");
                    // Uruchomienie symulacji opcji
                    BasicOptionSimulation.runBasicOptionSimulation();
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

    public static void main(String[] args) {
        Login login = new Login();
        login.zaloguj();
    }
}