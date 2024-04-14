package pl.isa.freshmenindustries.playGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

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
}
