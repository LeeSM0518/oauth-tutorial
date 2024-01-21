import { type AxiosInstance, type AxiosResponse } from "axios";
import { createApi } from ".";
import type { OauthLoginResponse, OauthType } from "@/controller/login-controller";

const BACKEND_ENV = {
    BACKEND_AUTH_URL: import.meta.env.VITE_BACKEND_AUTH_URL
}

export class OauthLoginApi {
    private loginApi: AxiosInstance = createApi(BACKEND_ENV.BACKEND_AUTH_URL)

    async login(authorizationCode: string, type: OauthType): Promise<OauthLoginResponse> {
        const response: AxiosResponse<OauthLoginResponse> =
            await this.loginApi.post("/login", {
                authorizationCode: authorizationCode,
                oauthType: type
            })
        return response.data
    }
}