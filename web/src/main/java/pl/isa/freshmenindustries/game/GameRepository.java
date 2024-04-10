package pl.isa.freshmenindustries.game;

import java.util.List;
import java.util.UUID;


public interface GameRepository {
    List<Game> getAllActiveGames();

    List<Game> getAllGames();

    void createGame(Game game);

    void updateGame(Game game);

    Game getGameById(UUID id);

    void deleteGame(UUID id);

    List<Game> getGamesByName(String name);
}
