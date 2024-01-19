export interface User {
    email: string,
    loginType: LoginType,
    naverLoginInfo?: NaverLoginInfo
}

export interface NaverLoginInfo {
    code: string,
    accessToken: string,
    refreshToken: string
}

export enum LoginType {
    NAVER
}