package pl.isa.freshmenindustries.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.isa.freshmenindustries.auth.UserAuthenticationAttributesDTO;
import pl.isa.freshmenindustries.user.*;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public GlobalControllerAdvice(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("usersAttributes")
    public UserAuthenticationAttributesDTO getUserAttributes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByEmail(authentication.getName());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return UserAuthenticationAttributesDTO.builder()
                    .userName(user.getName() + ' ' + user.getSurname())
                    .email(authentication.getName())
                    .userId(user.getId())
                    .roles(roleRepository.findAllByUser(user)
                            .stream().map(Role::getRole).toArray(String[]::new))
                    .isLoggedIn(true)
                    .build();
        } else {
            return UserAuthenticationAttributesDTO.builder()
                    .isLoggedIn(false)
                    .roles(new String[]{})
                    .build();
        }
    }
}
