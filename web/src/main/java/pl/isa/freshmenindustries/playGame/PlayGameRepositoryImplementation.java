package pl.isa.freshmenindustries.playGame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Map;

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
    File playGamesFilePath = new File("web/src/main/resources/source/play-game.json");


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
        return getAllPlayGame()
                .stream()
                .map(n -> {
                    String gameName = gameRepository.getGameById(n.getGameId()).getName();
                    UserGame userGame = userGameRepository.getTopScoredRecordForGameByGameId(n.getId());
                    if (userGame != null) {
                        return new PlayedGamesDTO(
                                gameName,
                                //TODO replace this by user concat: name + surname
                                userGame.getUserId().toString(),
                                userGame.getScore(),
                                n.isFinished(),
                                n.getId());
                    } else {
                        return new PlayedGamesDTO(
                                gameName,
                                "-",
                                0,
                                n.isFinished(),
                                n.getId());
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public List<PlayedGameRankDTO> getPlayGameTopUserRankListDTO() {

        try {
            getAllPlayGame()
                    .stream()
                    .filter(PlayGame::isFinished)
                    .map(n -> {
                        List<UserGame> userGames = userGameRepository.getUserGameByPlayGameId(n.getId());
                        Map<String, Integer> userScores = userGames.stream()
//                            .map(m -> {return Map<String userName, Integer score> (m.getUserId().toString(), m.getScore())})
                                .collect(Collectors.toMap(
                                        //TODO replace this by user object, concat: name + surname
                                        m -> m.getUserId().toString(),
                                        UserGame::getScore
                                ));
                        return new PlayedGameRankDTO(
//                            gameRepository.getGameById(n.getId()).getName(),
                                "",
                                n.getId(),
                                userScores
                        );
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return getAllPlayGame()
                .stream()
                .filter(PlayGame::isFinished)
                .map(n -> {
                    List<UserGame> userGames = userGameRepository.getUserGameByPlayGameId(n.getId());
                    Map<String, Integer> userScores = userGames.stream()
//                            .map(m -> {return Map<String userName, Integer score> (m.getUserId().toString(), m.getScore())})
                            .collect(Collectors.toMap(
                                    //TODO replace this by user object, concat: name + surname
                                    m -> m.getUserId().toString(),
                                    UserGame::getScore
                            ));
                    return new PlayedGameRankDTO(
                            gameRepository.getGameById(n.getGameId()).getName(),
                            n.getId(),
                            userScores
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public void endPlayGame(UUID id) {
        List<PlayGame> playGameList = getAllPlayGame();
        PlayGame playGame = getPlayGameById(id);
        playGameList.remove(playGame);
        playGame.setEndDate(LocalDate.now().toString());
        playGame.setFinished(true);
        playGameList.add(playGame);
        writeEntitiesToFile(playGameList,playGamesFilePath);
    }

    @Override
    public PlayGame getPlayGameById(UUID playGameId) {
        List<PlayGame> playGames = getAllPlayGame();
        return playGames.stream().filter(n -> n.getId().equals(playGameId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + playGameId));
    }

    private void writeEntitiesToFile(List entities, File file) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
