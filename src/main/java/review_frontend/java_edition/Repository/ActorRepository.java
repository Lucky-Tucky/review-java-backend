package review_frontend.java_edition.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import review_frontend.java_edition.Model.Actor;

import java.util.UUID;

public interface ActorRepository extends JpaRepository<Actor,UUID> {
}
