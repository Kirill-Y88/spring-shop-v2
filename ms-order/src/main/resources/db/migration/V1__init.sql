create table orders
(
    id         bigserial primary key,
    user_id     bigint,
    price       int,
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
    price_per_product       int,
    price           int,
    quantity        int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

insert into order_items (order_id, product_id, product_title, price_per_product, quantity, price)
values (1, 1, 'Bread', 10, 8, 80),
       (1, 2, 'Milk', 20, 1, 20),
       (2, 3, 'Cheese', 30, 5, 150),
       (3, 4, 'Bear', 40, 4, 160),
        (3, 1, 'Bread', 10, 1, 10),
        (3, 3, 'Cheese', 30, 1, 30),
        (4, 5, 'Horse', 500, 1, 500);

create table carts
(
    id         UUID primary key,
    user_id     bigint,
    price       int
);

create table cart_items
(
    id         bigserial primary key,
    cart_id        UUID,
    product_id     bigint,
    product_title  varchar(255),
    price_per_product       int,
    price           int,
    quantity        int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    FOREIGN KEY (cart_id) REFERENCES carts(id)
);