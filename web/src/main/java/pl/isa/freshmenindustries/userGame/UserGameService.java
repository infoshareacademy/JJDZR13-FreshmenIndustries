package pl.isa.freshmenindustries.userGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

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
            return new Response("Game created successfully", Boolean.TRUE);
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }
}
