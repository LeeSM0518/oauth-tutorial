import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { OauthInfo } from '@/domain/oauth-info'
import Storage from '@/utils/storage'

export const oauthInfoStorage = new Storage<OauthInfo>('oauthInfo')

export const useOauthInfoStore = defineStore('oauthStore', () => {
  const oauthInfo = ref(oauthInfoStorage.get())

  async function updateOauthInfo(oauthInfoData?: OauthInfo | null) {
    if (oauthInfoData) {
      oauthInfoStorage.set(oauthInfoData)
      oauthInfo.value = oauthInfoData
    } else {
      oauthInfoStorage.remove()
      oauthInfo.value = null
    }
  }

  return {
    oauthInfo,
    updateOauthInfo
  }
})
