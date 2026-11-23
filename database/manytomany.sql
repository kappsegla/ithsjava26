use bookstore;
drop table book;
drop table author;
create table author
(
    id         int primary key auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    birth_date date
);
create table book
(
    id           int primary key auto_increment,
    isbn         varchar(13) unique,
    title        varchar(255),
    release_date date
);
create table book_author
(
    book_id   int,
    author_id int,
    foreign key (book_id)
        references book (id),
    foreign key (author_id)
        references author (id),
    primary key (book_id, author_id)
);
insert into author(first_name, last_name, birth_date)
values ('Kalle', 'Anka', '2017-12-12'),
       ('Sting', 'Gyllenkrok', '1960-02-13'),
       ('Lille', 'Skutt', '1980-01-01');
insert into book(isbn, title, release_date)
values ('1234567890123', 'Test', '2026-01-17'),
       ('9234567890124', 'Test 2', '2026-07-11');
insert into book_author(book_id, author_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (2, 3);
-- Retrieve authors for the book with title Test
select author.first_name, author.last_name
from author
         join book_author on author.id = book_author.author_id
         join book on book_author.book_id = book.id
where book.title = 'Test';

-- Above query step by step with select, no joins
select *
from book
where book.title = 'Test';
select *
from book_author
where book_id = 1;
select *
from author
where id in (1, 2);
