insert into role (name) values('client');
insert into rules(name) values('common');
insert into role_rules(role_id, rules_id) values(1, 1);

insert into comments(name) values('installation MS Office');
insert into attachs(name) values('permission');
insert into category(name) values('software');
insert into state(name) values('processing');

insert into users(name, role_rules_id) values('Tom', 1);
insert into item(users_id, comments_id, attachs_id, category_id, state_id) values(1, 1, 1, 1, 1);
