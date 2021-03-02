-- Script Inicial al crear servicio de Mysql para Docker
CREATE DATABASE IF NOT EXISTS marvel CHARACTER SET utf8 COLLATE utf8_general_ci;
USE marvel;
CREATE TABLE hero(
	id_hero int NOT NULL,
	name_hero varchar(60) CHARSET utf8mb4,
	PRIMARY KEY(id_hero)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE comic(
	id_comic int NOT NULL,
	name_comic varchar(110) CHARSET utf8mb4,
	name_writer varchar(30) CHARSET utf8mb4,
	name_editor varchar(30) CHARSET utf8mb4,
	name_colorist varchar(30) CHARSET utf8mb4,
	id_hero int,
	last_sync datetime,
	PRIMARY KEY(id_comic),
	FOREIGN KEY(id_hero) REFERENCES hero(id_hero) ON UPDATE CASCADE ON DELETE RESTRICT
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE interaction(
	id_comic int not null,
	id_hero_interaction int not null,
	PRIMARY KEY(id_comic, id_hero_interaction),
	FOREIGN KEY(id_comic) REFERENCES comic(id_comic) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY(id_hero_interaction) REFERENCES hero(id_hero) ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO hero(id_hero, name_hero)VALUES (1009368,'Iron Man');
INSERT INTO hero(id_hero, name_hero)VALUES (1009220,'Captain America');




