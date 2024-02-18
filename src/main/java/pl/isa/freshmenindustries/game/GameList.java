package pl.isa.freshmenindustries.game;

import java.util.ArrayList;
import java.util.List;

public class GameList {

    public static List<Game> games() {
        List<Game> games = new ArrayList<>();
        games.add(new Game("Rising Sun", "Kami, zapomniane, lecz potężne istoty, powracają z zaświatów, rozczarowane biegiem spraw w Imperium pod rządami obecnego szoguna."));
        games.add(new Game("Rubik", "Magiczna kostka, stworzona ponad ćwierć wieku temu przez węgierskiego profesora Ernö Rubika do dziś stawiana jest jako przykład nieśmiertelnej łamigłówki doskonałej dla wielu pokoleń."));
        games.add(new Game("Rush Hour", "Łamigłówka składa się z planszy, przedstawiającej parking z wyjazdem oraz pojazdów, które mogą poruszać się do przodu i do tyłu."));
        return games;
    }

}
