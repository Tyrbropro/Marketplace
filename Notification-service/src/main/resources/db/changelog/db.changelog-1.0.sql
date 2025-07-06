--liquibase formatted sql

--changeset notifications:1

CREATE TABLE IF NOT EXISTS notifications (
    id bigserial primary key,
    user_id integer,
    message text,
    type varchar(10),
    is_read boolean,
    created_at timestamp
);

ALTER TABLE notifications ADD FOREIGN KEY (user_id) REFERENCES users (id);
