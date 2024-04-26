package pl.isa.freshmenindustries.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.isa.freshmenindustries.response.Response;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response getAllUsers() {
        log.info("Getting all users");
        try {
            return new Response(Boolean.TRUE, userRepository.getAllUsers());
        } catch (Exception e) {
            return new Response("General error occurred", Boolean.FALSE);
        }
    }

    public Response createUser(User user) {
        userRepository.createUser(user);
        return new Response("User created", Boolean.TRUE);
    }

}
