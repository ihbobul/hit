package com.hit

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.instanceOf
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus.OK
import io.micronaut.http.HttpStatus.UNAUTHORIZED
import io.micronaut.http.MediaType.TEXT_PLAIN
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.security.token.render.BearerAccessRefreshToken
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class JwtAuthenticationTest(
        @Inject @Client("/") private val client: HttpClient
) : StringSpec({

    "accessing a secured URL without authenticating returns Unauthorized" {
        val exception = shouldThrow<HttpClientResponseException> {
            client.toBlocking().exchange<Any, String>(HttpRequest.GET<Any>("/").accept(TEXT_PLAIN))
        }
        exception.status shouldBe UNAUTHORIZED
    }

    "upon successful authentication a JSON Web Token is issued to the user" {
        val creds = UsernamePasswordCredentials("sherlock", "password")
        val request = HttpRequest.POST("/login", creds)
        val response = client.toBlocking().exchange(request, BearerAccessRefreshToken::class.java)

        response.status shouldBe OK
        val bearerAccessRefreshToken = response.body()!!
        bearerAccessRefreshToken.username shouldBe "sherlock"
        val accessToken = bearerAccessRefreshToken.accessToken
        accessToken shouldNotBe null

        val parsedJwt = JWTParser.parse(accessToken)
        parsedJwt shouldBe instanceOf<SignedJWT>()

        val requestWithAuthorization = HttpRequest.GET<String>("/")
                .accept("text/plain")
                .bearerAuth(accessToken)
        val securedResponse = client.toBlocking().exchange(requestWithAuthorization, String::class.java)

        securedResponse.status shouldBe OK
        securedResponse.body() shouldBe "sherlock"
    }
})