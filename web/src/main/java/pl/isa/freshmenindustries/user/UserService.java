package pl.isa.freshmenindustries.user;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

import java.util.HashSet;
import java.util.Set;

import static pl.isa.freshmenindustries.user.RolesEnum.*;

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

    @Transactional
    public Response createUser(User user) {
        //TODO add user email duplication validator
        User newUser = userRepository.save(user);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(newUser, USER.name()));
        roleRepository.saveAll(roles);
        return new Response("User created", Boolean.TRUE);
    }
}
