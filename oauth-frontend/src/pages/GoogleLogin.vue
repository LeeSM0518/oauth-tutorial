<template>
  <div></div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { useGoogleLogin } from '@/composable/useGoogleLogin'

const { receiveGoogleLoginResponse } = useGoogleLogin()

onMounted(async () => {
  await receiveGoogleLoginResponse()
})
</script>
