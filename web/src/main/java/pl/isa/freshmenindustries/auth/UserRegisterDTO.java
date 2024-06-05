package pl.isa.freshmenindustries.auth;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRegisterDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}
