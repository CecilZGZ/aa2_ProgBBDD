CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT null,
    rol VARCHAR(20) NOT NULL
);

INSERT IGNORE INTO usuario (nombreUsuario, contrasena, rol) VALUES ('oak', '1234', 'Administrador');
INSERT IGNORE INTO usuarios (nombreUsuario, contrasena, rol) VALUES ('oak', '1234', 'Administrador');
INSERT IGNORE INTO usuario (nombreUsuario, contrasena, rol) VALUES ('ash', '1234', '');

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