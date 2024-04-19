package pl.isa.freshmenindustries.userGame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
    public List<UserGame> getUserGameByPlayGameId(UUID playGameId) {
        return getAllUserGame().stream()
                .filter(n -> n.getPlayGameId()
                .equals(playGameId))
                .sorted(Comparator.comparingInt(UserGame::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public UserGame getTopScoredRecordForGameByGameId(UUID playGameId) {
        return   getUserGameByPlayGameId(playGameId)
                .stream()
                .sorted(Comparator.comparingInt(UserGame::getScore).reversed())
                .limit(1)
                .findFirst()
                .orElse(null);
    }

    private void writeEntitiesToFile(List entities, File file) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
