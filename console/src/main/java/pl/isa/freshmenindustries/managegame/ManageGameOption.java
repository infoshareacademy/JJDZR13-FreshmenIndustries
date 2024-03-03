package pl.isa.freshmenindustries.managegame;

public class ManageGameOption {
    private String name;
    private int order;
    private boolean isReturn;

    public ManageGameOption(String name, int order, Boolean isReturn) {
        this.name = name;
        this.order = order;
        this.isReturn = isReturn;

    }

    public boolean isReturn() {
        return isReturn;
    }

    public String toStringShort() {
        return order + ". " + name;
    }
}
