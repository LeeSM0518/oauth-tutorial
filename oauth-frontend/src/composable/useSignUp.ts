import { MemberController } from '@/controller/member-controller'
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useOauthInfoStore } from '@/stores/oauth-info'
import { AxiosError } from 'axios'
import { OauthType } from '@/controller/auth-controller'

export function useSignUp() {
  const router = useRouter()
  const controller = new MemberController()

  const { oauthInfo } = useOauthInfoStore()
  const valid = ref(false)
  const formRef = ref(null)
  const signUpFailMessage = reactive({ message: '' })
  const form: SignUpForm = reactive({ oauthId: '', nickname: '', oauthType: OauthType.NAVER })
  const rules = ref({
    required: (v: string) => !!v || '닉네임이 반드시 필요합니다'
  })

  const isFail = ref(false)

  function routeHome() {
    router.push({ path: '/' })
  }

  function setOauthId() {
    if (!oauthInfo) {
      reportError('이메일 정보가 존재하지 않습니다.')
      routeHome()
    } else {
      form.oauthId = oauthInfo.oauthId
      form.oauthType = oauthInfo.oauthType
    }
  }

  async function signUp() {
    try {
      await controller.signUp(form)
      routeHome()
    } catch (error) {
      if (error instanceof AxiosError && error?.response?.data.status == 409) {
        signUpFailMessage.message = error.response.data.reason
        isFail.value = true
      }
    }
  }

  return {
    valid,
    formRef,
    form,
    rules,
    routeHome,
    setOauthId,
    signUp,
    signUpFailMessage,
    isFail
  }
}

export interface SignUpForm {
  oauthId: string
  nickname: string
  oauthType: OauthType
}
