import { ref, computed } from "vue"
import { defineStore } from "pinia";
import type { TokenGroup } from "@/domain/token-group";
import Storage from "@/utils/storage";

export const tokenGroupStorage = new Storage<TokenGroup>("tokenGroup")

export const useTokenGroupStore = defineStore("tokenGroup", () => {
    const tokenGroup = ref(tokenGroupStorage.get())
    const isAuthorizedTokenGroup = computed(() => !!tokenGroup.value)

    async function updateTokenGroup(tokenGroupData?: TokenGroup | null) {
        if (tokenGroupData) {
            tokenGroupStorage.set(tokenGroupData)
            tokenGroup.value = tokenGroupData
        } else {
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