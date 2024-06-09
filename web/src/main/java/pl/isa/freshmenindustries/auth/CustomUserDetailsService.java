package pl.isa.freshmenindustries.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.user.Role;
import pl.isa.freshmenindustries.user.RoleRepository;
import pl.isa.freshmenindustries.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pl.isa.freshmenindustries.user.User userByEmail = userRepository.getUserByEmail(username);
        if (userByEmail == null) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
            return User.builder()
                    .username(userByEmail.getEmail())
                    .password(userByEmail.getPassword())
                    .roles(roleRepository.findAllByUser(userByEmail).stream().map(Role::getRole).toArray(String[]::new))
                    .disabled(!userByEmail.isEnabled())
                    .build();
    }
}
