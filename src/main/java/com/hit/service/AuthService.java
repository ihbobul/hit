package com.hit.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.hit.exception.EmailAlreadyExistsException;
import com.hit.exception.UsernameAlreadyExistsException;
import com.hit.model.dto.SignupDTO;
import com.hit.model.entity.UserEntity;
import com.hit.model.mapper.UserMapper;
import com.hit.repository.UserRepository;
import jakarta.inject.Singleton;

@Singleton
public class AuthService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public AuthService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public void signup(SignupDTO signupDTO) {
    if (userRepository.existsByUsername(signupDTO.username())) {
      throw new UsernameAlreadyExistsException("Username is already taken.");
    }

    if (userRepository.existsByEmail(signupDTO.email())) {
      throw new EmailAlreadyExistsException("Email is already taken.");
    }

    String hashedPassword = BCrypt.withDefaults()
        .hashToString(12, signupDTO.password().toCharArray());

    SignupDTO signupDTOWithHashedPassword = new SignupDTO(signupDTO.username(), hashedPassword,
        signupDTO.email());

    // FIXME: Mapping is not working, so we need to create the entity manually
    UserEntity userEntity = new UserEntity(signupDTOWithHashedPassword.username(),
        signupDTOWithHashedPassword.password(), signupDTOWithHashedPassword.email());

    userRepository.save(userEntity);
  }
}
