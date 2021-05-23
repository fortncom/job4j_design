create table role(
	id serial primary key,
	name varchar (255)
);

create table rules(
	id serial primary key,
	name varchar (255)
);

create table role_rules(
	id serial primary key,
	role_id  int references role(id),
	rules_id int references rules(id)
);

create table users(
	id serial primary key,
	name varchar (255),
	role_rules_id int references role_rules(id)
);

create table comments(
	id serial primary key,
	name text
);

create table attachs(
	id serial primary key,
	name varchar (255)
);

create table category(
	id serial primary key,
	name varchar (255)
);

create table state(
	id serial primary key,
	name varchar (255)
);

create table item(
	id serial primary key,
	users_id int references users(id),
	comments_id int references comments(id),
	attachs_id  int references attachs(id),
	category_id int references category(id),
	state_id    int references state(id)
);
