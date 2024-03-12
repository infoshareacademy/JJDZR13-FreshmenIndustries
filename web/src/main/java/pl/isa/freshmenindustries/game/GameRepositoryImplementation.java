package pl.isa.freshmenindustries.game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GameRepositoryImplementation implements GameRepository {
    ObjectMapper objectMapper = new ObjectMapper();
    File gamesFilePath = new File("C:\\Users\\mwabnik\\JJDZR13-FreshmenIndustries\\web\\src\\main\\resources\\source\\games.json");
    public List<Game> getAllGames() {
        if (gamesFilePath.exists()) {
            try {
                return objectMapper.readValue(gamesFilePath, new TypeReference<List<Game>>() {
                });
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return new ArrayList<Game>();
    }
    public void createGame(Game game) {
            List<Game> games = getAllGames();
            game.setId(UUID.randomUUID());
            games.add(game);
            writeEntitiesToFile(games, gamesFilePath);
    }
    public void updateGame(Game game) {
        List<Game> games = getAllGames();
        game.setName(game.getName());
        game.setDescription(game.getDescription());
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
    public Boolean deleteGame(UUID id) {
        List<Game> games = getAllGames();
        Game game = getGameById(id);
        games.remove(game);
        writeEntitiesToFile(games, gamesFilePath);
        return Boolean.TRUE;
    }
    private void writeEntitiesToFile(List entities, File file) {
        try{
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
