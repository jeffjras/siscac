SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `donazione` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `donazione` ;

-- -----------------------------------------------------
-- Table `donazione`.`colaborador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`colaborador` ;

CREATE TABLE IF NOT EXISTS `donazione`.`colaborador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `celular` VARCHAR(11) NOT NULL,
  `logradouro` VARCHAR(200) NULL,
  `numero` VARCHAR(45) NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(100) NULL,
  `cep` VARCHAR(10) NULL,
  `cpfOuCnpj` VARCHAR(18) NULL,
  `senha` VARCHAR(100) NOT NULL,
  `perfil` CHAR NOT NULL DEFAULT 'C' COMMENT 'C - Colaborador\nA - Administrador',
  `profissao` VARCHAR(100) NOT NULL,
  `comoColaborar` VARCHAR(250) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`campanha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`campanha` ;

CREATE TABLE IF NOT EXISTS `donazione`.`campanha` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `cadastro` DATETIME NOT NULL,
  `habilitada` TINYINT(1) NOT NULL DEFAULT 1,
  `dataFim` DATETIME NOT NULL,
  `meta` DECIMAL(8,2) NOT NULL,
  `doacaoMinima` DECIMAL(4,2) NOT NULL,
  `arrecadado` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`item_campanha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`item_campanha` ;

CREATE TABLE IF NOT EXISTS `donazione`.`item_campanha` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `valor` DECIMAL(5,2) NOT NULL,
  `campanha` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_campanha_campanha_idx` (`campanha` ASC),
  CONSTRAINT `fk_item_campanha_campanha`
    FOREIGN KEY (`campanha`)
    REFERENCES `donazione`.`campanha` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`acao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`acao` ;

CREATE TABLE IF NOT EXISTS `donazione`.`acao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `inicio` DATETIME NOT NULL,
  `fim` DATETIME NOT NULL,
  `cadastro` DATETIME NOT NULL,
  `cargaHoraria` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`campanha_acao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`campanha_acao` ;

CREATE TABLE IF NOT EXISTS `donazione`.`campanha_acao` (
  `campanha_id` INT NOT NULL,
  `acao_id` INT NOT NULL,
  PRIMARY KEY (`campanha_id`, `acao_id`),
  INDEX `fk_campanha_has_acao_acao1_idx` (`acao_id` ASC),
  INDEX `fk_campanha_has_acao_campanha1_idx` (`campanha_id` ASC),
  CONSTRAINT `fk_campanha_has_acao_campanha1`
    FOREIGN KEY (`campanha_id`)
    REFERENCES `donazione`.`campanha` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_campanha_has_acao_acao1`
    FOREIGN KEY (`acao_id`)
    REFERENCES `donazione`.`acao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`colaborador_acao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`colaborador_acao` ;

CREATE TABLE IF NOT EXISTS `donazione`.`colaborador_acao` (
  `colaborador_id` INT NOT NULL,
  `acao_id` INT NOT NULL,
  PRIMARY KEY (`colaborador_id`, `acao_id`),
  INDEX `fk_colaborador_has_acao_acao1_idx` (`acao_id` ASC),
  INDEX `fk_colaborador_has_acao_colaborador1_idx` (`colaborador_id` ASC),
  CONSTRAINT `fk_colaborador_has_acao_colaborador1`
    FOREIGN KEY (`colaborador_id`)
    REFERENCES `donazione`.`colaborador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_colaborador_has_acao_acao1`
    FOREIGN KEY (`acao_id`)
    REFERENCES `donazione`.`acao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`doacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`doacao` ;

CREATE TABLE IF NOT EXISTS `donazione`.`doacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cadastro` DATETIME NOT NULL,
  `total` DECIMAL(6,2) NOT NULL DEFAULT 0,
  `colaborador` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_doacao_colaborador1_idx` (`colaborador` ASC),
  CONSTRAINT `fk_doacao_colaborador1`
    FOREIGN KEY (`colaborador`)
    REFERENCES `donazione`.`colaborador` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `donazione`.`item_doacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `donazione`.`item_doacao` ;

CREATE TABLE IF NOT EXISTS `donazione`.`item_doacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(80) NOT NULL,
  `valor` DECIMAL(5,2) NOT NULL,
  `doacao` INT NOT NULL,
  `campanha` INT NULL,
  `item_campanha` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_doacao_doacao1_idx` (`doacao` ASC),
  INDEX `fk_item_doacao_campanha1_idx` (`campanha` ASC),
  INDEX `fk_item_doacao_item_campanha1_idx` (`item_campanha` ASC),
  CONSTRAINT `fk_item_doacao_doacao1`
    FOREIGN KEY (`doacao`)
    REFERENCES `donazione`.`doacao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_item_doacao_campanha1`
    FOREIGN KEY (`campanha`)
    REFERENCES `donazione`.`campanha` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_item_doacao_item_campanha1`
    FOREIGN KEY (`item_campanha`)
    REFERENCES `donazione`.`item_campanha` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
