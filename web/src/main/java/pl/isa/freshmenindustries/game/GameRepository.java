package pl.isa.freshmenindustries.game;

import org.springframework.stereotype.Repository;
import pl.isa.freshmenindustries.game.Game;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GameRepository {
    List<Game> getAllGames();
    void createGame(Game game);
    void updateGame(Game game);
    Game getGameById(UUID id);

    Boolean deleteGame(UUID id);
    List<Game> getGamesByName(String name);
}
