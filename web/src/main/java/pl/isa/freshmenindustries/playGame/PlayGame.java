package pl.isa.freshmenindustries.playGame;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.isa.freshmenindustries.game.Game;
import pl.isa.freshmenindustries.userGame.UserGame;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity(name = "play_game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
    @OneToMany(mappedBy = "playGame", fetch = FetchType.LAZY)
    private Set<UserGame> userGames;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isFinished;

    public PlayGame(Game game, LocalDate startDate, boolean isFinished) {
        this.game = game;
        this.startDate = startDate;
        this.isFinished = isFinished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayGame playGame = (PlayGame) o;
        return isFinished == playGame.isFinished
               && Objects.equals(id, playGame.id)
               && Objects.equals(game, playGame.game)
               && Objects.equals(startDate, playGame.startDate)
               && Objects.equals(endDate, playGame.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game, startDate, endDate, isFinished);
    }
}
