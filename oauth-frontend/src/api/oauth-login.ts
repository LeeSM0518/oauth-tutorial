import type { AxiosInstance, AxiosResponse } from "axios";
import { createApi } from ".";
import type { OauthLoginResponse } from "@/controller/login-controller";

const BACKEND_ENV = {
    BACKEND_AUTH_URL: import.meta.env.VITE_BACKEND_AUTH_URL
}

export class OauthLoginApi {
    private loginApi: AxiosInstance = createApi(BACKEND_ENV.BACKEND_AUTH_URL)

    async login(code: string): Promise<OauthLoginResponse> {
        const response: AxiosResponse<OauthLoginResponse> =
            await this.loginApi.post("/login", {
                authorizationCode: code,
                oauthType: "NAVER"
            })

        console.log(response)

        return response.data
    }
}