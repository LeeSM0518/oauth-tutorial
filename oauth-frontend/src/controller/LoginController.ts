import type { User } from "@/domain/user";

import { NaverLoginApi, type GetTokenResponse } from "@/api/naver-login";

export class LoginController {
    private api = new NaverLoginApi

    async login(code: string) {
        const response: GetTokenResponse = await this.api.getToken(code)
    }
}

export interface NaverLoginResponse {
    state?: string,
    code?: string,
    error?: string,
    error_description?: string
}