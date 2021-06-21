create table meetings(
    id serial primary key,
    name varchar(255)
);

insert into meetings(name) values('discussion');
insert into meetings(name) values('goals');
insert into meetings(name) values('deadline');


create table users(
    id serial primary key,
    name varchar(255)
);

insert into users(name) values('Tom');
insert into users(name) values('Hank');
insert into users(name) values('Helena');


create table meetings_users(
    id serial primary key,
    meeting_id int references meetings(id),
    user_id int references users(id),
    accept boolean
);

insert into meetings_users(meeting_id, user_id, accept) values(1, 1, true);
insert into meetings_users(meeting_id, user_id, accept) values(1, 2, false);
insert into meetings_users(meeting_id, user_id, accept) values(1, 3, true);
insert into meetings_users(meeting_id, user_id, accept) values(2, 1, true);
insert into meetings_users(meeting_id, user_id, accept) values(2, 2, true);
insert into meetings_users(meeting_id, user_id, accept) values(2, 3, true);
insert into meetings_users(meeting_id, user_id, accept) values(3, null, null);


select m.name as mitings, count(mu.user_id)
from meetings_users mu join meetings m
on mu.meeting_id=m.id
and mu.accept=true
group by m.name;

select m.name as "mitings without accept"
from meetings_users mu join meetings m
on mu.meeting_id=m.id
group by m.name
having count(mu.accept)=0;