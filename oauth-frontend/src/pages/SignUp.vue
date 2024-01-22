<template>
    <v-row justify="center">
        <v-card width="400" title="회원가입">
            <v-row justify="center">
                <v-form v-model="valid">
                    <v-row>
                        <v-col cols="12" md="4">
                            <v-text-field v-model="form.email" :counter="10" label="Email" required
                                hide-details></v-text-field>
                        </v-col>
                    </v-row>
                </v-form>
            </v-row>
        </v-card>
    </v-row>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';

interface SignUpForm {
    email: string,
    nickname: string
}

const valid = ref(false)
const form: SignUpForm = reactive({
    email: "",
    nickname: ""
})
</script>