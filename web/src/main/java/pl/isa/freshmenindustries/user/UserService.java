package pl.isa.freshmenindustries.user;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.response.Response;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.isa.freshmenindustries.user.Role.RolesEnum.USER;

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
        log.info("Getting user by email: " + email);
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
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
        return new Response("User status changed", Boolean.TRUE);
    }

    public Response assignUserRole(UserRolesDTO userRolesDTO) {
        if (userRolesDTO.getUserId() == null) {
            return new Response("General error: missing user id.", Boolean.FALSE);
        }
        if (userRolesDTO.getRoles() == null) {
            return new Response("User has to have at least one role.", Boolean.FALSE);
        }
        User user = userRepository.getUserById(userRolesDTO.getUserId());
        Set<Role> roles = Arrays.stream(userRolesDTO.getRoles())
                .map(role -> new Role(user, Role.RolesEnum.valueOf(role).name()))
                .collect(Collectors.toSet());
        roleRepository.deleteAll(user.getRoles());
        roleRepository.saveAll(roles);
        return new Response("Roles assigned successfully", Boolean.TRUE);
    }

    public UserRolesDTO getUserRoles(Long userId) {
        User user = userRepository.getUserById(userId);
        return new UserRolesDTO(userId, roleRepository.findAllByUser(user)
                .stream()
                .map(Role::getRole)
                .toArray(String[]::new));
    }
}
