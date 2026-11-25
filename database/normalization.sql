create database norm;
use norm;
create table kurs_oversikt (
                               kurs_id varchar(20) primary key,
                               kursnamn varchar(120) not null,
                               larare_1 varchar(100),
                               larare_2 varchar(100),
                               taggar varchar(255),
                               anmalda_studenter text
);

insert into kurs_oversikt (
    kurs_id,
    kursnamn,
    larare_1,
    larare_2,
    taggar,
    anmalda_studenter
) values (
             'sys101',
             'systemarkitektur',
             'anna lind',
             'erik holm',
             'java,ddd,cloud',
             'kalle (2026-09-01), maja (2026-09-02)'
         );
-- Normalized version
create table kurs (
                        kurs_id varchar(20) primary key,
                        kursnamn varchar(120) not null
);

create table larare (
                        larare_id int primary key auto_increment,
                        namn varchar(100) not null
);

create table kurs_larare (
                             kurs_id varchar(20) not null,
                             larare_id int not null,
                             primary key (kurs_id, larare_id),
                             constraint fk_kurs_larare_kurs foreign key (kurs_id) references kurs (kurs_id),
                             constraint fk_kurs_larare_larare foreign key (larare_id) references larare (larare_id)
);

create table tagg (
                        tagg_id int primary key auto_increment,
                        namn varchar(50) not null unique
);

create table kurs_tagg (
                             kurs_id varchar(20) not null,
                             tagg_id int not null,
                             primary key (kurs_id, tagg_id),
                             constraint fk_kurs_tagg_kurs foreign key (kurs_id) references kurs (kurs_id),
                             constraint fk_kurs_tagg_tagg foreign key (tagg_id) references tagg (tagg_id)
);

create table student (
                           student_id int auto_increment primary key,
                           namn varchar(100) not null
);

create table anmalning (
                             kurs_id varchar(20) not null,
                             student_id int not null,
                             anmald_datum date not null,
                             primary key (kurs_id, student_id),
                             constraint fk_anmalningar_kurs foreign key (kurs_id) references kurs (kurs_id),
                             constraint fk_anmalningar_student foreign key (student_id) references student (student_id)
);

-- Anomalies when not properly normalized
create table student_course(
    studentId int,
    name varchar(255),
    course varchar(255)
);

insert into student_course(studentId, name, course) value (1,'Anna','Database');
insert into student_course(studentId, name, course) value (1,'Anna','Java');

select * from student_course;
-- Anomaly update, multiple rows need to be updated but we might miss some
update student_course
set name = 'Anna Svensson'
where studentId = 1;

-- Insert anomaly  no course name saved with the student
insert into student_course(studentId, name) value(2, 'Erik');

-- Delete anomaly  removes the course name also
delete from student_course where name = 'Anna';
