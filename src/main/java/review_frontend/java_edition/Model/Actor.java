package review_frontend.java_edition.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import review_frontend.java_edition.Enum.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

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

    @Column(nullable = false)
    private String image_id;

    @Column(nullable = false)
    private String image_url;

    @Builder
    public Actor(String name, String about, Gender gender, String modifiedBy, String image_id, String image_url) {
        this.name = name;
        this.about = about;
        this.gender = gender;
        this.modifiedBy = modifiedBy;
        this.image_id = image_id;
        this.image_url = image_url;
    }
}
