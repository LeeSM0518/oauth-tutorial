CREATE TABLE IF NOT EXISTS member
(
    member_id       uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    nickname        VARCHAR UNIQUE NOT NULL,
    registered_date TIMESTAMP      NOT NULL,
    modified_date   TIMESTAMP      NOT NULL
);

CREATE TABLE IF NOT EXISTS refresh_token
(
    refresh_token_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    member_id        uuid UNIQUE    NOT NULL,
    token            VARCHAR UNIQUE NOT NULL,
    registered_date  TIMESTAMP      NOT NULL,
    modified_date    TIMESTAMP      NOT NULL,
    CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES member (member_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS oauth_info
(
    oauth_id        VARCHAR PRIMARY KEY,
    oauth_type      VARCHAR     NOT NULL,
    member_id       uuid UNIQUE NOT NULL,
    registered_date TIMESTAMP   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES member (member_id) ON DELETE CASCADE ON UPDATE CASCADE
)