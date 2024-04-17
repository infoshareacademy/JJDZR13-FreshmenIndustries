package pl.isa.freshmenindustries.playGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayedGamesDTO {
    private String gameName;
    private String topScoreUserName;
    private int topScore;
    private boolean isCompleted;
}
