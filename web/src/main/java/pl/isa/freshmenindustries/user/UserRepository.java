package pl.isa.freshmenindustries.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User getUserById(Long id);

    List<User> findAll();
}
