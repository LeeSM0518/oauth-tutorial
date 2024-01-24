import { MemberApi, type SignUpRequest, type SignUpResponse } from '@/api/member-api'
import { useMemberStore } from '@/stores/member'
import { useTokenGroupStore } from '@/stores/token-group'

const { updateMember } = useMemberStore()
const { updateTokenGroup } = useTokenGroupStore()

export class MemberController {
  private memberApi = new MemberApi()

  async signUp(signUpRequest: SignUpRequest): Promise<SignUpResponse> {
    const response: SignUpResponse = await this.memberApi.signUp(signUpRequest)
    updateMember(response.member)
    updateTokenGroup(response.tokenGroup)
    return response
  }
}
