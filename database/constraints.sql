create database hotel;

create table booking
(
    id         int auto_increment primary key,
    guests     int check ( guests > 0 and guests <= 20) not null,
    promo_code varchar(20) check ( promo_code is null or regexp_like(promo_code, '^[A-Z0-9]{4,12}$')),
    start_at   date,
    end_at     date

);
drop table booking;

delimiter $$

create trigger trg_booking_start_at_after_today_and_before_end_at
    before insert
    on booking
    for each row
begin
    if new.start_at <= curdate() or new.start_at >= new.end_at then
        signal sqlstate '45000'
            set message_text = 'start_at must be in the future and before end_at';
    end if;
end$$

delimiter ;


insert into booking(guests, promo_code) value (2, null);
insert into booking(guests, promo_code) value (-1, null);
insert into booking(guests, promo_code) value (null, null);
insert into booking(guests, promo_code) value (10, 'Z01P');

insert into booking(guests, start_at, end_at) value (5, '2026-11-26', '2026-11-27');
insert into booking(guests, start_at, end_at) value (5, '2026-11-26', '2026-11-25');
insert into booking(guests, start_at, end_at) value (5, '2026-11-16', '2026-11-25');
