show databases;
create database if not exists bookstore;
use bookstore;
create table company
(
    id         int primary key auto_increment,
    name       varchar(50) check ( length(name) > 0),
    created_at datetime default now()
);
show tables;
drop table company;
start transaction;
insert into company(name)
    value ('Test');
commit;
select *
from company;
select *
from company
where name is null;

create table author
(
    id         int primary key auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    birth_date date
);
insert into author(first_name, last_name, birth_date)
values ('Kalle', 'Anka', '2001-01-01'),
       ('Stina', 'Gyllenkrok', '1960-12-11');
select *
from author;
create table book
(
    id           int primary key auto_increment,
    isbn         varchar(13) unique,
    title        varchar(255),
    release_date date,
    author_id    int,
    foreign key (author_id) references author (id)
);
insert into book(isbn, title, release_date, author_id)
values ('9789189942745', 'Ropet från rymden', '2026-10-07', 3),
       ('9789153110170', 'Bakom nissedörren', '2024-10-24', 4);
select *
from book;
insert into book(isbn, title, release_date, author_id)
    value ('6547890546723', 'Test test test', '2018-05-13', null);

select isbn, title, first_name, last_name
from book
         join author
where book.author_id = author.id;
delete
from author
where id = 3;
delete
from book
where id = 1;
