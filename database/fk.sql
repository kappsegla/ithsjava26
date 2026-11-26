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
    data     json check( json_schema_valid('{
      "$schema": "https://json-schema.org/draft/2020-12/schema",
      "type": "object",
      "properties": {
        "color": {
          "type": "string"
        },
        "rase": {
          "type": "string"
        },
        "age": {
          "type": "integer"
        },
        "chipped": {
          "type": "boolean"
        }
      },
      "required": [
        "age",
        "chipped"
      ],
      "additionalProperties": false
    }', data)),
    foreign key (owner_id) references person (id)
);
-- Lägg till en hund
insert into dog(name, owner_id, data)
    value ('Buster', 1,
           '{
             "color": "black",
             "rase": "labrador",
             "age": 7,
             "chipped": true
           }');
insert into dog(name, owner_id, data)
    value ('Maja', 2,
           '{
             "age": 2,
             "chipped": false
           }');
--
select * from dog
where data->'$.chipped' = false;

select * from dog
where data->'$.color' = 'black';

select * from dog
where json_contains_path(data,'one', '$.color') = 1;

update dog set data = json_set(data, '$.chipped', true)
where id = 2;

select * from dog;

set @schema = '{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "type": "object",
  "properties": {
    "color": {
      "type": "string"
    },
    "rase": {
      "type": "string"
    },
    "age": {
      "type": "integer"
    },
    "chipped": {
      "type": "boolean"
    }
  },
  "required": [
    "age",
    "chipped"
  ]
}';

show tables;
describe dog;
show create table dog;
