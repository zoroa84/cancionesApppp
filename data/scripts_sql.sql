/* script para qhe la tabla de usuario la columna 'nombre' y 'email' sean unicas*/

ALTER TABLE `usuario` ADD UNIQUE INDEX `nombre` (`nombre`),	ADD UNIQUE INDEX `email` (`email`);
ALTER TABLE `usuario` ADD UNIQUE INDEX `nombre` (`nombre`),	ADD UNIQUE INDEX `email` (`nombre`);
