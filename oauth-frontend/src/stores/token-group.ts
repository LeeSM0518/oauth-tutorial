import { ref, computed } from "vue"
import { defineStore } from "pinia";
import type { TokenGroup } from "@/domain/token-group";
import Storage from "@/utils/storage";
import Cookies from "cookies-ts"

export const tokenGroupStorage = new Storage<TokenGroup>("tokenGroup")

export const useTokenGroupStore = defineStore("tokenGroup", () => {
    const tokenGroup = ref(tokenGroupStorage.get())
    const isAuthorizedTokenGroup = computed(() => !!tokenGroup.value)
    const cookies = new Cookies()

    async function updateTokenGroup(tokenGroupData?: TokenGroup | null) {
        if (tokenGroupData) {
            cookies.set("oauthRefreshToken", tokenGroupData.refreshToken, { expires: "7d" })
            tokenGroupStorage.set(tokenGroupData)
            tokenGroup.value = tokenGroupData
        } else {
            cookies.remove("oauthRefreshToken")
            tokenGroupStorage.remove()
            tokenGroup.value = null
        }
    }

    return {
        tokenGroup,
        isAuthorizedTokenGroup,
        updateTokenGroup
    }
})