package pl.isa.freshmenindustries.playGame;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.isa.freshmenindustries.game.GameRepository;
import pl.isa.freshmenindustries.user.User;
import pl.isa.freshmenindustries.user.UserRepository;
import pl.isa.freshmenindustries.userGame.UserGame;
import pl.isa.freshmenindustries.userGame.UserGameDTO;
import pl.isa.freshmenindustries.userGame.UserGameRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class PlayGameRepositoryImplementation implements PlayGameRepository {

    private final GameRepository gameRepository;
    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;

    public PlayGameRepositoryImplementation(GameRepository gameRepository, UserGameRepository userGameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userGameRepository = userGameRepository;
        this.userRepository = userRepository;
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
                        User user = userRepository.getUserById(userGame.getUserId());
                        return new PlayedGamesDTO(
                                gameName,
                                user.getName() + " " + user.getSurname(),
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
        return getAllPlayGame()
                .stream()
                .filter(PlayGame::isFinished)
                .map(n -> {
                    List<UserGameDTO> userGamesDTO = userGameRepository.getUserGameDTOByPlayGameId(n.getId());
                    Map<String, Integer> userScores = userGamesDTO
                            .stream()
                            .sorted(Comparator.comparingInt(UserGameDTO::getScore).reversed())
                            .limit(3)
                            .collect(Collectors.toMap(
                                    UserGameDTO::getUserName,
                                    UserGameDTO::getScore,
                                    (score1, score2) -> score1, // Merge function to resolve conflicts
                                    LinkedHashMap::new
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
        writeEntitiesToFile(playGameList, playGamesFilePath);
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
