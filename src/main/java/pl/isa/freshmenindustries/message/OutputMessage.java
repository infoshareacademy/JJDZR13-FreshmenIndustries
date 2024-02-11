package pl.isa.freshmenindustries.message;

public class OutputMessage {
    public static final String SEPARATOR = "----------------------------------------------------------------------";
    public static final String ENTER_OPTION_NUMBER = "Enter number: ";
    public static final String INCORRECT_VALUE_PLEASE_TRY_AGAIN = "Incorrect value. Please try again: ";
    public static final String LIST_OF_GAMES = "List of games: ";
    public static final String REMOVE_TITLE = "Select the game to remove by entering number from 1 to ";
    public static final String EDIT_TITLE = "Select the game to edit by entering number from 1 to ";
    public static final String REMOVE_CONFIRMATION_TITLE = "Please confirm that you would like to remove game.";
    public static final String EDIT_CONFIRMATION_TITLE = "Please confirm that you would like to edit game.";
    public static final String YES_OR_NO_INFO = "Enter Yes or Not (Y/N)";
    public static final String GAME_REMOVED = "Game successfully removed.";
    public static final String GAME_EDITED = "Game has been successfully edited.";
    public static final String ENTER_NEW_NAME  = "Enter new name of the game and press Enter:";
    public static final String INPUT_VALUE_CANT_BE_EMPTY  = "The value can't be empty or the same.";
    public static final String ENTER_NEW_DESCRIPTION  = "Enter new description of the game and press Enter:";


    public static void printOutputMessage(String message) {
        System.out.println(message);
    }
}
