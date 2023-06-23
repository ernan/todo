insert into todo(title, description, status) VALUES ('Buy bread', 'Shopping', 'New');
insert into todo(title, description, status) VALUES ('Buy milk', 'Shopping', 'New');
insert into todo(title, description, status) VALUES ('Buy tea', 'Shopping', 'New');
insert into todo(title, description, status, important) VALUES ('Buy pants', 'Shopping', 'New', 1);
insert into todo(title, description, status, important) VALUES ('Buy pens', 'Shopping', 'New', 1);
insert into todo(title, description, status, important) VALUES ('Buy jotter', 'Shopping', 'New', 1);
insert into todo(title, description, status, completed) VALUES ('Buy pencil', 'Shopping', 'New', 1);
insert into todo(title, description, status, completed) VALUES ('Buy pens today', 'Shopping', 'New', 1);
insert into todo(title, description, status, start) VALUES ('Buy jotter today', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Interview', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Dinner', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start, `end`, completed) VALUES ('Exercise', 'Health', 'New', strftime('%Y-%m-%d','now', '-4 day'), strftime('%Y-%m-%d','now', '-3 day'), 1);
insert into todo(title, description, status, start, `end`, completed) VALUES ('Exercise', 'Health', 'New', strftime('%Y-%m-%d','now', '-2 day'), strftime('%Y-%m-%d','now', '-1 day'), 1);
insert into todo(title, description, status, start, completed) VALUES ('Exercise', 'Health', 'New', strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, completed) VALUES ('Yoga', 'Health', 'New', strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, `end`,completed) VALUES ('Yesterday', 'Life', 'New', strftime('%Y-%m-%d','now', '-1 day'), strftime('%Y-%m-%d','now'), 1);
insert into todo(title, description, status, start, completed) VALUES ('Tomorrow', 'Life', 'New', strftime('%Y-%m-%d','now', '+1 day'), 0);
insert into todo(title, description, status, start, completed) VALUES ('Start New Course', 'Life', 'New', strftime('%Y-%m-%d','now', '+100 day'), 0);
insert into todo(title, description, status, start, completed) VALUES ('Move House', 'Life', 'New', strftime('%Y-%m-%d','now', '+200 day'), 0);

insert into setting(section, name, value) VALUES ('default', 'Theme', 'Light');
insert into setting(section, name, value) VALUES ('default', 'View Completed', 'False');
insert into setting(section, name, value) VALUES ('ui', 'selectedColor', 'blue');
insert into setting(section, name, value) VALUES ('ui', 'completedColor', 'grey');

insert into list(name) VALUES ('Goals 2023');
insert into list(name) VALUES ('Goals 2022');
insert into list(name) VALUES ('Goals 2021');
insert into list(name) VALUES ('Goals 2020');

INSERT INTO list_item (list_id, todo_id) VALUES (1,1);
INSERT INTO list_item (list_id, todo_id) VALUES (1,2);
INSERT INTO list_item (list_id, todo_id) VALUES (1,3);
INSERT INTO list_item (list_id, todo_id) VALUES (1,4);
INSERT INTO list_item (list_id, todo_id) VALUES (2,1);
INSERT INTO list_item (list_id, todo_id) VALUES (2,2);
INSERT INTO list_item (list_id, todo_id) VALUES (3,3);
INSERT INTO list_item (list_id, todo_id) VALUES (3,4);
INSERT INTO list_item (list_id, todo_id) VALUES (4,1);
INSERT INTO list_item (list_id, todo_id) VALUES (4,2);
INSERT INTO list_item (list_id, todo_id) VALUES (4,3);
INSERT INTO list_item (list_id, todo_id) VALUES (4,4);


