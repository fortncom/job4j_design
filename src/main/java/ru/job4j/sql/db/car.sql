create database car;

create table engines(
    id serial primary key,
    name varchar(255)
);

insert into engines(name) values('diesel');
insert into engines(name) values('petrol');
insert into engines(name) values('steam');

create table bodies(
    id serial primary key,
    name varchar(255)
);

insert into bodies(name) values('sedan');
insert into bodies(name) values('Hatchback');
insert into bodies(name) values('Cabriolet');

create table transmissions(
    id serial primary key,
    name varchar(255)
);

insert into transmissions(name) values('mechanical');
insert into transmissions(name) values('automatic');
insert into transmissions(name) values('robotic');

create table wheels(
    id serial primary key,
    radius int
);

insert into wheels(radius) values(13);
insert into wheels(radius) values(15);
insert into wheels(radius) values(17);

create table fuel_tanks(
    id serial primary key,
    volume int
);

insert into fuel_tanks(volume) values(40);
insert into fuel_tanks(volume) values(55);
insert into fuel_tanks(volume) values(70);

create table navigators(
    id serial primary key,
    name varchar(255)
);

insert into navigators(name) values('garmin');
insert into navigators(name) values('navitel');
insert into navigators(name) values('prestigio');

create table passports(
    id serial primary key,
    release_date timestamp,
    weight float
);

insert into passports(release_date, weight) values(date '2020-09-19', 1070);
insert into passports(release_date, weight) values(date '2020-12-10', 1320);
insert into passports(release_date, weight) values(date '2021-02-024', 756);

create table car(
    id serial primary key,
    name varchar(255),
    navigator_id int references navigators(id),
    fuel_tank_id int references fuel_tanks(id),
    wheel_id int references wheels(id),
    transmission_id int references transmissions(id),
    body_id int references bodies(id),
    engine_id int references engines(id),
    passport_id int references passports(id)
);

insert into car(name, navigator_id, fuel_tank_id, wheel_id,
transmission_id, body_id, engine_id, passport_id) values('Polo', 1, 2, 3, 1, 1, 1, 1);

insert into car(name, navigator_id, fuel_tank_id, wheel_id,
transmission_id, body_id, engine_id, passport_id) values('Focus', null, 3, 2, 3, 2, 3, null);

insert into car(name, navigator_id, fuel_tank_id, wheel_id,
transmission_id, body_id, engine_id, passport_id) values('Octavia', 3, 1, 2, 2, 1, 2, 1);

insert into car(name, navigator_id, fuel_tank_id, wheel_id,
transmission_id, body_id, engine_id, passport_id) values('Golf', null, 2, 2, 1, 2, 2, 3);

insert into car(name, navigator_id, fuel_tank_id, wheel_id,
transmission_id, body_id, engine_id, passport_id) values('Rapid', 2, 1, 1, 1, 3, 1, 2);