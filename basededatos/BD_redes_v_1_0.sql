-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema redesunificadas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `redesunificadas` ;

-- -----------------------------------------------------
-- Schema redesunificadas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `redesunificadas` DEFAULT CHARACTER SET utf8 ;
USE `redesunificadas` ;

-- -----------------------------------------------------
-- Table `redesunificadas`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redesunificadas`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL,
  `eliminado` TINYINT NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redesunificadas`.`credencial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redesunificadas`.`credencial` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_credencial_usuarios_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_credencial_usuarios`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `redesunificadas`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redesunificadas`.`extension_sip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redesunificadas`.`extension_sip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero_ext` VARCHAR(10) NOT NULL,
  `clave` VARCHAR(100) NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_extension_sip_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_extension_sip_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `redesunificadas`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redesunificadas`.`sala_video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redesunificadas`.`sala_video` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre_sala` VARCHAR(250) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sala_video_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_sala_video_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `redesunificadas`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redesunificadas`.`registro_salas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redesunificadas`.`registro_salas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_ingreso` DATETIME NOT NULL,
  `fecha_salida` DATETIME NOT NULL,
  `usuario_id` INT NOT NULL,
  `sala_id` INT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`, `sala_id`),
  INDEX `fk_registro_salasee_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_registro_salasee_sala_video1_idx` (`sala_id` ASC) VISIBLE,
  CONSTRAINT `fk_registro_salasee_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `redesunificadas`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registro_salasee_sala_video1`
    FOREIGN KEY (`sala_id`)
    REFERENCES `redesunificadas`.`sala_video` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `redesunificadas`.`registro_llamadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redesunificadas`.`registro_llamadas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `origen_ext_id` INT NOT NULL,
  `destino_ext_id` INT NOT NULL,
  `fecha_inicio` DATETIME NOT NULL,
  `fecha_fin` DATETIME NOT NULL,
  `duracion_segundos` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_registro_llamadas_extension_sip1_idx` (`origen_ext_id` ASC) VISIBLE,
  INDEX `fk_registro_llamadas_extension_sip2_idx` (`destino_ext_id` ASC) VISIBLE,
  CONSTRAINT `fk_registro_llamadas_extension_sip1`
    FOREIGN KEY (`origen_ext_id`)
    REFERENCES `redesunificadas`.`extension_sip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_registro_llamadas_extension_sip2`
    FOREIGN KEY (`destino_ext_id`)
    REFERENCES `redesunificadas`.`extension_sip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
