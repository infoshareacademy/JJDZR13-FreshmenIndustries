package pl.isa.freshmenindustries.playGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.isa.freshmenindustries.userGame.UserGameProjection;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayedGameRankDTO {
    private String gameName;
    private Long playGameId;
    private List<UserGameProjection> usersScore;

}
