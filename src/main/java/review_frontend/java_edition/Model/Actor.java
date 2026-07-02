package review_frontend.java_edition.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import review_frontend.java_edition.Enum.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = true)
    private String about;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modifiedOn;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private String modifiedBy;
}
