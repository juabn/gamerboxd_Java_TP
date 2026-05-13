-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: gamerboxd
-- ------------------------------------------------------
-- Server version	8.0.42

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
  PRIMARY KEY (`idcompania`)
) ENGINE=InnoDB AUTO_INCREMENT=457760 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compania`
--

LOCK TABLES `compania` WRITE;
/*!40000 ALTER TABLE `compania` DISABLE KEYS */;
INSERT INTO `compania` VALUES (4,'Bethesda Softworks'),(10,'Rockstar Games'),(24,'CD PROJEKT'),(237,'505 Games'),(343,'id Software'),(383,'2K'),(568,'Playdead'),(694,'Telltale Games'),(1072,'Digital Extremes'),(1492,'Double Eleven'),(1612,'Valve Software'),(1757,'Codeglue'),(2347,'Turtle Rock Studios'),(2445,'Engine Software'),(3232,'Bungie'),(3524,'Rockstar North'),(3748,'Team Cherry'),(3813,'Monolith Productions'),(3900,'Hidden Path Entertainment'),(4015,'Gearbox Software'),(4037,'Crystal Dynamics'),(4066,'Pipeworks Studio'),(4149,'2K Australia'),(4207,'Bethesda Game Studios'),(4304,'4A Games'),(4512,'Re-Logic'),(4598,'Irrational Games'),(5113,'2K China'),(5114,'2K Marin'),(7693,'Escalation Studios'),(8770,'Tango Gameworks'),(9023,'CD PROJEKT RED'),(9157,'OVERKILL Software'),(9264,'DONTNOD Entertainment'),(9300,'Kojima Productions'),(10002,'Rocksteady Studios'),(10436,'War Drum Studios'),(14007,'Nixxes'),(14037,'Certain Affinity'),(14278,'Santa Monica Studio'),(15466,'Digital Domain'),(16852,'Psyonix'),(17132,'Aspyr Media'),(17202,'Guerrilla Games'),(18893,'Feral Interactive'),(19732,'Respawn Entertainment'),(19800,'BattleCry Studios'),(23342,'NVIDIA Lightspeed Studios'),(27789,'Sony Computer Entertainment America'),(28246,'Panic Button'),(457759,'鱼俞');
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
  `genero` varchar(45) DEFAULT NULL,
  `puntaje_promedio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idjuego`)
) ENGINE=InnoDB AUTO_INCREMENT=290857 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juego`
--

LOCK TABLES `juego` WRITE;
/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
INSERT INTO `juego` VALUES (28,'Red Dead Redemption 2','https://media.rawg.io/media/games/511/5118aff','America, 1899. The end of the wild west era h',NULL,NULL,NULL),(32,'Destiny 2','https://media.rawg.io/media/games/34b/34b1f18','Destiny 2 is an online multiplayer first-pers',NULL,NULL,NULL),(278,'Horizon Zero Dawn','https://media.rawg.io/media/games/b7d/b7d3f17','Horizon Zero Dawn is an experiment. A very im',NULL,NULL,NULL),(416,'Grand Theft Auto: San Andreas','https://media.rawg.io/media/games/960/960b601','Grand Theft Auto - San Andreas is the seventh',NULL,NULL,NULL),(422,'Terraria','https://media.rawg.io/media/games/f46/f466571','Terraria is a 2D action adventure sandbox gam',NULL,NULL,NULL),(766,'Warframe','https://media.rawg.io/media/games/f87/f87457e','Warframe is an online free-to-play cooperativ',NULL,NULL,NULL),(802,'Borderlands 2','https://media.rawg.io/media/games/49c/49c3dfa','Sequel to the 4-player cooperative FPS RPG Bo',NULL,NULL,NULL),(1030,'Limbo','https://media.rawg.io/media/games/942/9424d6b','This popular 2D puzzle-platformer creates the',NULL,NULL,NULL),(2454,'DOOM (2016)','https://media.rawg.io/media/games/587/587588c','Return of the classic FPS, Doom (2016) acts a',NULL,NULL,NULL),(3070,'Fallout 4','https://media.rawg.io/media/games/d82/d82990b','The fourth game in the post-apocalyptic actio',NULL,NULL,NULL),(3192,'Metal Gear Solid V: The Phantom Pain','https://media.rawg.io/media/games/490/49016e0','Metal Gear Solid 5 continues the story of MGS',NULL,NULL,NULL),(3272,'Rocket League','https://media.rawg.io/media/games/8cc/8cce7c0','Highly competitive soccer game with rocket-ca',NULL,NULL,NULL),(3287,'Batman: Arkham Knight','https://media.rawg.io/media/games/310/3106b0e','Batman: Arkham Knight is the final instalment',NULL,NULL,NULL),(3328,'The Witcher 3: Wild Hunt','https://media.rawg.io/media/games/618/618c203','The third game in a series, it holds nothing ',NULL,NULL,NULL),(3439,'Life is Strange','https://media.rawg.io/media/games/562/5625538','Interactive storytelling and plot-heavy games',NULL,NULL,NULL),(3498,'Grand Theft Auto V','https://media.rawg.io/media/games/20a/20aa03a','Rockstar Games went bigger, since their previ',NULL,NULL,NULL),(3939,'PAYDAY 2','https://media.rawg.io/media/games/73e/73eecb8','The gang is back, and they have bigger and be',NULL,NULL,NULL),(4062,'BioShock Infinite','https://media.rawg.io/media/games/fc1/fc1307a','The third game in the series, Bioshock takes ',NULL,NULL,NULL),(4200,'Portal 2','https://media.rawg.io/media/games/2ba/2bac0e8','Portal 2 is a first-person puzzle game develo',NULL,NULL,NULL),(4286,'BioShock','https://media.rawg.io/media/games/bc0/bc06a29','FPS with RPG elements, Bioshock invites playe',NULL,NULL,NULL),(4291,'Counter-Strike: Global Offensive','https://media.rawg.io/media/games/736/73619bd','Counter-Strike is a multiplayer phenomenon in',NULL,NULL,NULL),(4459,'Grand Theft Auto IV','https://media.rawg.io/media/games/4a0/4a0a131','Every crime story is a story of a search for ',NULL,NULL,NULL),(5286,'Tomb Raider','https://media.rawg.io/media/games/021/021c4e2','A cinematic revival of the series in its acti',NULL,NULL,NULL),(5679,'The Elder Scrolls V: Skyrim','https://media.rawg.io/media/games/7cf/7cfc922','The fifth game in the series, Skyrim takes us',NULL,NULL,NULL),(7689,'Rise of the Tomb Raider','https://media.rawg.io/media/games/b45/b45575f','Rise of the Tomb Raider is the eleventh entry',NULL,NULL,NULL),(9767,'Hollow Knight','https://media.rawg.io/media/games/4cf/4cfc6b7','Hollow Knight is a Metroidvania-type game dev',NULL,NULL,NULL),(10213,'Dota 2','https://media.rawg.io/media/games/6fc/6fcf4cd','What used to be an unofficial modded map for ',NULL,NULL,NULL),(11859,'Team Fortress 2','https://media.rawg.io/media/games/46d/46d98e6','TF2 is an objective based arena shooter with ',NULL,NULL,NULL),(11973,'Middle-earth: Shadow of Mordor','https://media.rawg.io/media/games/d1a/d1a2e99','Lord of the rings franchise brought a new tit',NULL,NULL,NULL),(12020,'Left 4 Dead 2','https://media.rawg.io/media/games/d58/d588947','Cooperative survival continues with a differe',NULL,NULL,NULL),(13536,'Portal','https://media.rawg.io/media/games/7fa/7fa0b58','Every single time you click your mouse while ',NULL,NULL,NULL),(13537,'Half-Life 2','https://media.rawg.io/media/games/b8c/b8c243e','Gordon Freeman became the most popular namele',NULL,NULL,NULL),(16944,'The Witcher 2: Assassins of Kings Enhanced Ed','https://media.rawg.io/media/games/6cd/6cd653e','The player is Geralt of Rivia, infamous monst',NULL,NULL,NULL),(17822,'The Witcher: Enhanced Edition Director\'s Cut','https://media.rawg.io/media/games/ee3/ee3e101','The Witcher is the very first instalment of t',NULL,NULL,NULL),(19103,'Half-Life 2: Lost Coast','https://media.rawg.io/media/games/b7b/b7b8381','Essentially a tech demo, “Half-Life 2: Lost C',NULL,NULL,NULL),(23027,'The Walking Dead: Season 1','https://media.rawg.io/media/games/8d6/8d69eb6','The Walking Dead is a five-part game series s',NULL,NULL,NULL),(29028,'Metro 2033','https://media.rawg.io/media/games/120/1201a40','Not all post-apocalyptic stories begin in the',NULL,NULL,NULL),(41494,'Cyberpunk 2077','https://media.rawg.io/media/games/26d/26d4437','Cyberpunk 2077 is a science fiction game loos',NULL,NULL,NULL),(58175,'God of War (2018)','https://media.rawg.io/media/games/4be/4be6a6a','It is a new beginning for Kratos. Living as a',NULL,NULL,NULL),(290856,'Apex Legends','https://media.rawg.io/media/games/737/737ea56','Conquer with character in Apex Legends, a fre',NULL,NULL,NULL);
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
INSERT INTO `juego_compania` VALUES (2454,4),(28,10),(3498,10),(41494,24),(3939,237),(2454,343),(802,383),(1030,568),(23027,694),(766,1072),(4286,1072),(1030,1492),(4200,1612),(4291,1612),(10213,1612),(11859,1612),(12020,1612),(13536,1612),(13537,1612),(19103,1612),(422,1757),(12020,2347),(422,2445),(32,3232),(416,3524),(3498,3524),(4459,3524),(9767,3748),(11973,3813),(4291,3900),(802,4015),(5286,4037),(7689,4037),(422,4066),(4062,4149),(4286,4149),(3070,4207),(5679,4207),(29028,4304),(422,4512),(4062,4598),(4286,4598),(4286,5113),(4286,5114),(2454,7693),(2454,8770),(3328,9023),(16944,9023),(17822,9023),(41494,9023),(3939,9157),(3439,9264),(3192,9300),(3287,10002),(416,10436),(7689,14007),(2454,14037),(58175,14278),(2454,15466),(3272,16852),(802,17132),(4062,17132),(278,17202),(7689,18893),(11973,18893),(290856,19732),(2454,19800),(13536,23342),(13537,23342),(802,27789),(766,28246),(2454,28246),(1030,457759);
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
-- Table structure for table `plataforma`
--

DROP TABLE IF EXISTS `plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plataforma` (
  `idplataforma` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idplataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
INSERT INTO `plataforma` VALUES (1,'Xbox One'),(3,'iOS'),(4,'PC'),(5,'macOS'),(6,'Linux'),(7,'Nintendo Switch'),(8,'Nintendo 3DS'),(10,'Wii U'),(14,'Xbox 360'),(15,'PlayStation 2'),(16,'PlayStation 3'),(18,'PlayStation 4'),(19,'PS Vita'),(21,'Android'),(80,'Xbox'),(171,'Web'),(186,'Xbox Series S/X'),(187,'PlayStation 5');
/*!40000 ALTER TABLE `plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma_juego`
--

DROP TABLE IF EXISTS `plataforma_juego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plataforma_juego` (
  `idplataforma` int NOT NULL,
  `idjuego` int NOT NULL,
  PRIMARY KEY (`idplataforma`,`idjuego`),
  KEY `fk_juego_idx` (`idjuego`),
  CONSTRAINT `fk_juego` FOREIGN KEY (`idjuego`) REFERENCES `juego` (`idjuego`) ON DELETE CASCADE,
  CONSTRAINT `fk_plataforma` FOREIGN KEY (`idplataforma`) REFERENCES `plataforma` (`idplataforma`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma_juego`
--

LOCK TABLES `plataforma_juego` WRITE;
/*!40000 ALTER TABLE `plataforma_juego` DISABLE KEYS */;
INSERT INTO `plataforma_juego` VALUES (1,28),(4,28),(18,28),(1,32),(4,32),(18,32),(171,32),(186,32),(187,32),(4,278),(18,278),(1,416),(3,416),(4,416),(5,416),(14,416),(15,416),(16,416),(18,416),(21,416),(80,416),(1,422),(3,422),(4,422),(5,422),(6,422),(7,422),(8,422),(10,422),(14,422),(16,422),(18,422),(19,422),(21,422),(1,766),(3,766),(4,766),(7,766),(18,766),(186,766),(187,766),(4,802),(5,802),(6,802),(14,802),(16,802),(19,802),(21,802),(1,1030),(3,1030),(4,1030),(5,1030),(6,1030),(7,1030),(14,1030),(16,1030),(18,1030),(19,1030),(21,1030),(1,2454),(4,2454),(7,2454),(18,2454),(1,3070),(4,3070),(18,3070),(187,3070),(1,3192),(4,3192),(14,3192),(16,3192),(18,3192),(1,3272),(4,3272),(5,3272),(6,3272),(7,3272),(18,3272),(1,3287),(4,3287),(7,3287),(18,3287),(1,3328),(4,3328),(5,3328),(7,3328),(18,3328),(186,3328),(187,3328),(1,3439),(3,3439),(4,3439),(5,3439),(6,3439),(14,3439),(16,3439),(18,3439),(21,3439),(1,3498),(4,3498),(14,3498),(16,3498),(18,3498),(186,3498),(187,3498),(1,3939),(4,3939),(6,3939),(1,4062),(4,4062),(6,4062),(7,4062),(14,4062),(16,4062),(18,4062),(1,4200),(4,4200),(5,4200),(6,4200),(14,4200),(16,4200),(4,4286),(5,4286),(14,4286),(16,4286),(4,4291),(6,4291),(14,4291),(16,4291),(1,4459),(4,4459),(14,4459),(16,4459),(4,5286),(5,5286),(14,5286),(16,5286),(1,5679),(4,5679),(7,5679),(14,5679),(16,5679),(18,5679),(186,5679),(187,5679),(1,7689),(4,7689),(5,7689),(18,7689),(1,9767),(4,9767),(5,9767),(6,9767),(7,9767),(18,9767),(4,10213),(5,10213),(6,10213),(4,11859),(5,11859),(6,11859),(1,11973),(4,11973),(5,11973),(6,11973),(14,11973),(16,11973),(18,11973),(4,12020),(5,12020),(6,12020),(14,12020),(4,13536),(5,13536),(6,13536),(7,13536),(14,13536),(16,13536),(21,13536),(4,13537),(5,13537),(6,13537),(14,13537),(21,13537),(80,13537),(4,16944),(5,16944),(14,16944),(4,17822),(5,17822),(4,19103),(5,19103),(6,19103),(1,23027),(3,23027),(4,23027),(5,23027),(7,23027),(14,23027),(16,23027),(18,23027),(19,23027),(21,23027),(4,29028),(14,29028),(1,41494),(4,41494),(7,41494),(18,41494),(186,41494),(187,41494),(4,58175),(18,58175),(1,290856),(4,290856),(5,290856),(7,290856),(18,290856);
/*!40000 ALTER TABLE `plataforma_juego` ENABLE KEYS */;
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

-- Dump completed on 2026-05-13 16:33:54
