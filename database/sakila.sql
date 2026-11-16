select *
from actor;

select actor.first_name, actor.last_name
from actor;

-- Don't start a like with %. Will not use index.
select *
from actor
where last_name like 'JO%';

select * from actor limit 10;
