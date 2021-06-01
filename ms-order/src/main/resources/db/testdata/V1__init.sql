create table orders
(
    id         bigserial primary key,
    user_id     bigint,
    price       bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into orders (user_id, price)
values (1, 100),
       (1, 150),
       (2, 200),
       (3, 500);

create table order_items
(
    id         bigserial primary key,
    order_id        bigint,
    product_id     bigint,
    product_title  varchar(255),
    price           bigint,
    quantity        bigint,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

insert into order_items (order_id, product_id, product_title, price, quantity)
values (1, 1, 'Bread', 10, 8),
       (1, 2, 'Milk', 20, 1),
       (2, 3, 'Cheese', 30, 5),
       (3, 4, 'Bear', 40, 4),
        (3, 1, 'Bread', 10, 1),
        (3, 3, 'Cheese', 30, 1),
        (4, 5, 'Horse', 500, 1);