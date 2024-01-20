import type { AxiosHeaders, AxiosInstance, AxiosResponse } from "axios"
import { createApi } from "."
import type { Email, GetTokenResponse } from "@/controller/login-controller"

export const NAVER_ENV = {
    NAVER_LOGIN_API_HOST: import.meta.env.VITE_NAVER_LOGIN_API_HOST,
    NAVER_LOGIN_URL: import.meta.env.VITE_NAVER_LOGIN_URL,
    NAVER_TOKEN_URL: import.meta.env.VITE_NAVER_TOKEN_URL,
    NAVER_USER_URL: import.meta.env.VITE_NAVER_USER_URL,
    NAVER_CLIENT_ID: import.meta.env.VITE_NAVER_CLIENT_ID,
    NAVER_CLIENT_SECRET: import.meta.env.VITE_NAVER_CLIENT_SECRET,
    NAVER_STATE: import.meta.env.VITE_NAVER_STATE,
    NAVER_REDIRECT_URL: import.meta.env.VITE_NAVER_REDIRECT_URL
}

export class NaverLoginApi {
    private tokenApi: AxiosInstance = createApi(NAVER_ENV.NAVER_TOKEN_URL)
    private userApi: (accessToken: string) => AxiosInstance = (accessToken: string) => createApi(NAVER_ENV.NAVER_USER_URL, { Authorization: `Bearer ${accessToken}` })

    async getToken(code: string): Promise<GetTokenResponse> {
        const response: AxiosResponse<GetNaverTokenResponse> =
            await this.tokenApi.get("", {
                params: {
                    grant_type: "authorization_code",
                    client_id: NAVER_ENV.NAVER_CLIENT_ID,
                    client_secret: NAVER_ENV.NAVER_CLIENT_SECRET,
                    code: code,
                    state: NAVER_ENV.NAVER_STATE
                }
            })

        if (response.data.error) {
            throw Error(`네이버 사용자의 토큰 조회를 실패했습니다.`)
        }

        return {
            accessToken: response.data.access_token!!,
            refreshToken: response.data.refresh_token!!
        }
    }

    async getEmail(accessToken: string): Promise<Email> {
        const response: AxiosResponse<GetNaverUserResponse> = await this.userApi(accessToken).get("")

        if (!response.data.response.email) {
            throw Error(`네이버 사용자의 이메일 조회를 실패했습니다.`)
        }

        return response.data.response.email
    }
}

interface GetNaverTokenResponse {
    access_token?: string,
    refresh_token?: string,
    token_type?: string,
    expires_in?: number,
    error?: string,
    error_description?: string
}

interface GetNaverUserResponse {
    response: {
        email?: string
    }
}