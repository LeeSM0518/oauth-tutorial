<template>
  <v-row justify="center" :align="'center'">
    <v-card width="400">
      <v-toolbar color="primary" cards dark flat>
        <v-btn icon>
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <v-card-title class="text-h6 font-weight-regular">
          Sign up
        </v-card-title>
      </v-toolbar>
      <v-form v-model="valid">
        <v-text-field
          v-model="form.email"
          :counter="10"
          label="Email"
          required
          hide-details
        ></v-text-field>
      </v-form>
    </v-card>
  </v-row>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useMemberStore } from '@/stores/member'
const { member } = useMemberStore()

interface SignUpForm {
  email: string
  nickname: string
}

const valid = ref(false)
const form: SignUpForm = reactive({
  email: member != null ? member.email : '',
  nickname: ''
})
</script>
