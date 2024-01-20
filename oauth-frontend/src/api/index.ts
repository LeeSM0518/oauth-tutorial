import axios, { Axios, AxiosHeaders } from "axios";

export const BASE_URL = import.meta.env.VITE_BASE_URL

export const createApi = (url: string, header?: {Authorization: string}) => axios.create({
    baseURL: `${BASE_URL}${url}`,
    headers: header
})