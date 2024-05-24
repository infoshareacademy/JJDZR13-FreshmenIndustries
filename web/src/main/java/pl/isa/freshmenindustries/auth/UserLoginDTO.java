package pl.isa.freshmenindustries.auth;

import lombok.Data;

@Data
public class UserLoginDTO {

    private String email;
    private String password;

}
