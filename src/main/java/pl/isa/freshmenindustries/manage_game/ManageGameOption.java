package pl.isa.freshmenindustries.manage_game;

public class ManageGameOption {
    private String name;
    private int order;
    private boolean isReturn;

    public ManageGameOption(String name, int order, Boolean isReturn) {
        this.name = name;
        this.order = order;
        this.isReturn = isReturn;

    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
