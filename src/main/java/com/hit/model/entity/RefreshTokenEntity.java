package com.hit.model.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.DateCreated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class RefreshTokenEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_token")
  @SequenceGenerator(name = "seq_token", sequenceName = "SEQ_TOKEN", allocationSize = 1)
  @NonNull
  private Long id;

  @NonNull
  @NotBlank
  private String username;

  @NonNull
  @NotBlank
  private String refreshToken;

  @NonNull
  @NotNull
  private Boolean revoked;

  @DateCreated
  @NonNull
  @NotNull
  private Instant dateCreated;

  public RefreshTokenEntity() {
  }

  public RefreshTokenEntity(String username, String refreshToken, Boolean revoked,
      Instant dateCreated) {
    this.username = username;
    this.refreshToken = refreshToken;
    this.revoked = revoked;
    this.dateCreated = dateCreated;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Boolean getRevoked() {
    return revoked;
  }

  public void setRevoked(Boolean revoked) {
    this.revoked = revoked;
  }

  public Instant getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Instant dateCreated) {
    this.dateCreated = dateCreated;
  }
}