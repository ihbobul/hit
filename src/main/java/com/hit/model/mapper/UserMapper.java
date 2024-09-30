package com.hit.model.mapper;

import com.hit.model.dto.SignupDTO;
import com.hit.model.entity.UserEntity;
import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;


@Singleton
public interface UserMapper {

  @Mapper
  UserEntity toEntity(SignupDTO signupDTO);
}
