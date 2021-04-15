CREATE TABLE IF NOT EXISTS posts (
	id_post int(7) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	post varchar(280),
	author_post varchar(50),
	date_post timestamp);
