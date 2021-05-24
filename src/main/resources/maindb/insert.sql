insert into role (name) values('client');
insert into rules(name) values('common');
insert into role_rules(role_id, rules_id) values(1, 1);

insert into state(name) values('processing');
insert into category(name) values('software');
insert into users(name, role_id) values('Tom', 1);
insert into item(users_id,category_id, state_id) values(1, 1, 1);

insert into comments(name, item_id) values('installation MS Office', 1);
insert into attachs(name, item_id) values('permission', 1);
