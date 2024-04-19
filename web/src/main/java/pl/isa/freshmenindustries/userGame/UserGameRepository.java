package pl.isa.freshmenindustries.userGame;

import java.util.List;
import java.util.UUID;

public interface UserGameRepository {
    void createUserGame(UserGame userGame);

    List<UserGame> getAllUserGame();

    List<UserGame> getUserGameByPlayGameId(UUID playGameId);

    UserGame getTopScoredRecordForGameByGameId(UUID playGameId);
}
