package com.hit.controller;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;


@Controller("/user")
public class UserController {

  @Secured("isAuthenticated()")
  @Get("/my-info")
  public Map myinfo(@Nullable Principal principal) {
    if (principal == null) {
      return Collections.singletonMap("isLoggedIn", false);
    }
    return CollectionUtils.mapOf("isLoggedIn", true, "username", principal.getName());
  }
}