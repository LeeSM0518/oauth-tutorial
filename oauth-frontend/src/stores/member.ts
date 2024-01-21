import { computed, ref } from "vue"
import { defineStore } from "pinia";
import type { Member } from "@/domain/member";
import Storage from "@/utils/storage";

export const memberStorage = new Storage<Member>("member")

export const useMemberStore = defineStore("member", () => {
    const member = ref(memberStorage.get())
    const isAuthorized = computed(() => !!member.value)

    async function updateMember(memberData?: Member | null) {
        if (memberData) {
            memberStorage.set(memberData)
            member.value = memberData
        } else {
            memberStorage.remove()
            member.value = null
        }
    }

    return {
        user: member,
        isAuthorized,
        updateMember
    }
})