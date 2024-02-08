package pl.isa.freshmenindustries.game;

public class Game {
    private String name;
    private String description;

    public Game(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        this.description = description;
        return description;
    }

}

