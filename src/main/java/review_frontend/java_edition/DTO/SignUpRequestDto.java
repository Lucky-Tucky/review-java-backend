package review_frontend.java_edition.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(
        @NotNull
        @NotBlank(message = "User Name Required")
        String username ,

        @NotNull
        @NotBlank(message = "Email Required")
        @Size(min=5,message = "Email is not valid")
        String email,

        @NotNull
        @NotBlank(message = "Password Required")
        String password) {
}
