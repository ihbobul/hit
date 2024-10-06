package com.hit.controller;

import com.hit.model.dto.SignupDTO;
import com.hit.service.AuthService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.Valid;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Post("/signup")
  public HttpResponse<?> signup(@Valid @Body SignupDTO signupDTO) {
    authService.signup(signupDTO);
    return HttpResponse.ok("User created successfully.");
  }

}
