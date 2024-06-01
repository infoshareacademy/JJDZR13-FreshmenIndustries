package pl.isa.freshmenindustries.user;

import jakarta.persistence.*;
import lombok.*;
import pl.isa.freshmenindustries.userGame.UserGame;

import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_enable")
    private boolean isEnabled;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserGame> userGameSet;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Role> role;
}
