create table role_table
(
    id   bigserial      not null
       /* constraint role_table_pk*/
            primary key,
    name varchar(20) not null
);

insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_USER');

create table user_table
(
    id       bigserial not null
       /* constraint user_table_pk*/
            primary key,
    login    varchar(50),
    password varchar(500),
    role_id  bigint,
       /* constraint user_table_role_table_id_fk*/
           foreign key (role_id) references role_table (id)
);
insert into user_table values (1, 'bill', '
$2y$08$QRzBWXqvPBqxET2/RZYJwO2BFT01AYM/LF3EQH5tApH64gnm/f7XW
', 1);

/*create
unique index user_table_login_uindex
    on user_table (login);*/

