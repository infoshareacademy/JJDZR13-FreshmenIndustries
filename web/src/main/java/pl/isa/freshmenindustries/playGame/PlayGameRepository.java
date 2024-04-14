package pl.isa.freshmenindustries.playGame;

import java.util.List;

public interface PlayGameRepository {

    List<PlayGame> getAllPlayGame();
    void createPlayGame(PlayGame playGame);
}
