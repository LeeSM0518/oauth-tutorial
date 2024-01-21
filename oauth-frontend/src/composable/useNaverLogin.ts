import { ref } from "vue"
import { useRoute, useRouter } from "vue-router"
import { OauthController, OauthType } from "@/controller/login-controller"
import { AxiosError } from "axios"

const NAVER_ENV = {
    NAVER_LOGIN_API_HOST: import.meta.env.VITE_NAVER_LOGIN_API_HOST,
    NAVER_CLIENT_ID: import.meta.env.VITE_NAVER_CLIENT_ID,
    NAVER_STATE: import.meta.env.VITE_NAVER_STATE,
    NAVER_REDIRECT_URL: import.meta.env.VITE_NAVER_REDIRECT_URL
}

const loginLink =
    `${NAVER_ENV.NAVER_LOGIN_API_HOST}/oauth2.0/authorize` +
    `?response_type=code&` +
    `client_id=${NAVER_ENV.NAVER_CLIENT_ID}&` +
    `redirect_uri=${NAVER_ENV.NAVER_REDIRECT_URL}&` +
    `state=${NAVER_ENV.NAVER_STATE}`


export function useNaverLogin() {
    const router = useRouter()
    const route = useRoute()
    const controller = new OauthController

    const loginResultMessage = ref({
        message: ""
    })

    function openNaverLoginPage(): void {
        window.location.href = loginLink
    }

    function routeHome(): void {
        router.push({ path: "/" })
    }

    async function receiveNaverLoginResponse() {
        const response = {
            state: route.query.state as string,
            code: route.query.code as string,
            error: route.query.error as string
        }

        try {
            if (response.state != null && response.code != null && response.error == null) {
                await controller.oauthLogin(response.code, OauthType.NAVER)
                loginResultMessage.value.message = "네이버 로그인 성공"
            } else {
                loginResultMessage.value.message = "네이버 로그인 실패"
            }
        } catch (error) {
            let message
            if (error instanceof AxiosError && error?.response?.data.status == 404) {
                console.log("회원가입 필요")
            }
            else message = String(error)
            loginResultMessage.value.message = `네이버 로그인 실패 (${message})`
            setTimeout(() => router.push({ path: "/" }), 3000)
            reportError({ message })
        }
    }

    return {
        loginResultMessage,
        routeHome,
        openNaverLoginPage,
        receiveNaverLoginResponse
    }
}