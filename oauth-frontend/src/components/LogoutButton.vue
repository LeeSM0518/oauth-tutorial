<template>
    <v-row justify="center">
        <v-btn @click="logout" color="gray">로그아웃</v-btn>
    </v-row>
</template>

<script setup lang="ts">
import { useLogout } from '@/composable/useLogout';

const { logout } = useLogout()
</script>