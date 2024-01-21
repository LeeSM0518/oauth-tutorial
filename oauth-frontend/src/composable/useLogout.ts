import { AuthController } from "@/controller/auth-controller"
import { useRouter } from "vue-router"

export function useLogout() {
    const router = useRouter()
    const controller = new AuthController

    async function logout() {
        try {
            controller.logout()
        } catch (error) {
            reportError({ error })
        }
        router.push({ path: "/" })
    }

    return {
        logout
    }
}