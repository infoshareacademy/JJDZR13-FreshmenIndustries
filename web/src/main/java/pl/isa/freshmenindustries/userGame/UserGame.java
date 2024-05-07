package pl.isa.freshmenindustries.userGame;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.isa.freshmenindustries.playGame.PlayGame;
import pl.isa.freshmenindustries.user.User;

@Entity(name = "user_game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "play_game_id")
    private PlayGame playGame;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int score;

    public UserGame(PlayGame playGame, User user, int score) {
        this.playGame = playGame;
        this.user = user;
        this.score = score;
    }
}
