package pl.isa.freshmenindustries.playGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayGameRepository extends JpaRepository<PlayGame, Long> {

    List<PlayGame> findAll();

    PlayGame save(PlayGame playGame);

    @Query(value = "SELECT\n" +
                   "    pg.id AS playGameId\n" +
                   "    ,g.name AS gameName\n" +
                   "    ,CONCAT(u.name, ' ' , u.surname) AS topScoreUserName\n" +
                   "    ,ug.score AS topScore\n" +
                   "    ,pg.is_finished AS isCompleted\n" +
                   "FROM\n" +
                   "    play_game pg\n" +
                   "    LEFT JOIN user_game ug\n" +
                   "        ON ug.id = (SELECT id FROM user_game WHERE play_game_id = pg.id ORDER BY score DESC LIMIT 1)\n" +
                   "    LEFT JOIN games g ON pg.game_id = g.id\n" +
                   "    LEFT JOIN users u ON ug.user_id = u.id\n" +
                   "ORDER BY g.id, ug.score DESC", nativeQuery = true)
    List<PlayedGamesProjection> getPlayedGameListDto();

    PlayGame findPlayGameById(Long playGameId);

    @Query(value = "SELECT * FROM play_game", nativeQuery = true)
    List<PlayedGameRankDTO> getPlayGameTopUserRankListDTO();

}
