create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values ('Bread', 10),
       ('Milk', 20),
       ('Cheese', 30),
       ('Bear', 40),
       ('Horse', 500),
       ('Pen', 600),
       ('Cheese5', 325),
       ('Cheese6', 326),
       ('Cheese7', 327),
       ('Cheese8', 328),
       ('Cheese9', 328),
       ('Cheese10', 328),
       ('Cheese11', 328),
       ('Cheese12', 328),
       ('Cheese13', 328),
       ('Cheese14', 328),
       ('Cheese15', 328);