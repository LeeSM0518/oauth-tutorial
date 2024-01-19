<template>
  <div>
    <button @click="naverLogin">네이버 로그인</button>
  </div>
</template>

<script setup lang="ts">
const naverClientId = import.meta.env.VITE_NAVER_CLIENT_ID;
const naverLoginHost = import.meta.env.VITE_NAVER_LOGIN_API_HOST;
const naverLoginUrl = import.meta.env.VITE_NAVER_LOGIN_URL;
const naverRedirectUrl = import.meta.env.VITE_NAVER_REDIRECT_URL;
const naverState = import.meta.env.VITE_NAVER_STATE;

const url = `${naverLoginHost}${naverLoginUrl}?response_type=code&` +
    `client_id=${naverClientId}&` +
    `redirect_uri=${naverRedirectUrl}&` +
    `state=${naverState}`

async function naverLogin() {
  window.location.href = url
}
</script>

<style scoped>
</style>
