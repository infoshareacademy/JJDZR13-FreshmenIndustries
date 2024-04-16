package pl.isa.freshmenindustries.playGame;

import java.util.List;
import java.util.UUID;

public interface PlayGameRepository {

    List<PlayGame> getAllPlayGame();
    void createPlayGame(UUID gameID);
}