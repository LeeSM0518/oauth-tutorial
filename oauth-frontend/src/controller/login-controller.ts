import type { Member } from "@/domain/member";
import type { TokenGroup } from "@/domain/token-group";
import { OauthLoginApi } from "@/api/oauth-login";

export class OauthController {
    private oauthLoginApi = new OauthLoginApi

    async oauthLogin(authorizationCode: string, type: OauthType): Promise<OauthLoginResponse> {
        const response: OauthLoginResponse = await this.oauthLoginApi.login(authorizationCode, type)
        return response
    }
}

export type Email = string

export enum OauthType {
    NAVER
}

export interface OauthLoginResponse {
    member: Member,
    tokenGroup: TokenGroup
}