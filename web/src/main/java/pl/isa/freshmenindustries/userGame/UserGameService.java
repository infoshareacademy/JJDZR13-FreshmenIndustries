package pl.isa.freshmenindustries.userGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserGameService {

    private final UserGameRepository userGameRepository;

    public UserGameService(UserGameRepository userGameRepository) {
        this.userGameRepository = userGameRepository;
    }

    public Response createUserGame(UserGame userGame) {
        log.info("Create user game record");
        if (!Objects.isNull(userGameRepository.findFirstByPlayGameIdAndUserId(userGame.getPlayGame().getId(), userGame.getUser().getId()))) {
            return new Response("User already added to score list", Boolean.FALSE);
        }
        try {
            userGameRepository.save(userGame);
            return new Response("User score added successfully", Boolean.TRUE);
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public List<UserGame> getUserGameByPlayGameId(Long playGameId) {
        return userGameRepository.getUserGameByPlayGameId(playGameId);
    }

    public List<UserGameProjection> getUserGameDTOByPlayGameId(Long playGameId) {
        return userGameRepository.getUserGameDTOByPlayGameId(playGameId);
    }
}
