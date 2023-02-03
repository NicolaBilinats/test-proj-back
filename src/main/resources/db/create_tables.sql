create table user_info
(
    id       uuid         not null,
    username varchar(255) not null,
    constraint user_pk
        primary key (id)
);

create table questions
(
    id            SERIAL PRIMARY KEY,
    text          VARCHAR(255) NOT NULL,
    type ENUM ('FREE_TEXT', 'CHOICE')
);

create table options_answer
(
    id          SERIAL PRIMARY KEY,
    question_id INTEGER      NOT NULL REFERENCES questions (id),
    text        VARCHAR(255) NOT NULL,
    is_correct  BOOLEAN NOT NULL
);

create table free_answer
(
    id          SERIAL PRIMARY KEY,
    question_id INTEGER      NOT NULL REFERENCES questions (id),
    answer        VARCHAR(255) NOT NULL
);

create table answer_log
(
    id          SERIAL PRIMARY KEY,
    user_id     uuid    NOT NULL REFERENCES user_info (id),
    question_id INTEGER NOT NULL REFERENCES questions (id),
    option_id   INTEGER REFERENCES options_answer (id),
    free_answer VARCHAR(255)
);