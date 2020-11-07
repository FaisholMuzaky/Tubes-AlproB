-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: parkir_
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `area` (
  `IdArea` int(11) NOT NULL AUTO_INCREMENT,
  `namaArea` varchar(255) NOT NULL,
  PRIMARY KEY (`IdArea`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (7,'area6'),(8,'area7'),(9,'area8'),(10,'areaParkir9'),(11,'areaParkir10'),(12,'area11'),(13,'area12');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `deleteGarage` AFTER DELETE ON `area` FOR EACH ROW DELETE FROM garage WHERE garage.IdArea = old.IdArea */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `garage`
--

DROP TABLE IF EXISTS `garage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `garage` (
  `IdArea` int(11) NOT NULL,
  `IdGarage` int(11) NOT NULL AUTO_INCREMENT,
  `namaGarage` varchar(255) NOT NULL,
  `tarifMobil` int(11) NOT NULL,
  `tarifMotor` int(11) DEFAULT NULL,
  `hariOperasi` int(11) NOT NULL,
  `jamBuka` int(11) NOT NULL,
  `jamTutup` int(11) NOT NULL,
  PRIMARY KEY (`IdGarage`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garage`
--

LOCK TABLES `garage` WRITE;
/*!40000 ALTER TABLE `garage` DISABLE KEYS */;
INSERT INTO `garage` VALUES (7,10,'garasi1',3500,2000,3,8,17),(8,11,'garasi2',3000,2000,2,7,12),(9,18,'asd',3300,2200,4,4,4),(9,19,'awe',3400,1850,4,3,5),(11,20,'parki1',2000,1900,5,5,17),(12,21,'asd',2000,1400,5,7,15),(12,22,'awe',2000,1000,5,7,12);
/*!40000 ALTER TABLE `garage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kendaraan`
--

DROP TABLE IF EXISTS `kendaraan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `kendaraan` (
  `idKendaraan` int(11) NOT NULL AUTO_INCREMENT,
  `idPengguna` int(11) NOT NULL,
  `nomorKendaraan` varchar(255) NOT NULL,
  `tipeKendaraan` varchar(255) NOT NULL,
  PRIMARY KEY (`idKendaraan`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kendaraan`
--

LOCK TABLES `kendaraan` WRITE;
/*!40000 ALTER TABLE `kendaraan` DISABLE KEYS */;
INSERT INTO `kendaraan` VALUES (1,4,'DC 1234 Z','Mobil'),(2,4,'DC 1252 A','Mobil'),(4,4,'DC 123 ZR','Motor'),(5,4,'DC 221 CD','Motor'),(7,4,'BD 5571 C','Mobil'),(9,4,'CD 123 CV','Motor'),(11,7,'BD 1884 C','Mobil'),(12,9,'TB 2314 R','Mobil'),(13,9,'TT 1234 B','Mobil');
/*!40000 ALTER TABLE `kendaraan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkir`
--

DROP TABLE IF EXISTS `parkir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `parkir` (
  `idParkir` int(11) NOT NULL AUTO_INCREMENT,
  `idKendaraan` int(11) NOT NULL,
  `idGarage` int(11) NOT NULL,
  `timeStart` timestamp NOT NULL,
  `timeStop` timestamp NULL DEFAULT NULL,
  `durasi` double DEFAULT NULL,
  `totalTransaksi` int(11) DEFAULT NULL,
  PRIMARY KEY (`idParkir`),
  KEY `parkir_FK` (`idGarage`),
  KEY `parkir_FK_1` (`idKendaraan`),
  CONSTRAINT `parkir_FK` FOREIGN KEY (`idGarage`) REFERENCES `garage` (`idgarage`),
  CONSTRAINT `parkir_FK_1` FOREIGN KEY (`idKendaraan`) REFERENCES `kendaraan` (`idkendaraan`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkir`
--

LOCK TABLES `parkir` WRITE;
/*!40000 ALTER TABLE `parkir` DISABLE KEYS */;
INSERT INTO `parkir` VALUES (24,13,10,'2020-11-03 21:55:49','2020-11-03 21:56:10',0.3499999940395355,2000),(25,13,20,'2020-11-03 22:04:54','2020-11-03 22:05:10',0.2666666805744171,2000),(26,13,19,'2020-11-03 22:13:39','2020-11-03 22:14:13',0.5666666626930237,2000),(27,13,21,'2020-11-03 22:16:25','2020-11-03 22:16:32',0.11666666716337204,2000),(28,13,18,'2020-11-04 01:56:18','2020-11-04 01:56:28',0.1666666716337204,2000),(29,13,11,'2020-11-04 02:11:03','2020-11-04 02:11:11',0.13333334028720856,2000),(30,13,11,'2020-11-04 02:16:02','2020-11-04 02:16:26',0.4000000059604645,2000),(31,13,19,'2020-11-04 04:54:47','2020-11-04 04:55:01',1,2012),(32,13,20,'2020-11-04 04:57:31','2020-11-04 04:57:39',0,4000),(33,13,11,'2020-11-04 09:34:35','2020-11-04 09:35:05',1,3000),(34,13,10,'2020-11-04 09:35:59','2020-11-04 09:36:06',1,3500),(35,13,10,'2020-11-04 09:37:38','2020-11-04 09:37:41',0,3500),(36,13,20,'2020-11-04 09:38:46','2020-11-04 09:38:50',0,4000),(37,13,10,'2020-11-04 09:42:32','2020-11-04 09:42:36',0,3500),(38,13,10,'2020-11-04 09:46:03','2020-11-04 09:46:08',0,3500),(39,13,22,'2020-11-04 09:48:20','2020-11-04 09:48:26',0,4000),(40,13,21,'2020-11-04 09:53:30','2020-11-04 09:53:39',0,4000),(41,13,22,'2020-11-05 02:01:30','2020-11-05 02:01:42',0,4000),(42,13,22,'2020-11-05 02:02:16','2020-11-05 02:02:31',0,4000),(43,13,10,'2020-11-05 02:02:42','2020-11-05 02:02:46',0,3500),(44,13,10,'2020-11-05 02:03:12','2020-11-05 02:03:15',0,3500),(45,13,10,'2020-11-05 02:04:13','2020-11-05 02:04:17',0,3500),(46,13,18,'2020-11-05 03:09:57','2020-11-05 03:10:04',1,2002),(47,13,18,'2020-11-05 03:18:37','2020-11-05 03:18:41',0,2002),(48,13,11,'2020-11-05 03:30:11','2020-11-05 03:30:20',0,3000),(49,13,20,'2020-11-05 03:30:48','2020-11-05 03:30:56',0,4000),(50,13,11,'2020-11-06 00:36:00','2020-11-06 00:36:05',0,3000),(51,12,20,'2020-11-06 04:04:37','2020-11-06 04:04:44',0,4000),(52,13,20,'2020-09-02 02:20:13','2020-11-06 04:33:53',13,4000),(53,12,21,'2020-11-06 03:00:00','2020-11-06 05:38:10',38,4000),(54,13,21,'2020-11-05 16:59:00','2020-11-06 05:52:40',12,26000),(55,13,20,'2020-11-05 05:00:00','2020-11-06 05:59:55',1499,50000),(56,12,21,'2020-11-04 01:34:12','2020-11-06 06:03:22',3149,106000),(57,12,20,'2020-11-06 00:00:00','2020-11-06 08:02:00',482,18000),(58,13,20,'2020-11-06 01:00:00','2020-11-06 08:11:10',431,16000),(59,13,20,'2020-11-06 01:00:00','2020-11-06 08:16:34',436,16000),(60,12,20,'2020-11-06 01:00:00','2020-11-06 08:19:18',439,16000),(61,12,20,'2020-11-06 01:00:00','2020-11-06 08:27:57',7,16000),(62,13,20,'2020-11-06 00:30:00','2020-11-06 08:31:13',8,18000),(63,13,20,'2020-11-06 00:30:00','2020-11-06 08:32:27',8,18000);
/*!40000 ALTER TABLE `parkir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pengguna`
--

DROP TABLE IF EXISTS `pengguna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pengguna` (
  `idPengguna` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `subscription` varchar(255) NOT NULL DEFAULT 'easy',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user',
  PRIMARY KEY (`idPengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengguna`
--

LOCK TABLES `pengguna` WRITE;
/*!40000 ALTER TABLE `pengguna` DISABLE KEYS */;
INSERT INTO `pengguna` VALUES (1,'Faisal','faisal@faisal.com','cikoneng','a6e4c7d55955494f0a8228a50b92a678','easy','user'),(4,'Fadil Ganteng','fadil@fadil.com','Sukapura','3c862ff9c3bd1296f98f2d771d198537','plus','user'),(5,'asd','asd@asd.com','asda','31b69a7494a0eec4ac544fd648c9d604','easy','user'),(6,'Dimas','dimas@dimas.com','Cikoneng','bc3e806c4f220f431fd5759102276ea6','easy','user'),(7,'Farhan','farhan@farhan.com','Sukabirus','9cf452b375e430338103a9c5cff21462','plus','user'),(8,'admin','admin','-','0baea2f0ae20150db78f58cddac442a9','-','admin'),(9,'Peter','peter@parker.com','Namex','56f323e456d774d93e68442000570af3','easy','user'),(10,'Peterr','peter@parkir.com','Sokanagara','56f323e456d774d93e68442000570af3','easy','admin');
/*!40000 ALTER TABLE `pengguna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'parkir_'
--
/*!50003 DROP PROCEDURE IF EXISTS `getHistory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`walim`@`localhost` PROCEDURE `getHistory`(IN _idPengguna INT)
BEGIN
	SELECT
-- 		a.idParkir ,
-- 		h.namaArea AS area,
-- 		g.namaGarage AS garage,
-- 		b.nomorKendaraan ,
-- 		b.tipeKendaraan ,
-- 		a.timeStart AS mulai,
-- 		a.timeStop AS selesai,
-- 		a.durasi AS durasi,
-- 		a.totalTransaksi AS total
		*
	FROM parkir a
	LEFT JOIN kendaraan b ON a.idKendaraan = b.idKendaraan 
	LEFT JOIN pengguna c ON b.idPengguna = c.idPengguna 
	LEFT JOIN garage g ON a.idGarage = g.IdGarage 
	LEFT JOIN area h ON g.IdArea = h.IdArea 
	WHERE c.idPengguna = _idPengguna;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `laporanTransaksi` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`walim`@`localhost` PROCEDURE `laporanTransaksi`(IN periode INT)
BEGIN
	IF periode=0 THEN
		SELECT
			DATE(a.timeStart) as tanggal,
			c.nama ,
			h.namaArea,
			g.namaGarage ,
			SUM(a.durasi) as durasi ,
			SUM(a.totalTransaksi) as totalTransaksi
		FROM parkir a
		LEFT JOIN kendaraan b ON a.idKendaraan = b.idKendaraan 
		LEFT JOIN pengguna c ON b.idPengguna = c.idPengguna 
		LEFT JOIN garage g ON a.idGarage = g.IdGarage 
		LEFT JOIN area h ON g.IdArea = h.IdArea
		GROUP BY
			DATE(a.timeStart),
			c.idPengguna ,
			c.nama ,
			h.IdArea ,
			h.namaArea,
			g.IdGarage ,
			g.namaGarage
		ORDER BY
			DATE(a.timeStart);
	ELSE 
		IF periode=1 THEN
			SELECT
				YEAR(a.timeStart) as tahun,
				WEEK(a.timeStart) as minggu, 
				c.nama ,
				h.namaArea,
				g.namaGarage ,
				SUM(a.durasi) as durasi,
				SUM(a.totalTransaksi) as totalTransaksi 
			FROM parkir a
			LEFT JOIN kendaraan b ON a.idKendaraan = b.idKendaraan 
			LEFT JOIN pengguna c ON b.idPengguna = c.idPengguna 
			LEFT JOIN garage g ON a.idGarage = g.IdGarage 
			LEFT JOIN area h ON g.IdArea = h.IdArea
			GROUP BY
				YEAR(a.timeStart),
				WEEK(a.timeStart),
				c.idPengguna ,
				c.nama ,
				h.IdArea ,
				h.namaArea,
				g.IdGarage ,
				g.namaGarage
			ORDER BY
				YEAR(a.timeStart),
				WEEK(a.timeStart);
		ELSE
			SELECT
				YEAR(a.timeStart) as tahun,
				MONTH(a.timeStart) as bulan, 
				c.nama ,
				h.namaArea,
				g.namaGarage ,
				SUM(a.durasi) as durasi ,
				SUM(a.totalTransaksi) as totalTransaksi
			FROM parkir a
			LEFT JOIN kendaraan b ON a.idKendaraan = b.idKendaraan 
			LEFT JOIN pengguna c ON b.idPengguna = c.idPengguna 
			LEFT JOIN garage g ON a.idGarage = g.IdGarage 
			LEFT JOIN area h ON g.IdArea = h.IdArea
			GROUP BY
				YEAR(a.timeStart),
				MONTH(a.timeStart),
				c.idPengguna ,
				c.nama ,
				h.IdArea ,
				h.namaArea,
				g.IdGarage ,
				g.namaGarage
			ORDER BY
				YEAR(a.timeStart),
				MONTH(a.timeStart);
		END IF;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setParkir` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`walim`@`localhost` PROCEDURE `setParkir`(
	IN _idKendaraan INT, IN _idGarage INT, IN _timeStart DATETIME, IN _timeStop DATETIME
)
BEGIN
	DECLARE _subscription varchar(30);
	DECLARE _totalTransaksi INT;
	DECLARE _hoursInterval FLOAT;
	
	SET _subscription = (SELECT p.subscription 
	FROM kendaraan k 
	JOIN pengguna p ON k.idPengguna = p.idPengguna 
	WHERE k.idKendaraan = _idKendaraan);
	
	SET _hoursInterval = TIME_TO_SEC(TIMEDIFF(_timeStop,_timeStart))/60;
	
	IF UPPER(_subscription)=UPPER('easy') THEN
		IF _hoursInterval<1 THEN
			SET _totalTransaksi = 2000;
		ELSE
			SET _totalTransaksi = _hoursInterval*2000;
		END IF;
	END IF;
	
	INSERT INTO parkir
		(idKendaraan,idGarage,timeStart,timeStop,durasi,totalTransaksi)
	VALUES
		(_idKendaraan,_idGarage,_timeStart,_timeStop,_hoursInterval,_totalTransaksi);
		
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateParkir` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`walim`@`localhost` PROCEDURE `updateParkir`(
	IN _idParkir INT, IN _idKendaraan INT, IN _idGarage INT, IN _timeStop DATETIME
)
BEGIN
	UPDATE parkir
	SET
		idKendaraan = _idKendaraan ,
		idGarage  = _idGarage,
		timeStop = _timeStop
	WHERE idParkir = _idParkir;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `viewParkir` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`walim`@`localhost` PROCEDURE `viewParkir`(IN _idParkir INT)
BEGIN
	SELECT
		a.timeStart ,
		a.timeStop ,
		a.durasi ,
		a.totalTransaksi ,
		b.nomorKendaraan ,
		b.tipeKendaraan ,
		e.nama ,
		e.subscription ,
		c.namaGarage ,
		c.tarif ,
		d.namaArea 
	FROM
		parkir a
	LEFT JOIN kendaraan b ON a.idKendaraan = b.idKendaraan 
	LEFT JOIN garage c ON a.idGarage = c.idGarage 
	LEFT JOIN area d ON c.idArea = d.idArea 
	LEFT JOIN pengguna e ON b.idPengguna = e.idPengguna
	WHERE a.idParkir = _idParkir;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-07 21:28:36
