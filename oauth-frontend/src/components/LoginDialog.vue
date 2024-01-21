<template>
    <v-row justify="center">
        <v-dialog v-model="dialog" width="256">
            <template v-slot:activator="{ props }">
                <v-btn color="primary" v-bind="props">
                    로그인
                </v-btn>
            </template>
            <v-card>
                <v-card-title>
                    <span class="text-h5">소셜 로그인</span>
                </v-card-title>
                <v-card-text>
                    <v-row justify="center">
                        <v-col>
                            <v-btn icon flat color="success" @click="openNaverLoginPage">
                                <img width="45" height="45" src="@/assets/naver_login_icon.png">
                            </v-btn>
                        </v-col>
                        <v-col>
                            <v-btn icon flat color="success" @click="openNaverLoginPage">
                                <img width="45" height="45" src="@/assets/naver_login_icon.png">
                            </v-btn>
                        </v-col>
                        <v-col>
                            <v-btn icon flat color="success" @click="openNaverLoginPage">
                                <img width="45" height="45" src="@/assets/naver_login_icon.png">
                            </v-btn>
                        </v-col>
                    </v-row>
                </v-card-text>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script setup lang="ts">
import { ref } from "vue"
import { useNaverLogin } from "@/composable/useNaverLogin"

const { openNaverLoginPage } = useNaverLogin()

const dialog = ref(false)
</script>

<style>
</style>