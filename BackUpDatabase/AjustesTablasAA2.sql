CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT null,
    rol VARCHAR(20) NOT NULL
);

USE entrega;

INSERT IGNORE INTO usuarios (nombre_usuario, contrasena, rol) VALUES ('oak', '1234', 'Administrador');
INSERT IGNORE INTO usuarios (nombre_usuario, contrasena, rol) VALUES ('ash', '1234', 'Entrenador');

ALTER TABLE pokemon 
ADD COLUMN altura DOUBLE DEFAULT 0.0,
ADD COLUMN peso DOUBLE DEFAULT 0.0,
ADD COLUMN imagen VARCHAR(255) DEFAULT 'imagenes/default.gif';

UPDATE pokemon SET altura = 0.7, peso = 6.9 WHERE numero_pokedex = 1;
UPDATE pokemon SET altura = 1.0, peso = 13.0 WHERE numero_pokedex = 2;
UPDATE pokemon SET altura = 2.0, peso = 100.0 WHERE numero_pokedex = 3;
UPDATE pokemon SET altura = 0.6, peso = 8.5 WHERE numero_pokedex = 4;
UPDATE pokemon SET altura = 0.5, peso = 9.0 WHERE numero_pokedex = 7;
UPDATE pokemon SET altura = 0.4, peso = 6.0 WHERE numero_pokedex = 25;

select * from regiones;

ALTER TABLE usuarios RENAME COLUMN nombreUsuario to nombre_usuario;

DROP TABLE IF EXISTS regiones;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE regiones ( 
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL UNIQUE,
	iniciales VARCHAR(100),
	villanos VARCHAR(50),
	profesor VARCHAR(50),
	videojuego_origen VARCHAR(50),
	fecha_lanzamiento DATE,
	tiene_concursos BOOLEAN
);

INSERT INTO regiones (id, nombre, iniciales, villanos, profesor, videojuego_origen, fecha_lanzamiento, tiene_concursos) VALUES 
(1, 'Kanto', 'Charmander, Squirtle y Bulbasaur', 'Team Rocket', 'Oak', 'Pokémon Rojo y Verde', '1996-02-27', FALSE),
(2, 'Johto', 'Cyndaquil, Totodile y Chikorita', 'Team Rocket', 'Elm', 'Pokémon Plata y Oro', '1999-11-21', FALSE),
(3, 'Hoenn', 'Torchic, Mudkip y Treecko', 'Equipo Magma o Aqua', 'Abedul', 'Pokémon Rubí y Zafiro', '2002-11-21', TRUE),
(4, 'Sinnoh', 'Chimchar, Piplup y Turtwig', 'Equipo Galaxia', 'Serbal', 'Pokémon Diamante y Perla', '2006-09-28', TRUE),
(5, 'Teselia', 'Snivy, Tepig y Oshawott', 'Equipo Plasma', 'Encina', 'Pokémon Negro y Blanco', '2010-09-18', FALSE),
(6, 'Kalos', 'Chespin, Fennekin y Froakie', 'Team Flare', 'Ciprés', 'Pokémon X e Y', '2013-10-12', FALSE),
(7, 'Alola', 'Rowlet, Litten y Popplio', 'Team Skull', 'Kukui', 'Pokémon Sol y Luna', '2016-11-18', FALSE),
(8, 'Galar', 'Grookey, Scorbunny y Sobble', 'Team Yell', 'Magnolia', 'Pokémon Espada y Escudo', '2019-11-15', FALSE),
(9, 'Paldea', 'Sprigatito, Fuecoco y Quaxly', 'Team Star', 'Clavel', 'Pokémon Escarlata y Púrpura', '2022-11-18', FALSE);

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE pokemon
	MODIFY COLUMN generacion ENUM('Primera', 'Segunda', 'Tercera', 'Cuarta', 'Quinta', 'Sexta', 'Séptima', 'Octava', 'Novena');
