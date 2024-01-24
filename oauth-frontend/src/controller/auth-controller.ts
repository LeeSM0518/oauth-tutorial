import { AuthApi, type LoginResponse, type LogoutResponse } from '@/api/auth-api'
import { useMemberStore } from '@/stores/member'
import { useTokenGroupStore } from '@/stores/token-group'
import { useOauthInfoStore } from '@/stores/oauth-info'
import { AxiosError } from 'axios'

const { updateMember } = useMemberStore()
const { updateTokenGroup } = useTokenGroupStore()
const { updateOauthInfo } = useOauthInfoStore()

export class AuthController {
  private authApi = new AuthApi()

  async login(authorizationCode: string, type: OauthType): Promise<LoginResponse> {
    try {
      const response: LoginResponse = await this.authApi.login(authorizationCode, type)
      updateMember(response.member)
      updateTokenGroup(response.tokenGroup)
      return response
    } catch (error) {
      if (error instanceof AxiosError && error?.response?.data.status == 404) {
        const reason: ErrorReason = JSON.parse(error.response.data.reason)
        if (!reason.email) throw error
        updateOauthInfo({ email: reason.email })
      }
      throw error
    }
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
      throw Error('리프레시 토큰이 존재하지 않습니다.')
    }
  }
}

export type Email = string

export enum OauthType {
  NAVER
}

interface ErrorReason {
  email?: string
}
