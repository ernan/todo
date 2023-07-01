DROP TABLE IF EXISTS todo;
CREATE TABLE todo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	title TEXT NOT NULL,
	status TEXT NOT NULL DEFAULT 'New',
	description TEXT NULL,
	completed INTEGER NOT NULL DEFAULT 0,
	start TEXT NULL,
	end TEXT NULL,
	schedule TEXT NULL,
	username TEXT NULL,
	important INTEGER NOT NULL DEFAULT 0,
	created TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS todo_history;
CREATE TABLE todo_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    todo_id INTEGER,
	title TEXT NOT NULL,
	status TEXT NOT NULL,
	description TEXT NULL,
	completed INTEGER NULL,
	start TEXT NULL,
	end TEXT NULL,
	schedule TEXT NULL,
	username TEXT NULL,
	important INTEGER NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS todo_trigger;
CREATE TRIGGER todo_trigger
   AFTER UPDATE ON todo
BEGIN
	INSERT INTO todo_history (
			todo_id,
			title,
			status,
			description,
			completed,
			start,
			end,
			schedule,
			username,
			important,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.title,
			new.status,
			new.description,
			new.completed,
			new.start,
			new.end,
			new.schedule,
			new.username,
			new.important,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

CREATE INDEX index_todo_title ON todo(title);

DROP TABLE IF EXISTS setting;
CREATE TABLE setting (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	section TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT NOT NULL,
	description TEXT NULL,
	seq INTEGER NULL,
	username TEXT NULL,
	created TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS setting_history;
CREATE TABLE setting_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    setting_id INTEGER,
	section TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT NOT NULL,
	description TEXT NULL,
	seq INTEGER NULL,
	username TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS setting_trigger;
CREATE TRIGGER setting_trigger
   AFTER UPDATE ON setting
BEGIN
	INSERT INTO setting_history (
			setting_id,
			section,
			name,
			value,
			description,
			seq,
			username,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.section,
			new.name,
			new.value,
			new.description,
			new.seq,
			new.username,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS attribute;
CREATE TABLE attribute (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	objectId INTEGER NOT NULL,
	objectType TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT NOT NULL,
	description TEXT NULL,
	seq INTEGER NULL,
	username TEXT NULL,
	created TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS attribute_history;
CREATE TABLE attribute_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    attribute_id INTEGER,
	section TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT NOT NULL,
	description TEXT NULL,
	seq INTEGER NULL,
	username TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS attribute_trigger;
CREATE TRIGGER attribute_trigger
   AFTER UPDATE ON attribute
BEGIN
	INSERT INTO attribute_history (
			attribute_id,
			section,
			name,
			value,
			description,
			seq,
			username,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.section,
			new.name,
			new.value,
			new.description,
			new.seq,
			new.username,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS session;
CREATE TABLE session (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	objectId INTEGER NOT NULL,
	objectType TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT NOT NULL,
	description TEXT NULL,
	source TEXT NULL,
	username TEXT NULL,
	expiry TEXT NULL,
	created TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS session_history;
CREATE TABLE session_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    session_id INTEGER,
	section TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT NOT NULL,
	description TEXT NULL,
	source TEXT NULL,
	username TEXT NULL,
	expiry TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS session_trigger;
CREATE TRIGGER session_trigger
   AFTER UPDATE ON session
BEGIN
	INSERT INTO session_history (
			session_id,
			section,
			name,
			value,
			description,
			source,
			username,
			expiry,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.section,
			new.name,
			new.value,
			new.description,
			new.source,
			new.username,
			new.expiry,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS list;
CREATE TABLE list (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	username TEXT NULL,
	created TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS list_history;
CREATE TABLE list_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    list_id INTEGER,
	name TEXT NOT NULL,
	username TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS list_trigger;
CREATE TRIGGER list_trigger
   AFTER UPDATE ON list
BEGIN
	INSERT INTO list_history (
			list_id,
			name,
			username,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.name,
			new.username,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;


DROP TABLE IF EXISTS list_item;
CREATE TABLE list_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	list_id INTEGER NOT NULL,
	todo_id INTEGER NOT NULL,
	username TEXT NULL,
	created TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS list_item_history;
CREATE TABLE list_item_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    list_item_id INTEGER,
	list_id INTEGER NOT NULL,
	todo_id INTEGER NOT NULL,
	username TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS list_item_trigger;
CREATE TRIGGER list_item_trigger
   AFTER UPDATE ON list_item
BEGIN
	INSERT INTO list_item_history (
			list_item_id,
			list_id,
			todo_id,
			username,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.list_id,
			new.todo_id,
			new.username,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS uploaded_file;
create table uploaded_file (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    uploaded_path TEXT NULL,
    file_size INTEGER NULL,
    uploaded_date TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_name TEXT NULL,
    file_type TEXT NOT NULL,
    data BLOB NULL
);
