package pl.isa.freshmenindustries.playGame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.isa.freshmenindustries.game.GameRepository;
import pl.isa.freshmenindustries.userGame.UserGame;
import pl.isa.freshmenindustries.userGame.UserGameRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PlayGameRepositoryImplementation implements PlayGameRepository {

    private final GameRepository gameRepository;
    private final UserGameRepository userGameRepository;

    public PlayGameRepositoryImplementation(GameRepository gameRepository, UserGameRepository userGameRepository) {
        this.gameRepository = gameRepository;
        this.userGameRepository = userGameRepository;
    }

    ObjectMapper objectMapper = new ObjectMapper();
    List<PlayGame> allPlayGames = new ArrayList<>();
    File playGamesFilePath = new File("web/src/main/resources/source/playgame.json");


    @Override
    public PlayGame createPlayGame(UUID gameID) {
        List<PlayGame> playGameList = getAllPlayGame();
        PlayGame playGame = new PlayGame(UUID.randomUUID(), gameID, LocalDate.now().toString(), "", false);
        playGameList.add(playGame);
        writeEntitiesToFile(playGameList, playGamesFilePath);
        return playGame;
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

    @Override
    public List<PlayedGamesDTO> getPlayedGameListDto() {
        try {
            getAllPlayGame()
                    .stream()
                    .map(n -> new PlayedGamesDTO(
                            gameRepository.getGameById(n.getGameId()).getName(),
                            userGameRepository.getTopScoredRecordForGameByGameId(n.getId()).getUserId().toString(),
                            userGameRepository.getTopScoredRecordForGameByGameId(n.getId()).getScore(),
                            true))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return getAllPlayGame()
                .stream()
                .map(n -> {
                    String gameName = gameRepository.getGameById(n.getGameId()).getName();
                    //TODO add some checkers and error handling for empty UserGame return
                    UserGame userGame = userGameRepository.getTopScoredRecordForGameByGameId(n.getId());
                    return new PlayedGamesDTO(
                            gameName,
                            userGame.getUserId().toString(),
                            userGame.getScore(),
                        true);})
                .collect(Collectors.toList());
    }

    private void writeEntitiesToFile(List entities, File file) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
