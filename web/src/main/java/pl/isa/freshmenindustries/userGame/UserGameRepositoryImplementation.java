package pl.isa.freshmenindustries.userGame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.isa.freshmenindustries.game.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Repository
@Slf4j
public class UserGameRepositoryImplementation implements UserGameRepository {
    ObjectMapper objectMapper = new ObjectMapper();
    List<UserGame> allUserGames = new ArrayList<>();
    File userGameFilePath = new File("web/src/main/resources/source/user-game.json");

    @Override
    public List<UserGame> getAllUserGame() {
        if (userGameFilePath.exists()) {
            try {
                allUserGames = objectMapper.readValue(userGameFilePath, new TypeReference<List<UserGame>>() {
                });
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return allUserGames;
    }

    @Override
    public void createUserGame(UserGame userGame) {
        List<UserGame> userGames = getAllUserGame();
        userGames.add(userGame);
        writeEntitiesToFile(userGames, userGameFilePath);
    }

    @Override
    public List<UserGame> getUserGameByGameId(UUID playGameId) {
        return getAllUserGame().stream().filter(n -> n.getPlayGameId().equals(playGameId)).collect(Collectors.toList());
    }

    private void writeEntitiesToFile(List entities, File file) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
