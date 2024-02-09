package org.example.oauthbackend.auth.exception

import org.example.oauthbackend.exception.ServerException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

const val CREATE_ACCESS_TOKEN_EXCEPTION = "액세스 토큰 생성을 실패했습니다."
const val CREATE_REFRESH_TOKEN_EXCEPTION = "리프래시 토큰 생성을 실패했습니다."
const val UNSUPPORTED_TOKEN_EXCEPTION = "잘못된 형식의 토큰 입니다."
const val EXPIRED_TOKEN_EXCEPTION = "만료된 토큰 입니다."
const val VALIDATE_TOKEN_EXCEPTION = "토큰 검증을 실패했습니다."
const val INVALID_REFRESH_TOKEN_EXCEPTION = "잘못된 리프래시 토큰입니다."
const val NOT_FOUND_REFRESH_TOKEN_EXCEPTION = "존재하지 않는 리프래시 토큰입니다."
const val DUPLICATE_NICKNAME_EXCEPTION = "닉네임이 이미 존재합니다."
const val DUPLICATE_EMAIL_EXCEPTION = "이미 존재하는 회원입니다."
const val NAVER_OAUTH_LOGIN_FAIL_EXCEPTION = "네이버 OAuth 인증을 실패했습니다."
const val KAKAO_OAUTH_LOGIN_FAIL_EXCEPTION = "카카오 OAuth 인증을 실패했습니다."

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class CreateAccessTokenException : ServerException(CREATE_ACCESS_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class CreateRefreshTokenException : ServerException(CREATE_REFRESH_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class UnsupportedTokenFormatException : ServerException(UNSUPPORTED_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class ExpiredTokenException : ServerException(EXPIRED_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class ValidateTokenException : ServerException(VALIDATE_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class InvalidRefreshTokenException : ServerException(INVALID_REFRESH_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class NotFoundRefreshTokenException : ServerException(NOT_FOUND_REFRESH_TOKEN_EXCEPTION)

@ResponseStatus(code = HttpStatus.CONFLICT)
class DuplicateNicknameException : ServerException(DUPLICATE_NICKNAME_EXCEPTION)

@ResponseStatus(code = HttpStatus.CONFLICT)
class DuplicateOauthException : ServerException(DUPLICATE_EMAIL_EXCEPTION)

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class MemberNotFoundByEmailException(message: String) : ServerException(message)

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class OauthLoginFailException(reason: String) : ServerException(reason)