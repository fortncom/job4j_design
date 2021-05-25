create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('phone', 3000);
insert into devices(name, price) values('tablet', 4500);
insert into devices(name, price) values('pc', 8500);


insert into people(name) values('Tom');
insert into people(name) values('Frank');
insert into people(name) values('Helena');


insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(1, 3);

insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(2, 3);

insert into devices_people(device_id, people_id) values(3, 1);
insert into devices_people(device_id, people_id) values(3, 2);


select d2.name, avg(d2.price) 
from devices_people as d1 join devices as d2 
on d1.device_id = d2.id 
group by d2.name;

select p.name, avg(d2.price) 
from devices_people as d join people p 
on d.people_id = p.id join devices as d2
on d.device_id = d2.id
group by p.name 
having avg(d2.price) > 5000;


