<template>
  <div>
    <div>{{ loginResultMessage.message }}</div>
    <button @click="routeHome">홈화면으로 이동</button>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { useNaverLogin } from "@/composable/useNaverLogin"

const { loginResultMessage, receiveNaverLoginResponse, routeHome } = useNaverLogin()

onMounted(async () => {
  await receiveNaverLoginResponse()
})
</script>

<style scoped></style>
