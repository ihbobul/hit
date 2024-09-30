package com.hit.repository;

import static io.micronaut.data.model.query.builder.sql.Dialect.POSTGRES;

import com.hit.model.entity.UserEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = POSTGRES)
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
