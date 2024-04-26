package pl.isa.freshmenindustries.playGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

import java.util.UUID;

@Service
@Slf4j
public class PlayGameService {
    private final PlayGameRepository playGameRepository;

    public PlayGameService(PlayGameRepository playGameRepository) {
        this.playGameRepository = playGameRepository;
    }

    public PlayGame startGame(UUID gameID) {
        return playGameRepository.createPlayGame(gameID);
    }

    public Response getPlayedGameListDto() {
        log.info("Getting all play games list with top score user");
        try {
            return new Response(Boolean.TRUE, playGameRepository.getPlayedGameListDto());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public Response getPlayedGameTopRankListDto() {
        log.info("Getting all play games list with top score users rank");
        try {
            return new Response(Boolean.TRUE, playGameRepository.getPlayGameTopUserRankListDTO());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public PlayGame getPlayGameById(UUID id) {
        log.info("Get play game with id : " + id);
        return playGameRepository.getPlayGameById(id);
    }

    public Response endPlayGame(UUID id) {
        log.info("Game finished");
        playGameRepository.endPlayGame(id);
        return new Response("The game has been finished", Boolean.TRUE);
    }
}
