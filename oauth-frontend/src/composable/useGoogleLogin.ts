import { useRoute, useRouter } from 'vue-router'
import { AuthController, OauthType } from '@/controller/auth-controller'
import { AxiosError } from 'axios'

const GOOGLE_ENV = {
  GOOGLE_LOGIN_API_URL: import.meta.env.VITE_GOOGLE_LOGIN_API_URL,
  GOOGLE_CLIENT_ID: import.meta.env.VITE_GOOGLE_CLIENT_ID,
  GOOGLE_REDIRECT_URL: import.meta.env.VITE_GOOGLE_REDIRECT_URL
}

const loginLink =
  `${GOOGLE_ENV.GOOGLE_LOGIN_API_URL}` +
  `?response_type=code&` +
  `client_id=${GOOGLE_ENV.GOOGLE_CLIENT_ID}&` +
  `redirect_uri=${GOOGLE_ENV.GOOGLE_REDIRECT_URL}&` +
  `scope=profile`

export function useGoogleLogin() {
  const router = useRouter()
  const route = useRoute()
  const controller = new AuthController()

  function openGoogleLoginPage(): void {
    window.location.href = loginLink
  }

  function routeHome(): void {
    router.push({ path: '/' })
  }

  async function receiveGoogleLoginResponse() {
    const response = {
      code: route.query.code as string,
      error: route.query.error as string
    }

    try {
      if (response.code != null && response.error == null) {
        await controller.login(response.code, OauthType.GOOGLE)
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
    openGoogleLoginPage,
    receiveGoogleLoginResponse
  }
}
