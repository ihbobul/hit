package com.hit.controller;

import com.hit.model.dto.HabitDTO;
import com.hit.service.HabitService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.Valid;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/habit")
public class HabitController {

  private final HabitService habitService;

  public HabitController(HabitService habitService) {
    this.habitService = habitService;
  }

  @Post("/create")
  public HttpResponse<?> createHabit(@Valid @Body HabitDTO habitDTO) {
    habitService.createHabit(habitDTO);
    return HttpResponse.ok("Habit created successfully.");
  }
}
