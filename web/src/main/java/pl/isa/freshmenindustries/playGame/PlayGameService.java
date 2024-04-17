package pl.isa.freshmenindustries.playGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.response.Response;

import java.util.UUID;

@Service
@Slf4j
public class PlayGameService {
    private final PlayGameRepository playGameRepository;

    public PlayGameService(PlayGameRepository playGameRepository) {
        this.playGameRepository = playGameRepository;
    }

    public Response getAllPlayGames() {
        log.info("Getting all play games list");
        try {
            return new Response(Boolean.TRUE, playGameRepository.getAllPlayGame());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }
        public Response startGame(UUID gameID) {
            playGameRepository.createPlayGame(gameID);
            return new Response("Game started", Boolean.TRUE);
    }
    public Response getPlayedGameListDto() {
        log.info("Getting all play games list with top score user");
        try {
            return new Response(Boolean.TRUE, playGameRepository.getPlayedGameListDto());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }
}
