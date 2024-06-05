package pl.isa.freshmenindustries.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.isa.freshmenindustries.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pl.isa.freshmenindustries.user.User userByEmail = userRepository.getUserByEmail(username);
        if (userByEmail == null) {
            return (UserDetails) new UsernameNotFoundException("User with email " + username + " not found");
        }
        return User.builder()
                .username(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .roles("ADMIN", "USER")
                .disabled(!userByEmail.isEnabled())
                .build();
    }
}
