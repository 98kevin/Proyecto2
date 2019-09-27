-- MariaDB dump 10.17  Distrib 10.4.7-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: Revistas
-- ------------------------------------------------------
-- Server version	10.4.7-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Bloqueo`
--

DROP TABLE IF EXISTS `Bloqueo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bloqueo` (
  `idBloqueo` int(11) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_suscripcion` int(11) NOT NULL,
  PRIMARY KEY (`idBloqueo`),
  KEY `fk_Bloqueo_Usuario1_idx` (`id_usuario`),
  KEY `fk_Bloqueo_Suscripcion1_idx` (`id_suscripcion`),
  CONSTRAINT `FK_BLOQUEO_SUSCRIPCION` FOREIGN KEY (`id_suscripcion`) REFERENCES `Suscripcion` (`id_suscripcion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_BLOQUEO_USUARIO` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bloqueo`
--

LOCK TABLES `Bloqueo` WRITE;
/*!40000 ALTER TABLE `Bloqueo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bloqueo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categoria` (
  `id_categoria` int(11) NOT NULL,
  `descripcion` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comentario`
--

DROP TABLE IF EXISTS `Comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comentario` (
  `id_comentario` int(11) NOT NULL,
  `descripcion` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fecha_hora` datetime DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `codigo_revista` int(11) NOT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `INDEX_USUARIO_REVISTA` (`id_usuario`),
  KEY `INDEX_CODIGO_REVISTA` (`codigo_revista`),
  CONSTRAINT `FK_COMENTARIO` FOREIGN KEY (`codigo_revista`) REFERENCES `Revista` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ID_USUARIO` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comentario`
--

LOCK TABLES `Comentario` WRITE;
/*!40000 ALTER TABLE `Comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Costos`
--

DROP TABLE IF EXISTS `Costos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Costos` (
  `id_costo` int(11) NOT NULL,
  `descripcion` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`id_costo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Costos`
--

LOCK TABLES `Costos` WRITE;
/*!40000 ALTER TABLE `Costos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Costos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cuenta`
--

DROP TABLE IF EXISTS `Cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cuenta` (
  `id_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `abonos` double DEFAULT NULL,
  PRIMARY KEY (`id_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cuenta`
--

LOCK TABLES `Cuenta` WRITE;
/*!40000 ALTER TABLE `Cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MeGusta`
--

DROP TABLE IF EXISTS `MeGusta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MeGusta` (
  `id_me_gusta` int(11) NOT NULL,
  `edicion_revista` int(11) DEFAULT NULL,
  `fecha_hora` datetime DEFAULT NULL,
  `estado` tinyint(4) DEFAULT 1,
  `codigo_revista` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_me_gusta`),
  KEY `INDEX_ME_GUSTA_CODIGO_REVISTA` (`codigo_revista`),
  KEY `INDEX_REVISTA_USUARIO` (`id_usuario`),
  CONSTRAINT `FK_ME_GUSTA_REVISTA` FOREIGN KEY (`codigo_revista`) REFERENCES `Revista` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ME_GUSTA_USUARIO` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MeGusta`
--

LOCK TABLES `MeGusta` WRITE;
/*!40000 ALTER TABLE `MeGusta` DISABLE KEYS */;
/*!40000 ALTER TABLE `MeGusta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pago`
--

DROP TABLE IF EXISTS `Pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pago` (
  `id_pago` int(11) NOT NULL,
  `mes` date DEFAULT NULL,
  `anio` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_suscripcion` int(11) NOT NULL,
  PRIMARY KEY (`id_pago`,`id_usuario`,`id_suscripcion`),
  KEY `fk_Pago_Usuario1_idx` (`id_usuario`),
  KEY `fk_Pago_Suscripcion1_idx` (`id_suscripcion`),
  CONSTRAINT `FK_PAGO_SUSCRIPCION` FOREIGN KEY (`id_suscripcion`) REFERENCES `Suscripcion` (`id_suscripcion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PAGO_USUARIO` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pago`
--

LOCK TABLES `Pago` WRITE;
/*!40000 ALTER TABLE `Pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Perfil`
--

DROP TABLE IF EXISTS `Perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Perfil` (
  `id_usuario` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL,
  `hobbies` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gustos` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fotografia` blob DEFAULT NULL,
  `lugar_de_residencia` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `numero_de_telefono` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `genero` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idioma` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_usuario`,`id_cuenta`),
  KEY `INDEX_PERFIL_CUENTA` (`id_cuenta`),
  KEY `INDEX_TARJETA_CREDITO` (`id_cuenta`),
  CONSTRAINT `FK_CUENTA` FOREIGN KEY (`id_cuenta`) REFERENCES `Cuenta` (`id_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO` FOREIGN KEY (`id_usuario`) REFERENCES `Usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Perfil`
--

LOCK TABLES `Perfil` WRITE;
/*!40000 ALTER TABLE `Perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `Perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permisos`
--

DROP TABLE IF EXISTS `Permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permisos` (
  `id_permisos` int(11) NOT NULL,
  `suscripciones` tinyint(4) DEFAULT 1,
  `me_gusta` tinyint(4) DEFAULT 1,
  `comentarios` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`id_permisos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permisos`
--

LOCK TABLES `Permisos` WRITE;
/*!40000 ALTER TABLE `Permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Publicacion`
--

DROP TABLE IF EXISTS `Publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Publicacion` (
  `idPublicacion` int(11) NOT NULL,
  `path` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fecha_de_publicacion` date DEFAULT NULL,
  `codigo_revista` int(11) NOT NULL,
  PRIMARY KEY (`idPublicacion`,`codigo_revista`),
  KEY `INDEX_PUBLICACION_REVISTA` (`codigo_revista`),
  CONSTRAINT `fk_Publicacion_Revista1` FOREIGN KEY (`codigo_revista`) REFERENCES `Revista` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Publicacion`
--

LOCK TABLES `Publicacion` WRITE;
/*!40000 ALTER TABLE `Publicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Publicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Revista`
--

DROP TABLE IF EXISTS `Revista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Revista` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `etiquetas` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cuota_suscripcion` double DEFAULT NULL,
  `id_Permisos` int(11) NOT NULL,
  `costo_por_dia` int(11) NOT NULL,
  `id_Categoria` int(11) NOT NULL,
  `id_editor` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `INDEX_PERMISO` (`id_Permisos`),
  KEY `INDEX_COSTO` (`costo_por_dia`),
  KEY `INDEX_CATEGORIA` (`id_Categoria`),
  KEY `INDEX_EDITOR` (`id_editor`),
  CONSTRAINT `FK_CATEGORIA` FOREIGN KEY (`id_Categoria`) REFERENCES `Categoria` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_COSTOS_REVISTA` FOREIGN KEY (`costo_por_dia`) REFERENCES `Costos` (`id_costo`) ON UPDATE CASCADE,
  CONSTRAINT `FK_EDITOR_REVISTA` FOREIGN KEY (`id_editor`) REFERENCES `Usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_PERMISOS` FOREIGN KEY (`id_Permisos`) REFERENCES `Permisos` (`id_permisos`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Revista`
--

LOCK TABLES `Revista` WRITE;
/*!40000 ALTER TABLE `Revista` DISABLE KEYS */;
/*!40000 ALTER TABLE `Revista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sistema`
--

DROP TABLE IF EXISTS `Sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sistema` (
  `porcentaje_de_ganancia` int(11) DEFAULT NULL,
  `id_cuenta` int(11) NOT NULL,
  KEY `FK_CUENTA_SISTEMA` (`id_cuenta`),
  CONSTRAINT `FK_CUENTA_SISTEMA` FOREIGN KEY (`id_cuenta`) REFERENCES `Cuenta` (`id_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sistema`
--

LOCK TABLES `Sistema` WRITE;
/*!40000 ALTER TABLE `Sistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Suscripcion`
--

DROP TABLE IF EXISTS `Suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Suscripcion` (
  `id_suscripcion` int(11) NOT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `mes_inicial` int(11) DEFAULT NULL,
  `anio_inicial` int(11) DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  `codigo_revista` int(11) NOT NULL,
  PRIMARY KEY (`id_suscripcion`),
  KEY `INDEX_SUSCRIPCION_USUARIO` (`idUsuario`),
  KEY `INDEX_CODIGO_REVISTA_USUARIO` (`codigo_revista`),
  CONSTRAINT `FK_REVISTA` FOREIGN KEY (`codigo_revista`) REFERENCES `Revista` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SUSCRIPTOR` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Suscripcion`
--

LOCK TABLES `Suscripcion` WRITE;
/*!40000 ALTER TABLE `Suscripcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Suscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TiposDeUsuarios`
--

DROP TABLE IF EXISTS `TiposDeUsuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TiposDeUsuarios` (
  `tipo` int(11) NOT NULL,
  `nombre` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TiposDeUsuarios`
--

LOCK TABLES `TiposDeUsuarios` WRITE;
/*!40000 ALTER TABLE `TiposDeUsuarios` DISABLE KEYS */;
INSERT INTO `TiposDeUsuarios` VALUES (1,'Administrador'),(2,'Editor'),(3,'Usuario');
/*!40000 ALTER TABLE `TiposDeUsuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `correo_electronico` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tipo_de_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `CORREO_ELECTRONICO_UNIQUE` (`correo_electronico`),
  KEY `INDEX_TIPO_DE_USUARIO` (`tipo_de_usuario`),
  CONSTRAINT `FK_TIPO_USUARIO` FOREIGN KEY (`tipo_de_usuario`) REFERENCES `TiposDeUsuarios` (`tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'Kevin Josue Fuentes','fuentesjosue83@gmail.com','VESaSAQR1YdTSkkS0tzQgA==:yndWR0/4ki0+BKF93gqDgg==',1);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-26 13:22:24
