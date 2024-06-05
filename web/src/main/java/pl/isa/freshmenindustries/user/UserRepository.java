package pl.isa.freshmenindustries.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User getUserById(Long id);

    List<User> findAll();

    User getUserByEmail(String email);
}
