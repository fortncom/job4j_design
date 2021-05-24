create table cinema_session(
 id serial primary key,
 name varchar(255),
 age_qualification int,
 price int,
 free_place int
);

create table client(
 id serial primary key,
 name varchar(255),
 age int,
 money int,
 cinema_session_id int references cinema_session(id)
);

insert into cinema_session(name, age_qualification, price) values('Форсаж 9', 12, 350);
insert into cinema_session(name, age_qualification, price, free_place) values('Пила: Спираль', 18, 400, 26);
insert into cinema_session(name, age_qualification, price, free_place) values('Гнев Человеческий', 18, 350, 34);
insert into cinema_session(name, age_qualification, price, free_place) values('Кролик Питер 2', 6, 250, 93);
insert into cinema_session(name, age_qualification, price, free_place) values('Новый порядок', 18, 400, 3);



insert into client(name, age, money, cinema_session_id) values('Tom', 36, 1135, 5);
insert into client(name, age, money, cinema_session_id) values('Bob', 25, 2122, 2);
insert into client(name, age, money, cinema_session_id) values('Moly', 25, 535, 2);
insert into client(name, age, money, cinema_session_id) values('Frenk', 48, 3335, 4);
insert into client(name, age, money, cinema_session_id) values('Helena', 10, 280, 4);



select * from cinema_session join client as c on c.money > 1000;


select c1.name  || '  ' || c1.age_qualification || '+' as "Название фильма", 
c1.price as Цена, c1.free_place, c2.name "Имя покупателя" from cinema_session 
as c1 join client as c2 on c1.free_place > 0 and c2.money >= c1.price;


select c1.name  || '  ' || c1.age_qualification || '+' as "Название фильма", 
c2.age "Возраст покупателя", c2.name "Имя покупателя" from cinema_session 
as c1 join client as c2 on c1.age_qualification < c2.age;