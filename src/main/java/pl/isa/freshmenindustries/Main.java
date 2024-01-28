package pl.isa.freshmenindustries;
import java.util.InputMismatchException;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Podaj swoją nazwę użytkownika: ");
            String nazwaUzytkownika = scanner.nextLine().toLowerCase();

            if (zarejestrowaniUzytkownicy.containsKey(nazwaUzytkownika)) {
                System.out.print("Podaj hasło: ");
                String haslo = scanner.nextLine();

                if (haslo.equals(zarejestrowaniUzytkownicy.get(nazwaUzytkownika))) {
                    System.out.println("Witaj " + nazwaUzytkownika + ".");
                    mainMenu();
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

    static void mainMenu() {
        BasicOptions basicOptions = new BasicOptions();
        basicOptions.displayOptions();

        System.out.println(basicOptions.enterNumber());
        try {
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    System.out.println(basicOptions.chooseOption(number));
                    System.out.println("You started a new game");
                    break;
                case 2:
                    System.out.println(basicOptions.chooseOption(number));
                    System.out.println("You are in manage games menu");
                    break;
                default:
                    while (number != 1 || number != 2) {
                        System.out.println("Choose again. Enter the number 1 or 2:");
                        Scanner scanner1 = new Scanner(System.in);
                        int newNumber = scanner1.nextInt();
                        if (newNumber == 1) {
                            System.out.println(basicOptions.chooseOption(newNumber));
                            System.out.println("You started a new game");
                            break;
                        }
                        if (newNumber == 2) {
                            System.out.println(basicOptions.chooseOption(newNumber));
                            System.out.println("You are in manage games menu");
                            break;
                        }
                    }
            }
        } catch (InputMismatchException exc) {
            System.out.println("You may only enter natural number. Enter 1 or 2: ");
            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    System.out.println(basicOptions.chooseOption(number));
                    System.out.println("You started a new game");
                    break;
                case 2:
                    System.out.println(basicOptions.chooseOption(number));
                    System.out.println("You are in manage games menu");
                    break;
                default:
                    while (number != 1 || number != 2) {
                        System.out.println("Choose again. Enter the number 1 or 2:");
                        Scanner scanner1 = new Scanner(System.in);
                        int newNumber = scanner1.nextInt();
                        if (newNumber == 1) {
                            System.out.println(basicOptions.chooseOption(newNumber));
                            System.out.println("You started a new game");
                            break;
                        }
                        if (newNumber == 2) {
                            System.out.println(basicOptions.chooseOption(newNumber));
                            System.out.println("You are in manage games menu");
                            break;
                        }
                    }
            }
        }
    }
}
