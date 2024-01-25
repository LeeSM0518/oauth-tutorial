import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: import('@/pages/Home.vue')
    },
    {
      path: '/login/naver',
      name: 'naver',
      component: import('@/pages/NaverLogin.vue')
    },
    {
      path: '/login/kakao',
      name: 'kakao',
      component: import('@/pages/KakaoLogin.vue')
    },
    {
      path: '/signup',
      name: 'signup',
      component: import('@/pages/SignUp.vue')
    }
  ]
})

export default router
