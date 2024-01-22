package pl.isa.freshmenindustries;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
