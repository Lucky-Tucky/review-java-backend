package review_frontend.java_edition.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull
        @NotBlank
        String email ,

        @NotNull
        @NotBlank
        String password) {
}
