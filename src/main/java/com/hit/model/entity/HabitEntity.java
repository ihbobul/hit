package com.hit.model.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.DateCreated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
public class HabitEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_habit")
  @SequenceGenerator(name = "seq_habit", sequenceName = "SEQ_HABIT", allocationSize = 1)
  private Long id;

  @NonNull
  @NotBlank
  private String name;

  @NonNull
  @NotBlank
  private String description;

  @DateCreated
  private Instant createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }
}