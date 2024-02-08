package pl.isa.freshmenindustries.editGame;

import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.game.GameUtils;
import pl.isa.freshmenindustries.validator.Validator;

import java.util.List;
import java.util.Scanner;

import static pl.isa.freshmenindustries.message.OutputMessage.*;
import static pl.isa.freshmenindustries.message.OutputMessage.ENTER_OPTION_NUMBER;

public class EditGame {
    EnterNameAndDescription enterNameAndDescription = new EnterNameAndDescription();

    public void editGameList(List<Game> listOfGames) {

        GameUtils gameUtils = new GameUtils();
        printOutputMessage(SEPARATOR);
        printOutputMessage(LIST_OF_GAMES);
        gameUtils.displayListOfGames(listOfGames);
        printOutputMessage(SEPARATOR);
        enterNameAndDescription.displayFunctionTitleWithLimit(listOfGames.size());
        printOutputMessage(ENTER_OPTION_NUMBER);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Integer input = Validator.validateIntegerInput(scanner.nextLine());
            if (input != null && input >= 1 && input <= listOfGames.size()) {
                if (enterNameAndDescription.runAndConfirmEditGame(listOfGames.get(input - 1).getName())) {
                    enterNameAndDescription.enterName(listOfGames, input - 1);
                    enterNameAndDescription.enterDescription(listOfGames, input - 1);
                    printOutputMessage(GAME_EDITED);
                    gameUtils.displayListOfGamesWithDescription(listOfGames);
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
