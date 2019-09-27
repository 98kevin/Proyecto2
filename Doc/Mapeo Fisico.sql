-- -----------------------------------------------------
-- Schema Revistas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Revistas` ;
USE `Revistas` ;

-- -----------------------------------------------------
-- Table `Revistas`.`Permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Permisos` (
  `id_permisos` INT NOT NULL,
  `suscripciones` TINYINT NULL DEFAULT 1,
  `me_gusta` TINYINT NULL DEFAULT 1,
  `comentarios` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id_permisos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Costos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Costos` (
  `id_costo` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `monto` DOUBLE NULL,
  PRIMARY KEY (`id_costo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Categoria` (
  `id_categoria` INT NOT NULL,
  `descripcion` VARCHAR(20) NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`TiposDeUsuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`TiposDeUsuarios` (
  `tipo` INT NOT NULL,
  `nombre` VARCHAR(15),
  PRIMARY KEY (`tipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Usuario` (
  `id_usuario` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `correo_electronico` VARCHAR(45) NULL,
  `password` LONGTEXT NULL,
  `tipo_de_usuario` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `CORREO_ELECTRONICO_UNIQUE` (`correo_electronico`),
  INDEX `INDEX_TIPO_DE_USUARIO` (`tipo_de_usuario`),
  CONSTRAINT `FK_TIPO_USUARIO`
    FOREIGN KEY (`tipo_de_usuario`)
    REFERENCES `Revistas`.`TiposDeUsuarios` (`tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Revista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Revista` (
  `codigo` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `etiquetas` VARCHAR(45) NULL,
  `cuota_suscripcion` DOUBLE NULL,
  `id_Permisos` INT NOT NULL,
  `costo_por_dia` INT NOT NULL,
  `id_Categoria` INT NOT NULL,
  `id_editor` INT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `INDEX_PERMISO` (`id_Permisos` ASC),
  INDEX `INDEX_COSTO` (`costo_por_dia` ASC),
  INDEX `INDEX_CATEGORIA` (`id_Categoria` ASC),
  INDEX `INDEX_EDITOR` (`id_editor` ASC),
  CONSTRAINT `FK_PERMISOS`
    FOREIGN KEY (`id_Permisos`)
    REFERENCES `Revistas`.`Permisos` (`id_permisos`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `FK_COSTOS_REVISTA`
    FOREIGN KEY (`costo_por_dia`)
    REFERENCES `Revistas`.`Costos` (`id_costo`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `FK_CATEGORIA`
    FOREIGN KEY (`id_Categoria`)
    REFERENCES `Revistas`.`Categoria` (`id_categoria`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_EDITOR_REVISTA`
    FOREIGN KEY (`id_editor`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Cuenta` (
  `id_cuenta` INT NOT NULL AUTO_INCREMENT,
  `abonos` DOUBLE NULL,
  PRIMARY KEY (`tarjeta_de_credito`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Comentario` (
  `id_comentario` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `fecha_hora` DATETIME NULL,
  `id_usuario` INT NOT NULL,
  `codigo_revista` INT NOT NULL,
  PRIMARY KEY (`id_comentario`),
  INDEX `INDEX_USUARIO_REVISTA` (`id_usuario`),
  INDEX `INDEX_CODIGO_REVISTA` (`codigo_revista`),
  CONSTRAINT `FK_ID_USUARIO`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COMENTARIO`
    FOREIGN KEY (`codigo_revista`)
    REFERENCES `Revistas`.`Revista` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Sistema` (
  `porcentaje_de_ganancia` INT NULL,
  `id_cuenta` INT NOT NULL,
  CONSTRAINT `FK_CUENTA_SISTEMA` 
    FOREIGN KEY (`id_cuenta`)
    REFERENCES `Revistas`.`Cuenta` (`id_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Suscripcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Suscripcion` (
  `id_suscripcion` INT NOT NULL,
  `estado` TINYINT NULL,
  `mes_inicial` INT NULL,
  `anio_inicial` INT NULL,
  `idUsuario` INT NOT NULL,
  `codigo_revista` INT NOT NULL,
  PRIMARY KEY (`id_suscripcion`),
  INDEX `INDEX_SUSCRIPCION_USUARIO` (`idUsuario`),
  INDEX `INDEX_CODIGO_REVISTA_USUARIO` (`codigo_revista`),
  CONSTRAINT `FK_SUSCRIPTOR`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_REVISTA`
    FOREIGN KEY (`codigo_revista`)
    REFERENCES `Revistas`.`Revista` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`MeGusta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`MeGusta` (
  `id_me_gusta` INT NOT NULL,
  `edicion_revista` INT NULL,
  `fecha_hora` DATETIME NULL,
  `estado` TINYINT NULL DEFAULT 1,
  `codigo_revista` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_me_gusta`),
  INDEX `INDEX_ME_GUSTA_CODIGO_REVISTA` (`codigo_revista`),
  INDEX `INDEX_REVISTA_USUARIO` (`id_usuario`),
  CONSTRAINT `FK_ME_GUSTA_REVISTA`
    FOREIGN KEY (`codigo_revista`)
    REFERENCES `Revistas`.`Revista` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_ME_GUSTA_USUARIO`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Perfil` (
  `id_usuario` INT NOT NULL,
  `id_cuenta` INT NOT NULL,
  `hobbies` VARCHAR(45) NULL,
  `gustos` VARCHAR(45) NULL,
  `fotografia` MEDIUMBLOB NULL,
  `lugar_de_residencia` VARCHAR(45) NULL,
  `numero_de_telefono` VARCHAR(15) NULL,
  `genero` VARCHAR(10) NULL,
  `idioma` VARCHAR(25) NULL,
  `fecha_de_nacimiento` DATE NULL,
  PRIMARY KEY (id_usuario, tarjeta_de_credito),
  INDEX `INDEX_TARJETA_CREDITO` (`tarjeta_de_credito`),
  CONSTRAINT `FK_USUARIO`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CUENTA`
    FOREIGN KEY (`id_cuenta`)
    REFERENCES `Revistas`.`Cuenta` (`id_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Publicacion` (
  `idPublicacion` INT NOT NULL,
  `path` VARCHAR(45) NULL,
  `fecha_de_publicacion` DATE NULL,
  `codigo_revista` INT NOT NULL,
  PRIMARY KEY (`idPublicacion`, `codigo_revista`),
  INDEX `INDEX_PUBLICACION_REVISTA` (`codigo_revista`),
  CONSTRAINT `fk_Publicacion_Revista1`
    FOREIGN KEY (`codigo_revista`)
    REFERENCES `Revistas`.`Revista` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Pago` (
  `id_pago` INT NOT NULL,
  `mes` DATE NULL,
  `anio` VARCHAR(45) NULL,
  `id_usuario` INT NOT NULL,
  `id_suscripcion` INT NOT NULL,
  PRIMARY KEY (`id_pago`, `id_usuario`, `id_suscripcion`),
  INDEX `fk_Pago_Usuario1_idx` (`id_usuario`),
  INDEX `fk_Pago_Suscripcion1_idx` (`id_suscripcion`),
  CONSTRAINT `FK_PAGO_USUARIO`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PAGO_SUSCRIPCION`
    FOREIGN KEY (`id_suscripcion`)
    REFERENCES `Revistas`.`Suscripcion` (`id_suscripcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Revistas`.`Bloqueo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Revistas`.`Bloqueo` (
  `idBloqueo` INT NOT NULL,
  `estado` TINYINT NULL,
  `id_usuario` INT NOT NULL,
  `id_suscripcion` INT NOT NULL,
  PRIMARY KEY (`idBloqueo`),
  INDEX `fk_Bloqueo_Usuario1_idx` (`id_usuario`),
  INDEX `fk_Bloqueo_Suscripcion1_idx` (`id_suscripcion`),
  CONSTRAINT `FK_BLOQUEO_USUARIO`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `Revistas`.`Usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_BLOQUEO_SUSCRIPCION`
    FOREIGN KEY (`id_suscripcion`)
    REFERENCES `Revistas`.`Suscripcion` (`id_suscripcion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

