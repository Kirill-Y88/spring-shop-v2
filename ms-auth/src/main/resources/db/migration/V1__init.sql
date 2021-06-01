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
insert into user_table values
(1, 'alan', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 1),
(2, 'dilan', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 2);



