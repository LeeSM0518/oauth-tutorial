import axios, { Axios, AxiosHeaders } from "axios";

const NAVER_LOGIN_API_HOST = import.meta.env.VITE_NAVER_LOGIN_API_HOST
const NAVER_TOKEN_URL = import.meta.env.VITE_NAVER_TOKEN_URL
const NAVER_USER_URL = import.meta.env.VITE_NAVER_USER_URL
const NAVER_CLIENT_ID = import.meta.env.VITE_NAVER_CLIENT_ID
const NAVER_CLIENT_SECRET = import.meta.env.VITE_NAVER_CLIENT_SECRET
const NAVER_STATE = import.meta.env.VITE_NAVER_STATE

function createNaverApi(url: string, header?: AxiosHeaders): Axios {
    return axios.create({
        baseURL: `${NAVER_LOGIN_API_HOST}${url}`,
        headers: header
    })
}

export class NaverLoginApi {
    private tokenApi: Axios = createNaverApi(NAVER_TOKEN_URL)
    private userApi: Axios = createNaverApi(NAVER_USER_URL)

    async getToken(code: string): Promise<GetTokenResponse> {
        const response: GetTokenResponse =
            await this.tokenApi.get("", {
                params: {
                    grant_type: "authorization_code",
                    client_id: NAVER_CLIENT_ID,
                    client_secret: NAVER_CLIENT_SECRET,
                    code: code,
                    state: NAVER_STATE
                }
            })
        return response
    }
}

export interface GetTokenResponse {
    accessToken: string,
    refreshToken: string,
    tokenType: string,
    expiresIn: number,
    error: string,
    errorDescription: string
}
