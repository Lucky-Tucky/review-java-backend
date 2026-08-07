package review_frontend.java_edition.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import review_frontend.java_edition.Enum.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private boolean isVerified = false;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime joinedOn;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

}
