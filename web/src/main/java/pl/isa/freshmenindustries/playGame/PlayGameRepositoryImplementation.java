package pl.isa.freshmenindustries.playGame;

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

@Repository
@Slf4j
public class PlayGameRepositoryImplementation implements PlayGameRepository {

    ObjectMapper objectMapper = new ObjectMapper();
    List<PlayGame> allPlayGames = new ArrayList<>();
    File playGamesFilePath = new File("web/src/main/resources/source/playgame.json");

    @Override
    public void createPlayGame(UUID gameID) {
        List<PlayGame> playGameList = getAllPlayGame();
        PlayGame playGame = new PlayGame(UUID.randomUUID(),gameID, "2024-01-01");
        playGameList.add(playGame);
        writeEntitiesToFile(playGameList, playGamesFilePath);
    }

    @Override
    public List<PlayGame> getAllPlayGame() {
        if (playGamesFilePath.exists()) {
            try {
                allPlayGames = objectMapper.readValue(playGamesFilePath, new TypeReference<List<PlayGame>>() {
                });
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return allPlayGames;
    }

        private void writeEntitiesToFile (List entities, File file){
            try {
                objectMapper.writeValue(file, entities);
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
    }
