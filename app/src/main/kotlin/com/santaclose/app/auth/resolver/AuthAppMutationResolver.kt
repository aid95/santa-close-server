package com.santaclose.app.auth.resolver

import arrow.core.flatMap
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Mutation
import com.santaclose.app.auth.resolver.dto.AppAuthInfo
import com.santaclose.app.auth.resolver.dto.SignInAppInput
import com.santaclose.app.auth.service.AuthAppService
import com.santaclose.app.config.JWTConfig
import com.santaclose.lib.web.error.getOrThrow
import org.springframework.stereotype.Component

@Component
class AuthAppMutationResolver(
  private val authAppService: AuthAppService,
  private val jwtConfig: JWTConfig,
) : Mutation {
  @GraphQLDescription("로그인 및 회원가입")
  suspend fun signIn(input: SignInAppInput): AppAuthInfo =
    authAppService
      .signIn(input.code)
      .flatMap { AppAuthInfo.by(it, jwtConfig.key, jwtConfig.expiredDays) }
      .getOrThrow()
}
