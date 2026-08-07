package review_frontend.java_edition.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import review_frontend.java_edition.Model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
