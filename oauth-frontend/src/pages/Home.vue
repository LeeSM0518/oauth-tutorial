<template>
  <logout-button v-if="isAuthorized" />
  <login-button v-else />
</template>

<script setup lang="ts">
import LoginButton from "@/components/LoginButton.vue"
import LogoutButton from "@/components/LogoutButton.vue"

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
const { isAuthorized } = storeToRefs(useMemberStore())
</script>

<style scoped></style>
