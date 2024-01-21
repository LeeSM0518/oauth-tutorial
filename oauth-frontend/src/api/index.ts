import axios, { AxiosHeaders } from "axios";

export const BASE_URL = import.meta.env.VITE_BASE_URL

export interface HttpHeader extends AxiosHeaders { Authorization: string }

export const createApi = (url: string, header?: HttpHeader) => axios.create({
    baseURL: `${BASE_URL}${url}`,
    headers: header
})