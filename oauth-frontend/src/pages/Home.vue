<template>
  <div>
    <button @click="openNaverLoginPage">네이버 로그인</button>
  </div>
</template>

<script setup lang="ts">
import { useNaverLogin } from "@/composable/useNaverLogin"

const { openNaverLoginPage } = useNaverLogin()
</script>

<style scoped>
</style>
