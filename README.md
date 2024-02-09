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

<br/>

# 네이버 로그인 API 사용 방법
1. [네이버 로그인 API 페이지](https://developers.naver.com/products/login/api/api.md)로 이동
   <img width="1840" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/9efaa1fe-8399-422c-a344-366377636ea5">
2. 네이버 로그인 수행
   <img width="626" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/6ac4d6c8-18ee-4bdf-9670-41ae460b1ba5">
4. [애플리케이션 목록 페이지](https://developers.naver.com/apps/#/list)로 이동
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/b853197c-d0f8-491a-be0c-5a3a741193f6">
5. [애플리케이셔 등록 페이지](https://developers.naver.com/apps/#/register)로 이동
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/fc856f73-07e4-4f6b-8942-0de3f6f5a713">
6. 애플리케이션 등록 수행
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/7614c24e-1127-40b3-a649-b484e6dd5e97">
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/958df688-9cca-4624-a1f1-de22a1ea11ca">
   <img width="1024" alt="스크린샷 2024-02-09 오후 7 57 24" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/86c977d6-7c66-4b28-a429-74d4cf22fd7d">
7. `Client ID` 와 `Client Secret` 을 복사하여 백엔드 및 프론트엔드 환경구성에 사용

<br/>

# 카카오 로그인 API 사용 방법
1. [카카오 API 페이지](https://developers.kakao.com/)로 이동
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/63c914ed-1c23-4b32-af01-04a1db420cf5">
   
2. 카카오 로그인 수행
   <img width="1840" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/39e12910-38b3-43e9-9875-9aca6d208037">
   
3. [내 애플리케이션으로 페이지](https://developers.kakao.com/console/app)로 이동 <br/>
   <img width="747" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/26534d67-aad9-4857-83e4-fcbe31e847e1">
   
5. 애플리케이션 추가하기 수행 <br/>
   <img width="667" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/67440d1f-3c75-4f65-8ee9-f3312d56d77f">
   
6. 애플리케이션으로 이동 <br/>
   <img width="642" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/c57016dc-84a4-41ef-a11f-ad0b9f1728cb">
   
7. 플랫폼으로 이동 <br/>
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/d873805d-427b-4df6-864d-04aa0246c70b">
   
8. Web 플랫폼 등록 <br/>
   <img width="663" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/a7d5a61d-d70b-46a2-829c-4c5521c9cbd6">
   
10. 카카오 로그인으로 이동 <br/>
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/e7a4d277-6be6-4f36-ade4-1294a31fc8e7">
   
11. 활성화 설정 상태 ON, Redirect URI 등록 <br/>
    <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/4926bf38-2e6b-4af6-8f2b-af296565e8b9">
    
12. 요약 정보로 이동하여 `Client ID` 를 복사한 후 백엔드 및 프론트엔드 환경구성에 사용 <br/>
    <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/b6726ef3-1c65-4845-abeb-33423a290843">

<br/>

# 구글 로그인 API 사용 방법
1. [구글 API 페이지](https://console.cloud.google.com/apis/dashboard)로 이동
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/5a65abfb-b4e0-4874-a37e-04533cf120ca">
   
2. 프로젝트 만들기 수행
   <img width="1024" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/42baea10-895a-4568-a7c1-eaa251337c96">
   <img width="587" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/169a6db5-a18d-4b7b-89da-03ad5909787c">
   
3. OAuth 동의 화면 만들기 수행
   <img width="871" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/f9d2375b-a3be-4bfa-8113-837fbcabe0e4">
   <img width="1024" alt="스크린샷 2024-02-09 오후 8 29 04" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/ee10df86-337d-4303-8481-2b5971e54178">
   <img width="976" alt="스크린샷 2024-02-09 오후 8 29 38" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/b583022f-47b5-479e-8cfd-c206a4e1cce3">
   <img width="932" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/20fb5619-599b-44c8-80ec-f7683fb49728">
   <img width="976" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/3519c371-6913-4d94-b8c0-767daa653395">
   <img width="839" alt="스크린샷 2024-02-09 오후 8 31 55" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/9e96c403-05bf-4b1a-868a-2a0172010037">

4. 사용자 인증 정보 만들기 수행
   <img width="837" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/a25bb69a-7c32-417d-9ad2-de42cef4066c">
   <img width="826" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/68738bd7-3307-4fca-8b24-8cb7914d457b">
   <img width="932" alt="image" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/5aed358f-de38-47b9-b7ec-850e14e15592">

5. `Client ID` 와 `Client Secret` 을 복사하여 백엔드 및 프론트엔드 환경구성에 사용
   <img width="534" alt="스크린샷 2024-02-09 오후 8 37 49" src="https://github.com/LeeSM0518/oauth-tutorial/assets/43431081/d0d3783b-c293-40af-b398-87f22100a4df">
