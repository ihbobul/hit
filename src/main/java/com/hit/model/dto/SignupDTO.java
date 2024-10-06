package com.hit.model.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Introspected
@Serdeable
public record SignupDTO(
    @NotBlank String username,

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$",
        message = "Password must contain at least one digit, one uppercase letter, and one lowercase letter."
    )
    String password,

    @NotBlank
    @Email(message = "Email should be valid.")
    String email
) {

}
