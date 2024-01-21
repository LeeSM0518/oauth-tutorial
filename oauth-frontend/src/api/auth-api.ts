import { type AxiosInstance, type AxiosResponse } from "axios";
import { createApi, type HttpHeader } from ".";
import type { LoginResponse, LogoutResponse, OauthType } from "@/controller/auth-controller";

const BACKEND_ENV = {
    BACKEND_AUTH_URL: import.meta.env.VITE_BACKEND_AUTH_URL
}

export class AuthApi {
    private authApi: AxiosInstance = createApi(BACKEND_ENV.BACKEND_AUTH_URL)

    async login(authorizationCode: string, type: OauthType): Promise<LoginResponse> {
        const response: AxiosResponse<LoginResponse> =
            await this.authApi.post("/login", {
                authorizationCode: authorizationCode,
                oauthType: type
            })
        return response.data
    }

    async logout(refreshToken: string): Promise<LogoutResponse> {
        const response: AxiosResponse<LogoutResponse> =
            await this.authApi.post("/logout", {refreshToken: refreshToken})

        return response.data
    }
}