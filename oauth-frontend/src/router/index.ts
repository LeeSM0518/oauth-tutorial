import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: import("@/pages/Home.vue")
        },
        {
            path: '/naver/login',
            name: 'naver',
            component: import("@/pages/NaverLogin.vue")
        }
    ]
})

export default router
