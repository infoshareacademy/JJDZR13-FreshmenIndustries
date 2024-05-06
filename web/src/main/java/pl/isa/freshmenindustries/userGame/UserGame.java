package pl.isa.freshmenindustries.userGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGame {
    private UUID playGameId;
    private Long userId;
    private int score;

}
