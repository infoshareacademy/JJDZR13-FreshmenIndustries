package pl.isa.freshmenindustries.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface UserRepository {
    void createUser (User user);
    User getUserById(UUID id);
    void editUser(User user);
    List<User> getAllUsers();
}
