<template>
  <div>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { useNaverLogin } from "@/composable/useNaverLogin"

const { receiveNaverLoginResponse } = useNaverLogin()

onMounted(async () => {
  await receiveNaverLoginResponse()
})
</script>

<style scoped></style>
