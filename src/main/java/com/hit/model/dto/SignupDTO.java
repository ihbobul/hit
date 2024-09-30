package com.hit.model.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Introspected
@Serdeable
public record SignupDTO(
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String email
) {}
