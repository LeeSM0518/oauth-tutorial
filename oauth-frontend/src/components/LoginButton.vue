<template>
  <v-dialog v-model="dialog" width="256">
    <template v-slot:activator="{ props }">
      <v-btn color="primary" v-bind="props"> 로그인 </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span>소셜 로그인</span>
      </v-card-title>
      <v-card-text>
        <v-row justify="center">
          <v-col>
            <v-btn icon flat @click="openNaverLoginPage">
              <img width="45" height="45" src="@/assets/naver_login_icon.png" />
            </v-btn>
          </v-col>
          <v-col>
            <v-btn icon flat @click="openKakaoLoginPage">
              <img width="45" height="45" src="@/assets/kakao_login_icon.png" />
            </v-btn>
          </v-col>
          <v-col>
            <v-btn icon flat @click="openGoogleLoginPage">
              <img width="45" height="45" src="@/assets/google_login_icon.png" />
            </v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useNaverLogin } from '@/composable/useNaverLogin'
import { useKakaoLogin } from '@/composable/useKakaoLogin'
import { useGoogleLogin } from '@/composable/useGoogleLogin'

const { openNaverLoginPage } = useNaverLogin()
const { openKakaoLoginPage } = useKakaoLogin()
const { openGoogleLoginPage } = useGoogleLogin()

const dialog = ref(false)
</script>

<style></style>
