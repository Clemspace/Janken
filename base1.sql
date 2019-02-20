CREATE TABLE users(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	login VARCHAR(32) UNIQUE,
	password BLOB,
	nom VARCHAR(32),
	prenom VARCHAR(32)
	);

CREATE TABLE friends(
	from_id INTEGER,
	to_id INTEGER,
	times timestamp,
	PRIMARY KEY(from_id, to_id),
	FOREIGN KEY(from_id) REFERENCES users(id),
	FOREIGN KEY(to_id) REFERENCES users(id)
	);

CREATE TABLE connections(
	conn_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	user_id INTEGER,
	times timestamp,
	conn_type boolean,
	FOREIGN KEY(user_id) REFERENCES users(id)
	);
	
CREATE TABLE duel(
	from_id INTEGER,
	to_id INTEGER,
	times timestamp,
	winner_id INTEGER,
	PRIMARY KEY(from_id, to_id),
	FOREIGN KEY(from_id) REFERENCES users(id),
	FOREIGN KEY(to_id) REFERENCES users(id)
	);
