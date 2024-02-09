# oauth-tutorial
네이버/카카오/구글 로그인을 연동한 튜토리얼 프로젝트입니다.

<br/>

# 기술 스택
## 백엔드
![kotlin](https://img.shields.io/badge/kotlin-white?logo=kotlin)
![spring](https://img.shields.io/badge/spring-white?logo=spring)
![postgres](https://img.shields.io/badge/postgres-white?logo=postgresql)

## 프론트엔드
![typescript](https://img.shields.io/badge/typescript-white?logo=typescript)
![vue](https://img.shields.io/badge/vue-white?logo=vue.js)
![pnpm](https://img.shields.io/badge/pnpm-white?logo=pnpm)

<br/>

# 실행 방법

1. 프로젝트 클론
    
    ```bash
    $ git clone git@github.com:LeeSM0518/oauth-tutorial.git
    $ cd oauth-tutorial
    ```
    
2. 백엔드 환경구성 및 실행
    1. oauth-tutorial/oauth-backend/src/main/resource 경로의 application.sample.yaml 파일을 복사하여 `application.yaml` 파일로 생성
        
        ```bash
        $ cp oauth-backend/src/main/resources/application.sample.yaml \
        oauth-backend/src/main/resources/application.yaml
        ```
        
    2. `application.yaml` 파일의 비어있는 값 할당
        
        ```bash
        $ vi oauth-backend/src/main/resources/application.yaml
        
        ...
        
        naver:
          ...
          clientId: {Naver Client ID}
          clientSecret: {Naver Client Secret}
        
        kakao:
          ...
          clientId: {Kakao Client ID}
          ...
        
        google:
          ...
          clientId: {Google Client ID}
          clientSecret: {Google Client Secret}
          ...
        ```
        
        - [네이버 정보 할당시 참고 링크](https://github.com/LeeSM0518/oauth-tutorial/tree/main#%EB%84%A4%EC%9D%B4%EB%B2%84-%EB%A1%9C%EA%B7%B8%EC%9D%B8-api-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95)
        - [카카오 정보 할당시 참고 링크](https://github.com/LeeSM0518/oauth-tutorial/tree/main#%EC%B9%B4%EC%B9%B4%EC%98%A4-%EB%A1%9C%EA%B7%B8%EC%9D%B8-api-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95)
        - [구글 정보 할당시 참고 링크](https://github.com/LeeSM0518/oauth-tutorial/tree/main#%EA%B5%AC%EA%B8%80-%EB%A1%9C%EA%B7%B8%EC%9D%B8-api-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95)
    3. 백엔드 서버 빌드
        
        ```bash
        $ cd oauth-backend
        $ ./gradlew build
        ```
        
    4. 백엔드 서버 실행
        
        ```bash
        $ cd docker
        $ docker-compose up -d
        ```
        
3. 프론트엔드 환경구성 및 실행
    1. oauth-tutorial/oauth-frontend 경로의 .env.sample 파일을 복사하여 `.env` 파일로 생성
        
        ```bash
        $ cd ../../oauth-frontend
        $ cp .env.sample .env
        ```
        
    2. `.env` 파일의 비어있는 값 할당
        
        ```
        $ vi .env
        
        ...
        VITE_NAVER_CLIENT_ID={Naver Client ID}
        ...
        VITE_KAKAO_CLIENT_ID={Kakao Client ID}
        ...
        VITE_GOOGLE_CLIENT_ID={Google Client ID}
        ...
        ```
        
        - 백엔드 환경설정 값 구성시 사용한 값을 그대로 사용
    3. pnpm install 수행
        
        ```bash
        $ pnpm i
        ```
        
    4. 프론트엔드 서버 실행
        
        ```bash
        $ pnpm dev
        ```
        
4. http://localhost:8080 접속

# 네이버 로그인 API 사용 방법
1. [네이버 로그인 API 페이지](https://developers.naver.com/products/login/api/api.md)로 이동
   <img width="1840" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/9efaa1fe-8399-422c-a344-366377636ea5">
2. 네이버 로그인
   <img width="626" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/6ac4d6c8-18ee-4bdf-9670-41ae460b1ba5">
3. [애플리케이션 목록 페이지](https://developers.naver.com/apps/#/list)로 이동
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/b853197c-d0f8-491a-be0c-5a3a741193f6">
4. [애플리케이셔 등록 페이지](https://developers.naver.com/apps/#/register)로 이동
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/fc856f73-07e4-4f6b-8942-0de3f6f5a713">
5. 애플리케이션 등록 수행
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/7614c24e-1127-40b3-a649-b484e6dd5e97">
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/958df688-9cca-4624-a1f1-de22a1ea11ca">
   <img width="1024" alt="스크린샷 2024-02-09 오후 7 57 24" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/86c977d6-7c66-4b28-a429-74d4cf22fd7d">
6. `Client ID` 와 `Client Secret` 을 복사하여 백엔드 및 프론트엔드 환경구성에 사용

<br/>

# 카카오 로그인 API 사용 방법

# 구글 로그인 API 사용 방법
