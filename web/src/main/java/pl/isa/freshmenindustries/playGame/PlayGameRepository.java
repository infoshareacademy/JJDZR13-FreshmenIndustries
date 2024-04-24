package pl.isa.freshmenindustries.playGame;

import java.util.List;
import java.util.UUID;

public interface PlayGameRepository {

    List<PlayGame> getAllPlayGame();

    PlayGame createPlayGame(UUID gameID);

    List<PlayedGamesDTO> getPlayedGameListDto();

    PlayGame getPlayGameById(UUID playGameId);

    List<PlayedGameRankDTO> getPlayGameTopUserRankListDTO();


}
