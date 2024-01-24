package pl.isa.freshmenindustries.helpers;

public final class Validator {
        public static Integer validateIntegerInput(String input) {
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                return null;
            }
        }
}
