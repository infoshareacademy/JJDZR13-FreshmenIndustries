package pl.isa.freshmenindustries.userGame;

import pl.isa.freshmenindustries.playGame.PlayGame;

import java.util.List;
import java.util.UUID;

public interface UserGameRepository {
    void createUserGame(UserGame userGame);

    List<UserGame> getAllUserGame();

    List<UserGame> getUserGameByGameId(UUID playGameId);

    UserGame getTopScoredRecordForGameByGameId(UUID playGameId);
}
