import type { OauthType } from "@/controller/auth-controller"

export interface OauthInfo {
  oauthId: string
  oauthType: OauthType
}
