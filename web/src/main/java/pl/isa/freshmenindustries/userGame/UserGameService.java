package pl.isa.freshmenindustries.userGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserGameService {

    private final UserGameRepository userGameRepository;

    public UserGameService(UserGameRepository userGameRepository) {
        this.userGameRepository = userGameRepository;
    }

    public Response createUserGame(UserGame userGame) {
        log.info("Create user game record");
        try {
            userGameRepository.createUserGame(userGame);
            return new Response("User score added successfully", Boolean.TRUE);
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }
    public List<UserGame> getUserGameByPlayGameId(UUID playGameId) {
        return userGameRepository.getUserGameByPlayGameId(playGameId);
    }
}
