-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: gamerboxd
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compania`
--

DROP TABLE IF EXISTS `compania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compania` (
  `idcompania` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcompania`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compania`
--

LOCK TABLES `compania` WRITE;
/*!40000 ALTER TABLE `compania` DISABLE KEYS */;
/*!40000 ALTER TABLE `compania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compania_propuesta`
--

DROP TABLE IF EXISTS `compania_propuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compania_propuesta` (
  `id_comp` int NOT NULL,
  `id_propuesta` int NOT NULL,
  PRIMARY KEY (`id_comp`,`id_propuesta`),
  KEY `id_compania_propuesta_id_propuesta_idx` (`id_propuesta`),
  CONSTRAINT `id_compania_propuesta_id_compania` FOREIGN KEY (`id_comp`) REFERENCES `compania` (`idcompania`),
  CONSTRAINT `id_compania_propuesta_id_propuesta` FOREIGN KEY (`id_propuesta`) REFERENCES `propuesta` (`idpropuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compania_propuesta`
--

LOCK TABLES `compania_propuesta` WRITE;
/*!40000 ALTER TABLE `compania_propuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `compania_propuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `idgenero` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idgenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero_juego`
--

DROP TABLE IF EXISTS `genero_juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero_juego` (
  `idgenero` int NOT NULL,
  `idjuego` int NOT NULL,
  PRIMARY KEY (`idgenero`,`idjuego`),
  KEY `id_genero_juego_id_juego_idx` (`idjuego`),
  CONSTRAINT `id_genero_juego_id_genero` FOREIGN KEY (`idgenero`) REFERENCES `genero` (`idgenero`),
  CONSTRAINT `id_genero_juego_id_juego` FOREIGN KEY (`idjuego`) REFERENCES `juego` (`idjuego`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero_juego`
--

LOCK TABLES `genero_juego` WRITE;
/*!40000 ALTER TABLE `genero_juego` DISABLE KEYS */;
/*!40000 ALTER TABLE `genero_juego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo` (
  `idgrupo` int NOT NULL AUTO_INCREMENT,
  `foto_perfil` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idgrupo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_usuario`
--

DROP TABLE IF EXISTS `grupo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_usuario` (
  `id_grupo` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_grupo`,`id_usuario`),
  KEY `id_grupo_usuario_id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_grupo_usuario_id_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`idgrupo`),
  CONSTRAINT `id_grupo_usuario_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_usuario`
--

LOCK TABLES `grupo_usuario` WRITE;
/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juego`
--

DROP TABLE IF EXISTS `juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juego` (
  `idjuego` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  `imagen` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `precio` varchar(45) DEFAULT NULL,
  `plataforma` varchar(45) DEFAULT NULL,
  `puntaje_promedio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idjuego`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juego`
--

LOCK TABLES `juego` WRITE;
/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
/*!40000 ALTER TABLE `juego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juego_compania`
--

DROP TABLE IF EXISTS `juego_compania`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juego_compania` (
  `idjuego` int NOT NULL,
  `id_comp` int NOT NULL,
  PRIMARY KEY (`idjuego`,`id_comp`),
  KEY `id_juego_comania_id_compania_idx` (`id_comp`),
  CONSTRAINT `id_juego_comania_id_compania` FOREIGN KEY (`id_comp`) REFERENCES `compania` (`idcompania`),
  CONSTRAINT `id_juego_compania_id_juego` FOREIGN KEY (`idjuego`) REFERENCES `juego` (`idjuego`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juego_compania`
--

LOCK TABLES `juego_compania` WRITE;
/*!40000 ALTER TABLE `juego_compania` DISABLE KEYS */;
/*!40000 ALTER TABLE `juego_compania` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `idpersona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `contrasenia` varchar(60) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `foto_perfil` varchar(45) DEFAULT NULL,
  `rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Lucas Rodriguez','admin123','lucas.rod@mail.com','default.jpg','admin'),(2,'Martina Garcia','user456','marti.garcia@mail.com','default.jpg','user'),(3,'Julian Alvarez','gol2022','laaraña@mail.com','default.jpg','user'),(4,'Sofia Benitez','pass789','sofi.b@mail.com','default.jpg','user'),(5,'Mateo Lopez','root.pass','m.lopez@mail.com','default.jpg','admin'),(6,'Valentina Paz','valen123','vpaz@mail.com','default.jpg','user'),(7,'Nicolas Perez','nico99','nicolas.p@mail.com','default.jpg','user'),(8,'Elena Gomez','elena.admin','egomez@mail.com','default.jpg','admin'),(9,'Tomas Ruiz','tomi_user','truiz@mail.com','default.jpg','user'),(10,'Camila Sosa','cami.pass','csosa@mail.com','default.jpg','user');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propuesta`
--

DROP TABLE IF EXISTS `propuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propuesta` (
  `idpropuesta` int NOT NULL AUTO_INCREMENT,
  `nombrejuego` varchar(45) DEFAULT NULL,
  `descripcionjuego` varchar(255) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_administrador` int DEFAULT NULL,
  PRIMARY KEY (`idpropuesta`),
  KEY `id_propuesta_id_admin_idx` (`id_administrador`),
  KEY `id_propuesta_id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_propuesta_id_admin` FOREIGN KEY (`id_administrador`) REFERENCES `persona` (`idpersona`),
  CONSTRAINT `id_propuesta_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propuesta`
--

LOCK TABLES `propuesta` WRITE;
/*!40000 ALTER TABLE `propuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `propuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resenia`
--

DROP TABLE IF EXISTS `resenia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resenia` (
  `id_juego` int NOT NULL,
  `id_usuario` int NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `hora` varchar(45) DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(1500) DEFAULT NULL,
  `puntaje` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_juego`,`id_usuario`),
  KEY `id_resenia_id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_resenia_id_juego` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`idjuego`),
  CONSTRAINT `id_resenia_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `persona` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resenia`
--

LOCK TABLES `resenia` WRITE;
/*!40000 ALTER TABLE `resenia` DISABLE KEYS */;
/*!40000 ALTER TABLE `resenia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-04  7:02:32
