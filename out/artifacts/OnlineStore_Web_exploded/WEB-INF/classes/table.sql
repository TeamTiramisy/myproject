CREATE TABLE IF NOT EXISTS users
(
    id        BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(64)  NOT NULL,
    lastname  VARCHAR(64)  NOT NULL,
    email     VARCHAR(64)  NOT NULL UNIQUE,
    password  VARCHAR(124) NOT NULL,
    tel       VARCHAR(64)  NOT NULL,
    address   VARCHAR(255) NOT NULL,
    role      VARCHAR(32)  NOT NULL,
    gender    VARCHAR(32)  NOT NULL,
    blacklist varchar(32)  NOT NULL
);

DROP TABLE orders;

ALTER TABLE technic
    ADD COLUMN image VARCHAR(124);

CREATE TABLE IF NOT EXISTS technic
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    category    VARCHAR(64)        NOT NULL,
    description VARCHAR(255)       NOT NULL,
    price       INT                NOT NULL,
    amount      INT                NOT NULL,
    image       VARCHAR(124)       NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id                BIGSERIAL PRIMARY KEY,
    product           VARCHAR(512)                 NOT NULL,
    user_id           BIGINT REFERENCES users (id) NOT NULL,
    date_registration DATE                    NOT NULL,
    date_close        DATE,
    status            VARCHAR(32)                  NOT NULL,
    total             INT                          NOT NULL
);