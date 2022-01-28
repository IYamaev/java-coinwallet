CREATE TABLE coins
(
    id      BIGSERIAL PRIMARY KEY,
    name    TEXT        NOT NULL,
    ticker  TEXT        NOT NULL UNIQUE,
    price   BIGINT      NOT NULL CHECK ( price >= 0 ),
    qty     BIGINT      NOT NULL CHECK ( qty >= 0 ),
    image   TEXT        NOT NULL,
    removed BOOL        NOT NULL DEFAULT FALSE,
    created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE sales
(
    id      BIGSERIAL PRIMARY KEY,
    coin_id BIGINT      NOT NULL REFERENCES coins,
    name    TEXT        NOT NULL,
    ticker  TEXT        NOT NULL,
    price   BIGINT      NOT NULL CHECK ( price >= 0 ),
    qty     BIGINT      NOT NULL CHECK ( qty >= 0 ) DEFAULT 1,
    type    TEXT        NOT NULL CHECK ( type IN ('sale', 'buy') ),
    removed BOOLEAN     NOT NULL                    DEFAULT FALSE,
    created timestamptz NOT NULL                    DEFAULT CURRENT_TIMESTAMP
);