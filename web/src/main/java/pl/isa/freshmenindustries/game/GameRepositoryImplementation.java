package pl.isa.freshmenindustries.game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GameRepositoryImplementation implements GameRepository {
    ObjectMapper objectMapper = new ObjectMapper();
    List<Game> allGames = new ArrayList<>();
    File gamesFilePath = new File("web/src/main/resources/source/games.json");

    public List<Game> getAllGames() {
        if (gamesFilePath.exists()) {
            try {
                allGames = objectMapper.readValue(gamesFilePath, new TypeReference<List<Game>>() {
                });
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return allGames.stream().filter(game -> !game.getIsDeleted()).collect(Collectors.toList());
    }

    public void createGame(Game game) {
        List<Game> games = getAllGames();
        game.setId(UUID.randomUUID());
        game.setIsDeleted(Boolean.FALSE);
        games.add(game);
        writeEntitiesToFile(games, gamesFilePath);
    }

    public void updateGame(Game game) {
        List<Game> games = getAllGames();
//        game.setName(game.getName());
//        game.setDescription(game.getDescription());
        games.remove(game);
        game.setIsDeleted(Boolean.FALSE);
        games.add(game);
        writeEntitiesToFile(games, gamesFilePath);
    }

    public List<Game> getGamesByName(String name) {
        List<Game> games = getAllGames();
        return games.stream().filter(n -> n.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public Game getGameById(UUID id) {
        List<Game> games = getAllGames();
        return games.stream().filter(n -> n.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + id));
    }

    public void deleteGame(UUID id) {
        List<Game> games = getAllGames();
        Game game = getGameById(id);
        games.remove(game);
        game.setIsDeleted(Boolean.TRUE);
        games.add(game);
        writeEntitiesToFile(games, gamesFilePath);
    }

    private void writeEntitiesToFile(List entities, File file) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
