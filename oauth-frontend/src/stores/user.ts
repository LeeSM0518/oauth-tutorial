import {computed, ref} from "vue"
import {defineStore} from "pinia";
import type {User} from "@/domain/user";
import Storage from "../../utils/storage";

export const userStorage = new Storage<User>("user")

export const useUserStore = defineStore("user", () => {
    const user = ref(userStorage.get())
    const isAuthorized = computed(() => !!user.value)

    async function updateUser(userData?: User | null) {
        if (userData) {
            userStorage.set(userData)
            user.value = userData
        } else {
            userStorage.remove()
            user.value = null
        }
    }

    return {
        user,
        isAuthorized,
        updateUser
    }
})