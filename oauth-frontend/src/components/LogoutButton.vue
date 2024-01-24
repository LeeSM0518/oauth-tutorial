<template>
  <v-btn @click="logout" color="warning">로그아웃</v-btn>
</template>

<script setup lang="ts">
import { useLogout } from '@/composable/useLogout'

const { logout } = useLogout()
</script>
