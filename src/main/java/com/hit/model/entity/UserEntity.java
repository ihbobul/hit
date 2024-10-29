package com.hit.model.entity;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
  @SequenceGenerator(name = "seq_user", sequenceName = "SEQ_USER", allocationSize = 1)
  @NonNull
  private Long id;

  @NonNull
  @NotBlank
  private String username;

  @NonNull
  @NotBlank
  private String password;

  @NonNull
  @NotBlank
  @Email
  private String email;

  public UserEntity() {
  }

  public UserEntity(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
