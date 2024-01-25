import { useRoute, useRouter } from 'vue-router'
import { AuthController, OauthType } from '@/controller/auth-controller'
import { AxiosError } from 'axios'

const KAKAO_ENV = {
  KAKAO_LOGIN_API_URL: import.meta.env.VITE_KAKAO_LOGIN_API_URL,
  KAKAO_CLIENT_ID: import.meta.env.VITE_KAKAO_CLIENT_ID,
  KAKAO_REDIRECT_URL: import.meta.env.VITE_KAKAO_REDIRECT_URL
}

const loginLink =
  `${KAKAO_ENV.KAKAO_LOGIN_API_URL}` +
  `?response_type=code&` +
  `client_id=${KAKAO_ENV.KAKAO_CLIENT_ID}&` +
  `redirect_uri=${KAKAO_ENV.KAKAO_REDIRECT_URL}`

export function useKakaoLogin() {
  const router = useRouter()
  const route = useRoute()
  const controller = new AuthController()

  function openKakaoLoginPage(): void {
    window.location.href = loginLink
  }

  function routeHome(): void {
    router.push({ path: '/' })
  }

  async function receiveKakaoLoginResponse() {
    const response = {
      code: route.query.code as string,
      error: route.query.error as string
    }

    try {
      if (response.code != null && response.error == null) {
        await controller.login(response.code, OauthType.KAKAO)
        router.push({ path: '/' })
      } else {
        throw Error(response.error)
      }
    } catch (error) {
      let message
      if (error instanceof AxiosError && error?.response?.data.status == 404) {
        router.push({ path: `/signup` })
      } else message = String(error)
      reportError({ message })
    }
  }

  return {
    routeHome,
    openKakaoLoginPage,
    receiveKakaoLoginResponse
  }
}
