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
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;
