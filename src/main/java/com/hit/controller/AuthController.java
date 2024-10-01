package com.hit.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.hit.model.dto.SignupDTO;
import com.hit.model.entity.UserEntity;
import com.hit.model.mapper.UserMapper;
import com.hit.repository.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class AuthController {

  @Inject
  private UserRepository userRepository;

  @Inject
  private UserMapper userMapper;

  @Post("/signup")
  public HttpResponse<?> signup(@Valid @Body SignupDTO signupDTO) {
    if (userRepository.existsByUsername(signupDTO.username())) {
      return HttpResponse.badRequest("Username is already taken");
    }

    if (userRepository.existsByEmail(signupDTO.email())) {
      return HttpResponse.badRequest("Email is already taken");
    }

    String hashedPassword = BCrypt.withDefaults()
        .hashToString(12, signupDTO.password().toCharArray());

    SignupDTO signupDTOWithHashedPassword = new SignupDTO(signupDTO.username(), hashedPassword,
        signupDTO.email());

    UserEntity userEntity = userMapper.toEntity(signupDTOWithHashedPassword);

    userRepository.save(userEntity);

    return HttpResponse.ok("User created successfully.");
  }
}
