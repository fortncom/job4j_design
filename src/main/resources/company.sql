create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);


insert into departments(name) values('Администрация');
insert into departments(name) values('Отдел кадров');
insert into departments(name) values('Отдел разработки');
insert into departments(name) values('Финансовый отдел');

insert into employees(name, department_id) values('Директор', 1);
insert into employees(name, department_id) values('Специалист по кадрам', 2);
insert into employees(name, department_id) values('Инженер 1 кат.', 3);
insert into employees(name, department_id) values('Экономист', 4);
insert into employees(name, department_id) values('Экономист', null);

select * from employees e left join departments d on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;
select * from employees e cross join departments d;

select * from employees e left join departments d on e.department_id = d.id where d.id is null;

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;


create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(20)
);

insert into teens(name, gender) values('Tom', 'Male');
insert into teens(name, gender) values('Frank', 'Transgender');
insert into teens(name, gender) values('Moly', 'Female');
insert into teens(name, gender) values('Helena', 'Butch');

select t1 as user1, t2 as user2, t1.name || ' - ' || t2.name as pair
from teens t1 cross join teens t2;

