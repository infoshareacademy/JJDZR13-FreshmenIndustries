package pl.isa.freshmenindustries.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.isa.freshmenindustries.user.Role;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserAuthenticationAttributesDTO {
    private String userName;
    private String email;
    private boolean isLoggedIn;
    private String[] roles;
}
