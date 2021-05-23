create table users(
	number serial primary key,
	name varchar (255),
	age integer,
	married boolean
);

insert into users (name, age, married) values('Tom', '35', 'true');
update users set age = 36;
delete from users;