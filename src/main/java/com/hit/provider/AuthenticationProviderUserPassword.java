package com.hit.provider;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.hit.model.entity.UserEntity;
import com.hit.repository.UserRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class AuthenticationProviderUserPassword<B> implements HttpRequestAuthenticationProvider<B> {

  private final UserRepository userRepository;

  @Inject
  public AuthenticationProviderUserPassword(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public AuthenticationResponse authenticate(
      @Nullable HttpRequest<B> httpRequest,
      @NonNull AuthenticationRequest<String, String> authenticationRequest
  ) {
    String username = authenticationRequest.getIdentity();
    String password = authenticationRequest.getSecret();

    return userRepository.findByUsername(username)
        .map(userEntity -> verifyPassword(userEntity, password))
        .orElseGet(
            () -> AuthenticationResponse.failure(AuthenticationFailureReason.USER_NOT_FOUND));
  }

  private AuthenticationResponse verifyPassword(UserEntity userEntity, String password) {
    BCrypt.Result result = BCrypt.verifyer()
        .verify(password.toCharArray(), userEntity.password());

    return result.verified
        ? AuthenticationResponse.success(userEntity.username())
        : AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
  }
}
