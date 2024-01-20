import type { UUID } from "crypto";

export interface Member {
    id: UUID,
    email: string,
    nickname: string,
}
