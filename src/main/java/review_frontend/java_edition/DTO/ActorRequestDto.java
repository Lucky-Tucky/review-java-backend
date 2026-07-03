package review_frontend.java_edition.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import review_frontend.java_edition.Enum.Gender;

public record ActorRequestDto(
        @NotBlank(message = "Name is Required")
        @Size(min = 2, max = 20, message = "Name Should be between 2 and 20")
        String name,

        @Size(max = 1000, message = "About cannot exceed 1000 characters")
        String about ,

        @NotNull(message = "Gender is Required")
        Gender gender) {
}
