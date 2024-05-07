package pl.isa.freshmenindustries.userGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, Long> {
    UserGame save(UserGame userGame);

    List<UserGame> findAll();

    List<UserGame> getUserGameByPlayGameId(Long playGameId);

    @Query(value = "SELECT\n" +
                   "    CONCAT(u.name, ' ', u.surname) AS userName\n" +
                   "    ,ug.score\n" +
                   "FROM\n" +
                   "    user_game ug\n" +
                   "    JOIN users u ON ug.user_id = u.id\n" +
                   "WHERE\n" +
                   "    ug.play_game_id = :playGameId\n" +
                   "ORDER BY ug.score DESC", nativeQuery = true)
    List<UserGameProjection> getUserGameProjectionByPlayGameId(@Param("playGameId") Long playGameId);

    @Query(value = "SELECT * FROM user_game ug WHERE ug.play_game_id = :playGameId AND ug.user_id = :userId LIMIT 1", nativeQuery = true)
    UserGame findFirstByPlayGameIdAndUserId(@Param("playGameId") Long id, @Param("userId") Long userId);

    @Query(value = "SELECT CONCAT( u.name, ' ', u.surname) AS userName, ug.score FROM user_game ug JOIN users u ON ug.user_id = u.id " +
                   "WHERE ug.play_game_id = :playGameId ORDER BY ug.score DESC", nativeQuery = true)
    List<UserGameProjection> getUserGameDTOByPlayGameId(@Param("playGameId") Long playGameId);

}
