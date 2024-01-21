import type { Member } from "@/domain/member";
import { NaverLoginApi } from "@/api/naver-login";
import type { TokenGroup } from "@/domain/token-group";
import { OauthLoginApi } from "@/api/oauth-login";

export class OauthController {
    private naverLoginApi = new NaverLoginApi
    private oauthLoginApi = new OauthLoginApi

    async oauthLogin(authorizationCode: string, type: OauthType): Promise<OauthLoginResponse> {
        await this.oauthLoginApi.login(authorizationCode)
        return {
            member: {
                email: "email",
                id: "efed1040-279b-420c-ad16-fa3d176dcdc1",
                nickname: "nickname"
            },
            tokenGroup: {
                accessToken: "accessToken",
                refreshToken: "refreshToken"
            }
        }
    }

    async naverLogin(authorizationCode: string): Promise<Email> {
        const token: GetTokenResponse = await this.naverLoginApi.getToken(authorizationCode)
        return await this.naverLoginApi.getEmail(token.accessToken)
    }
}

export type Email = string

export enum OauthType {
    NAVER
}

export interface GetTokenResponse {
    accessToken: string,
    refreshToken: string
}

interface NaverLoginResponse {
    state?: string,
    code?: string,
    error?: string,
    error_description?: string
}

export interface OauthLoginResponse {
    member: Member,
    tokenGroup: TokenGroup
}