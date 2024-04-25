package pl.isa.freshmenindustries.playGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayGame {
    private UUID id;
    private UUID gameId;
    private String startDate;
    private String endDate;
    private boolean isFinished;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayGame playGame = (PlayGame) o;
        return isFinished == playGame.isFinished && Objects.equals(id, playGame.id)
               && Objects.equals(gameId, playGame.gameId)
               && Objects.equals(startDate, playGame.startDate)
               && Objects.equals(endDate, playGame.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, startDate, endDate, isFinished);
    }
}
