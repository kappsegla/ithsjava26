-- Skapa databas test01
create database test01;
use test01;
-- Skapa tabell för person med id, namn, pnr
create table person
(
    id   int primary key auto_increment,
    name varchar(255),
    pnr  varchar(13) unique not null
);
-- Lägg till en person
insert into person(name, pnr)
values ('Östen Rundqvist', '971022-4636'),
       ('Gottfrid Lönnberg', '080929-6957');
-- Skapa tabell för hund med id, namn och ägare
create table dog
(
    id       int primary key auto_increment,
    name     varchar(255),
    owner_id int,
    foreign key (owner_id) references person (id)
);
-- Lägg till en hund
insert into dog(name, owner_id)
    value ('Buster', 1);
