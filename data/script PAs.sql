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

-- Volcando estructura para procedimiento canciones.pa_rol_delete
DROP PROCEDURE IF EXISTS `pa_rol_delete`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_rol_delete`(
	IN `pId` INT
)
BEGIN
	DELETE FROM `rol` WHERE  `id`=pId;
END//
DELIMITER ;

-- Volcando estructura para procedimiento canciones.pa_rol_findAll
DROP PROCEDURE IF EXISTS `pa_rol_findAll`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_rol_findAll`()
BEGIN
	SELECT `id`, `nombre` FROM `rol` ORDER BY `id` desc;
END//
DELIMITER ;

-- Volcando estructura para procedimiento canciones.pa_rol_findById
DROP PROCEDURE IF EXISTS `pa_rol_findById`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_rol_findById`(
	IN `pId` INT
)
BEGIN
	SELECT `id`, `nombre` FROM `rol` WHERE `id` = pId;
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