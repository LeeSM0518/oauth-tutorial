import { type AxiosInstance, type AxiosResponse } from 'axios'
import { createApi } from '.'
import type { OauthType } from '@/controller/auth-controller'
import type { Member } from '@/domain/member'
import type { TokenGroup } from '@/domain/token-group'

const BACKEND_AUTH_URL = import.meta.env.VITE_BACKEND_AUTH_URL

export class AuthApi {
  private authApi: AxiosInstance = createApi(BACKEND_AUTH_URL)

  async login(authorizationCode: string, type: OauthType): Promise<LoginResponse> {
    const response: AxiosResponse<LoginResponse> = await this.authApi.post('/login', {
      authorizationCode: authorizationCode,
      oauthType: type
    })
    return response.data
  }

  async logout(refreshToken: string): Promise<LogoutResponse> {
    const response: AxiosResponse<LogoutResponse> = await this.authApi.post('/logout', {
      refreshToken: refreshToken
    })

    return response.data
  }
}

export interface LoginResponse {
    member: Member
    tokenGroup: TokenGroup
  }

export interface LogoutResponse {
    message: string
  }