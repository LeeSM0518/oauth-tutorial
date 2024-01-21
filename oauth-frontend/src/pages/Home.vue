<template>
  <logout-button v-if="isAuthorizedMember && isAuthorizedTokenGroup" />
  <login-button v-else />
</template>

<script setup lang="ts">
import LoginButton from "@/components/LoginButton.vue"
import LogoutButton from "@/components/LogoutButton.vue"

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { useTokenGroupStore } from "@/stores/token-group";

const { isAuthorizedMember } = storeToRefs(useMemberStore())
const { isAuthorizedTokenGroup } = storeToRefs(useTokenGroupStore())
</script>

<style scoped></style>
