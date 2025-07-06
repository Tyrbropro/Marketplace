--liquibase formatted sql

--changeset user:1
CREATE TABLE IF NOT EXISTS users
(
    id bigserial primary key,
    username varchar(255),
    email varchar(255),
    password varchar(255),
    role varchar(50),
    rating double precision,
    created_at timestamp
);
