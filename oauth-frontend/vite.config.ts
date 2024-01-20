import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      "/oauth/naver/login": {
        target: "https://nid.naver.com",
        changeOrigin: true,
        rewrite: path => path.replace(/^\/oauth\/naver\/login/, "/oauth2.0")
      },
      "/oauth/naver/user": {
        target: "https://openapi.naver.com",
        changeOrigin: true,
        rewrite: path => path.replace(/^\/oauth\/naver\/user/, "/v1/nid/me")
      }
    }
  }
})
