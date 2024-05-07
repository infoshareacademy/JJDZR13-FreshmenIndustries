package pl.isa.freshmenindustries.playGame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.game.GameRepository;
import pl.isa.freshmenindustries.response.Response;
import pl.isa.freshmenindustries.userGame.UserGameRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayGameService {
    private final PlayGameRepository playGameRepository;
    private final GameRepository gameRepository;
    private final UserGameRepository userGameRepository;

    public PlayGameService(PlayGameRepository playGameRepository, GameRepository gameRepository, UserGameRepository userGameRepository) {
        this.playGameRepository = playGameRepository;
        this.gameRepository = gameRepository;
        this.userGameRepository = userGameRepository;
    }

    public PlayGame startGame(Long gameID) {
        log.info("Start game play");
        PlayGame playGame = new PlayGame(gameRepository.getGameById(gameID), LocalDate.now(), false);
        return playGameRepository.save(playGame);
    }

    public Response getPlayedGameListDto() {
        log.info("Getting all play games list with top score user");
        try {
            return new Response(Boolean.TRUE, playGameRepository.getPlayedGameListDto());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public Response getPlayedGameTopRankListDto() {
        log.info("Getting all play games list with top score users rank");
        try {
            List<PlayedGameRankDTO> playedGameRankDTO = playGameRepository.findAll()
                    .stream()
                    .filter(PlayGame::isFinished)
                    .map(n -> {
                        return new PlayedGameRankDTO(
                                gameRepository.getGameById(n.getGame().getId()).getName(),
                                n.getId(),
                                userGameRepository.getUserGameProjectionByPlayGameId(n.getId())
                                        .stream().limit(3).collect(Collectors.toList())
                        );
                    })
                    .collect(Collectors.toList());
            return new Response(Boolean.TRUE, playedGameRankDTO);
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public PlayGame getPlayGameById(Long id) {
        log.info("Get play game with id : " + id);
        return playGameRepository.findPlayGameById(id);
    }

    public Response endPlayGame(Long id) {
        log.info("Game finished");
        PlayGame playGame = playGameRepository.findPlayGameById(id);
        playGame.setEndDate(LocalDate.now());
        playGame.setFinished(true);
        playGameRepository.save(playGame);
        return new Response("The game has been finished", Boolean.TRUE);
    }
}
