<template>
  <div></div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { useKakaoLogin } from '@/composable/useKakaoLogin'

const { receiveKakaoLoginResponse } = useKakaoLogin()

onMounted(async () => {
  await receiveKakaoLoginResponse()
})
</script>
