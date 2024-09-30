package com.hit.model.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@MappedEntity
public record UserEntity(
    @Id @GeneratedValue Long id,

    @NonNull @NotBlank String username,

    @NonNull @NotBlank String password,

    @NonNull @NotBlank @Email String email
) {

}
