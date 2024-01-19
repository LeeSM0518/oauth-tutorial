<template>
  <div>
    <div>{{ loginResultMessage.message }}</div>
    <button @click="routeHome">홈화면으로 이동</button>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { LoginController, type NaverLoginResponse } from "@/controller/LoginController";

const route = useRoute()
const router = useRouter()
const controller = new LoginController()
const loginResultMessage = ref({
  message: ""
})

async function routeHome() {
  router.push({ path: "/" })
}

async function naverCallback() {
  const url = ``
}

onMounted(async () => {
  const response: NaverLoginResponse = route.query as NaverLoginResponse
  console.log(response);

  if (response.state != null && response.error == null) {
    loginResultMessage.value.message = "네이버 로그인 성공"
  } else {
    loginResultMessage.value.message = "네이버 로그인 실패"
  }
})
</script>

<style scoped></style>
