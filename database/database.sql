create database if not exists test;
use test;
create table users
(
    id   int primary key auto_increment,
    name varchar(255)
);
insert into users(name)
values ('Kalle'),
       ('Olle');

select * from users;
select id,name from users;
