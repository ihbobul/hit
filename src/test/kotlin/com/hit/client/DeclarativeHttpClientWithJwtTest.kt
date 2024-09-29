package com.hit.client

import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.security.authentication.UsernamePasswordCredentials
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class DeclarativeHttpClientWithJwtTest(
        @Inject private val appClient: AppClient
) : StringSpec({

    "verify JWT authentication works with declarative client" {
        val creds = UsernamePasswordCredentials("sherlock", "password")
        val loginRsp = appClient.login(creds)
        println(loginRsp)

        loginRsp.shouldNotBeNull()
        loginRsp.accessToken.shouldNotBeNull()
        (JWTParser.parse(loginRsp.accessToken) is SignedJWT).shouldBeTrue()

        val msg = appClient.home("Bearer ${loginRsp.accessToken}")
        msg shouldBe "sherlock"
    }
})
