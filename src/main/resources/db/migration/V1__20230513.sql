CREATE TABLE users
(
    id               SERIAL PRIMARY KEY,
    username         VARCHAR(64)   NOT NULL UNIQUE,
    password         VARCHAR(2048) NOT NULL,
    role             VARCHAR(32)   NOT NULL,
    first_name       VARCHAR(64)   NOT NULL,
    last_name        VARCHAR(64)   NOT NULL,
    enabled          BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    question_list_id INTEGER
);

CREATE TABLE question_list
(
    id SERIAL PRIMARY KEY
);

CREATE TABLE questions
(
    id       SERIAL PRIMARY KEY,
    list_id  INTEGER,
    question VARCHAR(128) NOT NULL
);

CREATE TABLE variants
(
    id       SERIAL PRIMARY KEY,
    question_id  INTEGER      NOT NULL,
    variant VARCHAR(64) NOT NULL
);

CREATE TABLE answers
(
    id       SERIAL PRIMARY KEY,
    question_id  INTEGER      NOT NULL,
    answer VARCHAR(64) NOT NULL
);
