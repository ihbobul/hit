package com.hit.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {
  @Get
  String index() {
    return "Hello World";
  }
}
