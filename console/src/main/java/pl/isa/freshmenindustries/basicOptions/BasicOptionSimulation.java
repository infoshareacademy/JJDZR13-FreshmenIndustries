package pl.isa.freshmenindustries.basicOptions;

import java.util.InputMismatchException;

public class BasicOptionSimulation {
    public static void runBasicOptionSimulation() {
        BasicOptions basicOptions = new BasicOptions();
        basicOptions.displayOptions();
        basicOptions.enterNumber();
        try {
            basicOptions.enterInput();
        } catch (InputMismatchException exc) {
            System.out.println("Invalid value. You can only enter integer number. Enter 1 or 2:");
            basicOptions.enterInput();
        }
    }
}
