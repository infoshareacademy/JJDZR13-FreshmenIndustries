package pl.isa.freshmenindustries.userGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserGameDTO {
    private Long playGameId;
    private Long userId;
    private int score;
}
