package pl.isa.freshmenindustries.managegame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameUtils;
import pl.isa.freshmenindustries.validator.Validator;

import java.util.List;
import java.util.Scanner;

import static pl.isa.freshmenindustries.message.OutputMessage.*;

public class RemoveGameSimulation {
    private static GameUtils gameUtils = new GameUtils();

    public static final void removeGame(List<Game> listOfGames) {
        printOutputMessage(SEPARATOR);
        printOutputMessage(LIST_OF_GAMES);
        gameUtils.displayListOfGames(listOfGames);
        printOutputMessage(SEPARATOR);
        gameUtils.displayFunctionTitleWithLimit(listOfGames.size());
        printOutputMessage(ENTER_OPTION_NUMBER);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if (input != null && input >= 1 && input <= listOfGames.size()) {
                if (gameUtils.runAndConfirmRemoveGame(listOfGames.get(input - 1).getName())) {
                    gameUtils.removeGameFromList(listOfGames, input);
                    break;
                } else {
                    break;
                }
            } else {
                printOutputMessage(INCORRECT_VALUE_PLEASE_TRY_AGAIN);
            }
        }
    }
}