select *
from actor;

select actor.first_name, actor.last_name
from actor;

-- Don't start a like with %. Will not use index.
select *
from actor
where last_name like 'JO%';

select *
from actor
limit 10;

start transaction read only;
rollback;
delete
from rental
where rental_id = 11;
insert into actor(first_name, last_name) value ('KALLE', 'ANKA');
update actor
set first_name='KALLE'
where actor_id = 2;

select *
from actor
order by actor_id asc;

select sum(amount)
from payment;
select sum(first_name)
from actor;

select first_name, count(first_name)
from actor
group by first_name;
select distinct first_name
from actor;

-- Find unique first_names with more than 1 occurrence
select first_name, count(first_name)
from actor
group by first_name
having count(first_name) > 1;

-- Find the number of unique first_names with more than 1 occurrence
-- subquery
select count(*)
from (select first_name, count(first_name)
      from actor
      group by first_name
      having count(first_name) > 1) as subquery;

-- with
with aggregated_data as (select first_name, count(first_name)
                         from actor
                         group by first_name
                         having count(first_name) > 1)
select count(*)
from aggregated_data;
delete from rental where rental_id = 31;

select * from rental limit 10;  -- 12-21
select * from rental limit 10 offset 10; -- 22-31
select * from rental where rental_id > 21 limit 10;
select * from rental where rental_id > 31 limit 10;
