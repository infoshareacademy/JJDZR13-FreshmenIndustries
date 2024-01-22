package pl.isa.freshmenindustries;

public class BasicOptions {
    public void displayOptions(){
        System.out.println("Options:");
        System.out.println("1. Start new game");
        System.out.println("2. Manage games");
    }
    public String enterNumber(){
        return "Enter the number of the chosen option: ";
    }
    public int chooseOption(int number){
        if(number == 1){
            System.out.println("You chose option number: ");
        }
        if(number == 2){
            System.out.println("You chose option number: ");
        }
        return number;
    }
}
