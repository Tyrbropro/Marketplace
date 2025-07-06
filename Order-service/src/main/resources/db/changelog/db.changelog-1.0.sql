--liquibase formatted sql

--changeset order:1
CREATE TABLE IF NOT EXISTS orders
(
    id bigserial primary key,
    title varchar(50),
    description text,
    status varchar(50),
    customer_id integer,
    executor_id integer,
    created_at timestamp
);

ALTER TABLE orders ADD FOREIGN KEY (customer_id) REFERENCES users(id);
ALTER TABLE orders ADD FOREIGN KEY (executor_id) REFERENCES users(id);
