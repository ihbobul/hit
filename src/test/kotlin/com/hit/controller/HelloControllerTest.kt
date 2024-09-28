package com.hit.controller

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest
class HelloControllerTest(
        @Client("/") val client: HttpClient
) : StringSpec({

  "should return Hello World" {
    val response = client.toBlocking().retrieve("/hello")
    response shouldBe "Hello World"
  }
})