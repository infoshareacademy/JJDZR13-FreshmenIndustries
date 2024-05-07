package pl.isa.freshmenindustries.playGame;

public interface PlayedGamesProjection {
    String getGameName();
    String getTopScoreUserName();
    Integer getTopScore();
    boolean getIsCompleted();
    Long getPlayGameId();
}
