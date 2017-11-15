-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.19 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para canciones
DROP DATABASE IF EXISTS `canciones`;
CREATE DATABASE IF NOT EXISTS `canciones` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `canciones`;

-- Volcando estructura para tabla canciones.artista
DROP TABLE IF EXISTS `artista`;
CREATE TABLE IF NOT EXISTS `artista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.artista: ~7 rows (aproximadamente)
DELETE FROM `artista`;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
INSERT INTO `artista` (`id`, `nombre`) VALUES
	(1, 'Fito y Fitipaldis'),
	(2, 'Loquillo'),
	(3, 'Extremoduro'),
	(4, 'Fangoria'),
	(5, 'Gertrudis'),
	(6, 'LALALAsoloSinCancion'),
	(7, 'Mafalda');
/*!40000 ALTER TABLE `artista` ENABLE KEYS */;

-- Volcando estructura para tabla canciones.cancion
DROP TABLE IF EXISTS `cancion`;
CREATE TABLE IF NOT EXISTS `cancion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(90) NOT NULL,
  `duracion` varchar(5) NOT NULL,
  `cover` varchar(255) NOT NULL DEFAULT 'http://www.thetopictrend.com/wp-content/uploads/2015/07/musica-C%C3%B3mo-nos-influye-en-la-Publicidad-el-Retail-y-las-Redes-Sociales-1024x1024.jpg',
  `youtube` varchar(11) NOT NULL DEFAULT '_JCIJQeUrCo',
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `genero_id` int(11) NOT NULL COMMENT 'Guardamos solo el Identificador de la cancion, para verlo concaternar en: "https://www.youtube.com/watch?v=ID_CANCION"',
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cancion_genero1_idx` (`genero_id`),
  KEY `fk_cancion_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_cancion_genero1` FOREIGN KEY (`genero_id`) REFERENCES `genero` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cancion_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.cancion: ~4 rows (aproximadamente)
DELETE FROM `cancion`;
/*!40000 ALTER TABLE `cancion` DISABLE KEYS */;
INSERT INTO `cancion` (`id`, `titulo`, `duracion`, `cover`, `youtube`, `fecha_creacion`, `fecha_modificacion`, `genero_id`, `usuario_id`) VALUES
	(1, 'Segundo Movimiento: Lo de fuera', '11:27', 'https://i.scdn.co/image/45914386b8c3297b0d860c0124491ea3208b8ba7', 'He7h34v-ryI', '2017-11-03 12:27:56', '2017-11-07 09:08:32', 1, 1),
	(2, 'Tercer Movimiento: Lo de Dentro', '7:36', 'https://i.scdn.co/image/45914386b8c3297b0d860c0124491ea3208b8ba7', 'xHpqeQEcS3I', '2017-11-03 13:24:17', '2017-11-07 12:29:12', 2, 1),
	(3, 'Primer mov', '0:03', 'https://i.scdn.co/image/45914386b8c3297b0d860c0124491ea3208b8ba7', 'xzWAWaUJNqg', '2017-11-03 13:24:17', '2017-11-08 11:39:48', 2, 1),
	(4, 'Del pita pita dell', '1:00', 'http://www.thetopictrend.com/wp-content/uploads/2015/07/musica-C%C3%B3mo-nos-influye-en-la-Publicidad-el-Retail-y-las-Redes-Sociales-1024x1024.jpg', '_JCIJQeUrCo', '2017-11-08 09:53:48', '2017-11-08 13:45:22', 1, 2);
/*!40000 ALTER TABLE `cancion` ENABLE KEYS */;

-- Volcando estructura para tabla canciones.cancion_has_artista
DROP TABLE IF EXISTS `cancion_has_artista`;
CREATE TABLE IF NOT EXISTS `cancion_has_artista` (
  `cancion_id` int(11) NOT NULL,
  `artista_id` int(11) NOT NULL,
  PRIMARY KEY (`cancion_id`,`artista_id`),
  KEY `fk_cancion_has_artista_artista1_idx` (`artista_id`),
  KEY `fk_cancion_has_artista_cancion_idx` (`cancion_id`),
  CONSTRAINT `fk_cancion_has_artista_artista1` FOREIGN KEY (`artista_id`) REFERENCES `artista` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cancion_has_artista_cancion` FOREIGN KEY (`cancion_id`) REFERENCES `cancion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.cancion_has_artista: ~5 rows (aproximadamente)
DELETE FROM `cancion_has_artista`;
/*!40000 ALTER TABLE `cancion_has_artista` DISABLE KEYS */;
INSERT INTO `cancion_has_artista` (`cancion_id`, `artista_id`) VALUES
	(3, 1),
	(1, 3),
	(2, 3),
	(3, 3),
	(4, 4),
	(4, 5);
/*!40000 ALTER TABLE `cancion_has_artista` ENABLE KEYS */;

-- Volcando estructura para tabla canciones.genero
DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.genero: ~2 rows (aproximadamente)
DELETE FROM `genero`;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` (`id`, `nombre`) VALUES
	(1, 'Punk'),
	(2, 'Rock');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;

-- Volcando estructura para tabla canciones.likes
DROP TABLE IF EXISTS `likes`;
CREATE TABLE IF NOT EXISTS `likes` (
  `cancion_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cancion_id`,`usuario_id`),
  KEY `fk_cancion_has_usuario1_usuario1_idx` (`usuario_id`),
  KEY `fk_cancion_has_usuario1_cancion1_idx` (`cancion_id`),
  CONSTRAINT `fk_cancion_has_usuario1_cancion1` FOREIGN KEY (`cancion_id`) REFERENCES `cancion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cancion_has_usuario1_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.likes: ~2 rows (aproximadamente)
DELETE FROM `likes`;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` (`cancion_id`, `usuario_id`, `fecha`) VALUES
	(3, 1, '2017-11-08 09:26:45'),
	(3, 2, '2017-11-08 09:27:01');
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;

-- Volcando estructura para tabla canciones.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(5) DEFAULT 'user' COMMENT 'Los roles posibles son ADMIN y USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.rol: ~2 rows (aproximadamente)
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id`, `nombre`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;

-- Volcando estructura para tabla canciones.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `avatar` varchar(255) NOT NULL DEFAULT 'https://blog.omsica.com/wp-content/uploads/2015/09/Qu%C3%A9-le-hace-la-musica-a-nuestro-cerebro.jpg',
  `rol_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_usuario_rol1_idx` (`rol_id`),
  CONSTRAINT `fk_usuario_rol1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla canciones.usuario: ~3 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nombre`, `pass`, `email`, `avatar`, `rol_id`) VALUES
	(1, 'alfredo', '1234', 'MAIL@ES.ES', 'https://blog.omsica.com/wp-content/uploads/2015/09/Qu%C3%A9-le-hace-la-musica-a-nuestro-cerebro.jpg', 1),
	(2, 'lei', 'lei', 'lei@lei.es', 'http://aculco.co.uk/wp-content/uploads/2016/01/avatarsi.jpeg', 2),
	(3, 'Tralarii', 'lololo', 'A@aa.es', 'https://blog.omsica.com/wp-content/uploads/2015/09/Qu%C3%A9-le-hace-la-musica-a-nuestro-cerebro.jpg', 2),
	(4, 'dsfsf', 'sfd', 'fdsf', '', 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para procedimiento canciones.pa_check_usuario_nombre
DROP PROCEDURE IF EXISTS `pa_check_usuario_nombre`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_check_usuario_nombre`(
	IN `pNombre` VARCHAR(50)
)
BEGIN

	SELECT id FROM usuario WHERE nombre = pNombre;

END//
DELIMITER ;

-- Volcando estructura para procedimiento canciones.pa_rol_create
DROP PROCEDURE IF EXISTS `pa_rol_create`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_rol_create`(
	IN `pNombre` VARCHAR(50)

)
BEGIN

	INSERT INTO `canciones`.`rol` (`nombre`) VALUES (pNombre);
	
END//
DELIMITER ;

-- Volcando estructura para procedimiento canciones.pa_rol_update
DROP PROCEDURE IF EXISTS `pa_rol_update`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_rol_update`(
	IN `pNombre` VARCHAR(50),
	IN `pId` INT
)
BEGIN
	UPDATE `rol` SET `nombre`=pNombre WHERE  `id`=pId;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
