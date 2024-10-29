package com.hit.model.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Introspected
@Serdeable
public record HabitDTO(
    @NonNull @NotBlank String name,
    @NonNull @NotBlank String description
) {

}
