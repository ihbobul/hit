package com.hit.repository;

import com.hit.model.entity.RefreshTokenEntity;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, Long> {

  @Transactional
  RefreshTokenEntity save(
      @NonNull @NotBlank String username,
      @NonNull @NotBlank String refreshToken,
      @NonNull @NotNull Boolean revoked,
      @NonNull @NotNull Instant dateCreated
  );

  Optional<RefreshTokenEntity> findByRefreshToken(@NonNull @NotBlank String refreshToken);

  long updateByUsername(@NonNull @NotBlank String username,
      boolean revoked);
}