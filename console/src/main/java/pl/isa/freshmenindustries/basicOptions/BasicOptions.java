package pl.isa.freshmenindustries.basicOptions;

import pl.isa.freshmenindustries.game.GameStarter;
import pl.isa.freshmenindustries.managegame.ManageGameSimulation;

import java.util.Scanner;

public class BasicOptions {

    public void displayOptions() {
        System.out.println("Options:");
        System.out.println("1. New game");
        System.out.println("2. Manage games");
    }

    public void enterNumber() {
        System.out.println("Enter the number of the chosen option: ");
    }

    public int chooseOption(int number) {
        if (number == 1) {
            System.out.println("You chose option number: ");
        }
        if (number == 2) {
            System.out.println("You chose option number: ");
        }
        return number;
    }

    public void enterInput() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        while (number != 1 && number != 2) {
            System.out.println("Choose again. Enter 1 or 2:");
            number = scanner.nextInt();
        }
        switch (number) {
            case 1:
                GameStarter.startNewGame();
                break;
            case 2:
                ManageGameSimulation.startManageOptions();
                break;
        }
    }
}
