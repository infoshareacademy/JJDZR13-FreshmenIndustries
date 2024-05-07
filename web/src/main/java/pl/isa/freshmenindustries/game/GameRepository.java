package pl.isa.freshmenindustries.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "SELECT * FROM games WHERE is_deleted = 0", nativeQuery = true)
    List<Game> getAllActiveGames();

    List<Game> findAll();

    Game save(Game game);

    Game getGameById(Long id);

    List<Game> getGamesByName(String name);
}
