package pl.isa.freshmenindustries.user;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

import java.util.HashSet;
import java.util.Set;

import static pl.isa.freshmenindustries.user.Role.RolesEnum.*;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Response getAllUsers() {
        log.info("Getting all users");
        try {
            return new Response(Boolean.TRUE, userRepository.findAll());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }
    public Boolean isUserByEmailExist(String email) {
        log.info("Get user by email: " + email);
        return userRepository.getUserByEmail(email) != null;
    }

    @Transactional
    public Response createUser(User user) {
        User newUser = userRepository.save(user);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(newUser, USER.name()));
        roleRepository.saveAll(roles);
        return new Response("User created", Boolean.TRUE);
    }

    public Response activateUser(String email) {
        User user = userRepository.getUserByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
        return new Response("User is activated", Boolean.TRUE);
    }

}
