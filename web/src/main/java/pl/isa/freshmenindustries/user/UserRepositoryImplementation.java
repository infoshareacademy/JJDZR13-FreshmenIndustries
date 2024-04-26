package pl.isa.freshmenindustries.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.isa.freshmenindustries.game.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class UserRepositoryImplementation implements UserRepository {

    ObjectMapper objectMapper = new ObjectMapper();
    List<User> allUsers = new ArrayList<>();
    File usersFilePath = new File("web/src/main/resources/source/users.json");

    @Override
    public void createUser(User user) {
        List<User> users = getAllUsers();
        user.setId(UUID.randomUUID());
        user.setName(user.getName());
        user.setSurname(user.getSurname());
        users.add(user);
        writeEntitiesToFile(users, usersFilePath);
    }

    @Override
    public User getUserById(UUID id) {
        List<User> users =getAllUsers();
        return users.stream().filter(n -> n.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    @Override
    public void editUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        if (usersFilePath.exists()) {
            try {
                allUsers = objectMapper.readValue(usersFilePath, new TypeReference<List<User>>() {
                });
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
        return allUsers;
    }

    private void writeEntitiesToFile(List entities, File file) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

}
