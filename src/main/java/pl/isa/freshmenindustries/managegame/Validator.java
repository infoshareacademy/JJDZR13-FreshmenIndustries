package pl.isa.freshmenindustries.manage_game;

public final class Validator {
        public static Integer validateIntegerInput(String input) {
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                return null;
            }
        }
}
