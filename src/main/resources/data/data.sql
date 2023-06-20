insert into todo(title, description, status) VALUES ('Buy bread', 'Shopping', 'New');
insert into todo(title, description, status) VALUES ('Buy milk', 'Shopping', 'New');
insert into todo(title, description, status) VALUES ('Buy tea', 'Shopping', 'New');
insert into todo(title, description, status, important) VALUES ('Buy pants', 'Shopping', 'New', 1);
insert into todo(title, description, status, important) VALUES ('Buy pens', 'Shopping', 'New', 1);
insert into todo(title, description, status, important) VALUES ('Buy jotter', 'Shopping', 'New', 1);
insert into todo(title, description, status, completed) VALUES ('Buy pencil', 'Shopping', 'New', 1);
insert into todo(title, description, status, completed) VALUES ('Buy pens today', 'Shopping', 'New', 1);
insert into todo(title, description, status, start) VALUES ('Buy jotter today', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Buy pencil today', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Buy pencil today', 'Shopping', 'New', strftime('%Y-%m-%d','now'));
insert into todo(title, description, status, start) VALUES ('Buy pencil today', 'Shopping', 'New', strftime('%Y-%m-%d','now'));



insert into setting(section, name, value) VALUES ('default', 'Theme', 'Light');
insert into setting(section, name, value) VALUES ('default', 'View Completed', 'False');