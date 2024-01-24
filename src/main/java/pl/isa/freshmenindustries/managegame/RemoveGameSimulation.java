package pl.isa.freshmenindustries.managegame;

import java.util.List;
import java.util.Scanner;

public class RemoveGameSimulation {
    GameUtils gameUtils = new GameUtils();
    private String SEPARATOR = "----------------------------------------------------------------------";
    private String TITLE = "List of games: ";
    private String ERROR_MESSAGE = "Incorrect value. Please try again: ";

    public final void removeGame(List<Game> listOfGames) {

        System.out.println(SEPARATOR);
        System.out.println(TITLE);
        gameUtils.displayListOfGames(listOfGames);
        System.out.println(SEPARATOR);
        gameUtils.displayFunctionTitleWithLimit(listOfGames.size());
        System.out.println("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if(input !=null && input >= 1 && input <= listOfGames.size()) {
                if (gameUtils.confirmRemoveGame(listOfGames.get(input -1).getName())) {
                    gameUtils.removeGameFromList(listOfGames, input);
                    break;
                } else {
                    break;
                }
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }
}