import type { Member } from "@/domain/member";
import type { TokenGroup } from "@/domain/token-group";
import { AuthApi } from "@/api/auth-login";
import { useMemberStore } from "@/stores/member";
import { useTokenGroupStore } from "@/stores/token-group";

const { updateMember } = useMemberStore()
const { updateTokenGroup } = useTokenGroupStore()

export class AuthController {
    private authApi = new AuthApi


    async login(authorizationCode: string, type: OauthType): Promise<LoginResponse> {
        const response: LoginResponse = await this.authApi.login(authorizationCode, type)
        updateMember(response.member)
        updateTokenGroup(response.tokenGroup)
        return response
    }

    async logout(): Promise<LogoutResponse> {
        const { tokenGroup } = useTokenGroupStore()
        if (tokenGroup) {
            const response: LogoutResponse = await this.authApi.logout(tokenGroup.refreshToken)
            updateMember(null)
            updateTokenGroup(null)
            return response
        } else {
            updateMember(null)
            updateTokenGroup(null)
            throw Error("리프레시 토큰이 존재하지 않습니다.")
        }
    }
}

export type Email = string

export enum OauthType {
    NAVER
}

export interface LoginResponse {
    member: Member,
    tokenGroup: TokenGroup
}

export interface LogoutResponse {
    message: string
}