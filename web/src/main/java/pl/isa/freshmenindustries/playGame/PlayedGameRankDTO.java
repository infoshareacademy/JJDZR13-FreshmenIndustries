package pl.isa.freshmenindustries.playGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayedGameRankDTO {
    private String gameName;
    private UUID playGameId;
    private Map<String, Integer> usersScore;

}
