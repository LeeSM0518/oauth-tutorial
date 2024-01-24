import { type AxiosInstance, type AxiosResponse } from 'axios'
import { createApi } from '.'
import type { Member } from '@/domain/member'
import type { TokenGroup } from '@/domain/token-group'
import type { OauthType } from '@/controller/auth-controller'

const BACKEND_MEMBER_URL = import.meta.env.VITE_BACKEND_MEMBER_URL

export class MemberApi {
  private memberApi: AxiosInstance = createApi(BACKEND_MEMBER_URL)

  async signUp(request: SignUpRequest): Promise<SignUpResponse> {
    const response: AxiosResponse<SignUpResponse> = await this.memberApi.post('/signup', request)
    return response.data
  }
}

export interface SignUpRequest {
  oauthId: string
  nickname: string
  oauthType: OauthType
}

export interface SignUpResponse {
  member: Member
  tokenGroup: TokenGroup
}
