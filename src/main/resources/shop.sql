create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('ХЛЕБ');
insert into type(name) values('ОВОЩИ');

insert into product(name, type_id, expired_date, price) values('Моцарелла', 1, date '2021-06-25', 32.35);
insert into product(name, type_id, expired_date, price) values('Бородинский', 3, date '2021-05-27', 5.15);
insert into product(name, type_id, expired_date, price) values('Пломбир мороженое', 2, date '2021-08-15', 12.2);
insert into product(name, type_id, expired_date, price) values('Помидоры', 4, date '2021-07-01', 10.43);



select * from product p join type as t on p.type_id = t.id and t.name = 'СЫР';

select * from product where name like '%мороженое%';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(t.name) from product p join type t on p.type_id = t.id group by t.name;

select * from product p join type t on p.type_id = t.id and t.name = 'СЫР' or p.type_id = t.id and t.name = 'МОЛОКО';

select t.name  from product p join type t on p.type_id = t.id group by t.name having count(t.id) < 10;

select p.*, t.name as Категория from product p join type as t on p.type_id = t.id;