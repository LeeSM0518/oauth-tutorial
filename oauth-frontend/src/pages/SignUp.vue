<template>
  <v-row justify="center" :align="'center'">
    <v-card width="400">
      <v-toolbar color="primary" cards dark flat>
        <v-btn icon @click="routeHome">
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <v-card-title class="text-h6 font-weight-regular"> 회원가입 </v-card-title>
      </v-toolbar>
      <v-form ref="formRef" v-model="valid" class="pa-4 pt-6">
        <v-text-field
          v-model="form.email"
          label="이메일"
          required
          disabled
          :rules="[rules.required]"
        ></v-text-field>
        <v-text-field
          v-model="form.nickname"
          label="닉네임"
          required
          :rules="[rules.required]"
        ></v-text-field>
        <v-divider></v-divider>
        <v-card-actions>
          <v-alert v-if="isFail" color="error" :text="signUpFailMessage.message"></v-alert>
          <v-btn :disabled="!form.nickname" @click="signUp"> 완료 </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-row>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { useSignUp } from '@/composable/useSignUp'

const { valid, formRef, form, rules, routeHome, setEmail, signUp, signUpFailMessage, isFail } =
  useSignUp()

onMounted(async () => {
  setEmail()
})
</script>
