package pl.isa.freshmenindustries.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        log.info("Getting all games list");
        return gameRepository.getAllGames();
    }
    public void createGame(Game game) {
        log.info("Create game with name : " +  game.getName());
        if(game.getName().isEmpty() || game.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Missing name or description");
        }
        if(!gameRepository.getGamesByName(game.getName()).isEmpty()) {
            throw new IllegalArgumentException("Game with provided name already exists.");
        }
        gameRepository.createGame(game);
    }
    public void updateGame(Game game) {
        log.info("Update game with id : " +  game.getId());
        if(game.getName().isEmpty() || game.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Missing name or description");
        }
        gameRepository.deleteGame(game.getId());
        gameRepository.updateGame(game);
    }
    public Game getGameById(UUID id) {
        log.info("Get game with id : " +  id);
        return gameRepository.getGameById(id);
    }
    public Boolean deleteGame(UUID id) {
        log.info("Delete game with id : " +  id);
        return gameRepository.deleteGame(id);
    }
}
