CREATE TABLE titles
(
    id       BIGSERIAL PRIMARY KEY,
    set_name TEXT NOT NULL
);

CREATE TABLE flashCards
(
    id               BIGSERIAL PRIMARY KEY,
    id_card          BIGINT  NOT NULL REFERENCES titles ON DELETE CASCADE,
    question         TEXT    NOT NULL,
    answer           TEXT    NOT NULL,
    status_knowledge BOOLEAN NOT NULL
);