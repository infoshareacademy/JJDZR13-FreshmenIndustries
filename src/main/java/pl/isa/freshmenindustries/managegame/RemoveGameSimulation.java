package pl.isa.freshmenindustries.managegame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameUtils;
import pl.isa.freshmenindustries.validator.Validator;

import java.util.List;
import java.util.Scanner;

import static pl.isa.freshmenindustries.message.OutputMessage.*;

public class RemoveGameSimulation {
    GameUtils gameUtils = new GameUtils();

    public final void removeGame(List<Game> listOfGames) {
        System.out.println(SEPARATOR);
        System.out.println(LIST_OF_GAMES);
        gameUtils.displayListOfGames(listOfGames);
        System.out.println(SEPARATOR);
        gameUtils.displayFunctionTitleWithLimit(listOfGames.size());
        System.out.println("Insert number: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if (input != null && input >= 1 && input <= listOfGames.size()) {
                if (gameUtils.confirmRemoveGame(listOfGames.get(input - 1).getName())) {
                    gameUtils.removeGameFromList(listOfGames, input);
                    break;
                } else {
                    break;
                }
            } else {
                System.out.println(INCORRECT_VALUE_PLEASE_TRY_AGAIN);
            }
        }
    }
}