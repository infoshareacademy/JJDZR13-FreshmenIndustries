package pl.isa.freshmenindustries.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

@Service
@Slf4j
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Response getAllActiveGames() {
        log.info("Getting all games list");
        try {
            return new Response(Boolean.TRUE, gameRepository.getAllActiveGames());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public Response createGame(Game game) {
        if (!gameRepository.getGamesByName(game.getName()).isEmpty()) {
            return new Response("Game with provided name already exists", Boolean.FALSE);
        }
        log.info("Create game with name : " + game.getName());
        gameRepository.save(game);
        return new Response("Game created successfully", Boolean.TRUE);
    }

    public Response updateGame(Game game) {
        log.info("Update game with id : " + game.getId());
        gameRepository.save(game);
        return new Response("Game updated successfully", Boolean.TRUE);
    }

    public Game getGameById(Long id) {
        log.info("Get game with id : " + id);
        return gameRepository.getGameById(id);
    }

    public Response deleteGame(Long id) {
        log.info("Delete game with id : " + id);
        try {
            Game game = gameRepository.getGameById(id);
            game.setIsDeleted(true);
            gameRepository.save(game);
            return new Response("Game deleted", Boolean.TRUE);
        } catch (Exception e) {
            return new Response(e.getMessage(), Boolean.FALSE);
        }
    }

}
