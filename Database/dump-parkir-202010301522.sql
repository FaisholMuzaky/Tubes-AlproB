-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: parkir
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
  `idArea` int(11) NOT NULL AUTO_INCREMENT,
  `namaArea` varchar(100) NOT NULL,
  PRIMARY KEY (`idArea`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Area1');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garage`
--

DROP TABLE IF EXISTS `garage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `garage` (
  `idGarage` int(11) NOT NULL AUTO_INCREMENT,
  `idArea` int(11) NOT NULL,
  `namaGarage` varchar(100) DEFAULT NULL,
  `hariOperasi` int(11) DEFAULT NULL,
  `jamBuka` time DEFAULT NULL,
  `jamTutup` time DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idGarage`),
  KEY `garage_FK` (`idArea`),
  CONSTRAINT `garage_FK` FOREIGN KEY (`idArea`) REFERENCES `area` (`idarea`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garage`
--

LOCK TABLES `garage` WRITE;
/*!40000 ALTER TABLE `garage` DISABLE KEYS */;
INSERT INTO `garage` VALUES (1,1,'Garage1',7,'07:00:00','24:00:00',1);
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
  `platNomor` varchar(100) DEFAULT NULL,
  `tipeKendaraan` varchar(100) DEFAULT NULL,
  `idPemilik` int(11) NOT NULL,
  PRIMARY KEY (`idKendaraan`),
  KEY `kendaraan_FK` (`idPemilik`),
  CONSTRAINT `kendaraan_FK` FOREIGN KEY (`idPemilik`) REFERENCES `pengguna` (`idpengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kendaraan`
--

LOCK TABLES `kendaraan` WRITE;
/*!40000 ALTER TABLE `kendaraan` DISABLE KEYS */;
INSERT INTO `kendaraan` VALUES (1,'D 6929 ZA','Motor',15);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkir`
--

LOCK TABLES `parkir` WRITE;
/*!40000 ALTER TABLE `parkir` DISABLE KEYS */;
INSERT INTO `parkir` VALUES (1,1,1,'2020-10-29 22:49:32','2020-10-30 01:49:32',NULL,NULL),(2,1,1,'2020-10-29 23:08:21','2020-10-30 02:49:32',3.6863889694213867,7373),(3,1,1,'2020-10-30 05:29:55','2020-10-30 05:29:57',0.0005555549869313836,1),(4,1,1,'2020-10-30 05:29:55','2020-10-30 05:29:57',0.0005555549869313836,1),(5,1,1,'2020-10-30 05:32:46','2020-10-30 05:32:47',0.00027777699870057404,1),(6,1,1,'2020-10-30 05:36:36','2020-10-30 05:36:37',0.00027777699870057404,1),(7,1,1,'2020-10-30 05:37:47','2020-10-30 05:37:48',0.00027777699870057404,1),(8,1,1,'2020-10-30 06:24:06','2020-10-30 06:24:07',0.00027777699870057404,1),(9,1,1,'2020-10-30 06:31:16','2020-10-30 06:31:31',0.004166665952652693,8),(10,1,1,'2020-10-30 06:32:57','2020-10-30 06:33:13',0.004444444086402655,9),(11,1,1,'2020-10-30 06:33:48','2020-10-30 06:34:02',0.0038888880517333746,8),(12,1,1,'2020-10-30 06:34:51','2020-10-30 06:34:58',0.0019444440258666873,4),(13,1,1,'2020-10-30 06:37:24','2020-10-30 06:37:31',0.0019444440258666873,4),(14,1,1,'2020-10-30 06:41:41','2020-10-30 06:41:43',0.0005555549869313836,1),(15,1,1,'2020-10-30 06:43:34','2020-10-30 06:43:38',0.0011111110216006637,2),(16,1,1,'2020-10-30 06:44:50','2020-10-30 06:44:52',0.0005555549869313836,1),(17,1,1,'2020-10-30 06:50:48','2020-10-30 06:50:51',0.05000000074505806,2000),(18,1,1,'2020-10-30 06:53:26','2020-10-30 06:53:32',0.10000000149011612,2000),(19,1,1,'2020-10-30 06:55:25','2020-10-30 06:55:30',0.0833333358168602,2000),(20,1,1,'2020-10-30 06:57:02','2020-10-30 06:57:07',0.0833333358168602,2000),(21,1,1,'2020-10-30 07:21:44','2020-10-30 07:22:11',0.44999998807907104,2000),(22,1,1,'2020-10-30 07:47:54','2020-10-30 07:47:57',0.05000000074505806,2000),(23,1,1,'2020-10-30 08:16:24','2020-10-30 08:16:27',0.05000000074505806,2000);
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
  PRIMARY KEY (`idPengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengguna`
--

LOCK TABLES `pengguna` WRITE;
/*!40000 ALTER TABLE `pengguna` DISABLE KEYS */;
INSERT INTO `pengguna` VALUES (1,'faisal','faisaall@muzakii.com','cikoneng','3f0371e01d75ce9b788cac9beb4ff92a',''),(8,'faisal','faisal@muzaki.com','cikoneng','3f0371e01d75ce9b788cac9beb4ff92a',''),(11,'Walim','fadil@fadil.com','Bogor','27622e6109d0abc1c6c6ff2b32a2cc82',''),(12,'Walim','walim12@walim.co.id','Bekasi','462376e6510f1006cee7bad8a834dcbe','easy'),(13,'Faisal','faisal@faisal.com','Bandung','a474e17de5a2352cb6654e59adc4f4fe','easy'),(14,'Dimas','dimas@dimas.com','Cikoneng','166e3e7a609da761fd76e4f64f7323a6','easy'),(15,'Peter','peter@parker.com','Bekesong','56f323e456d774d93e68442000570af3','easy');
/*!40000 ALTER TABLE `pengguna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'parkir'
--
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
	JOIN pengguna p ON k.idPemilik = p.idPengguna 
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
		b.platNomor ,
		b.tipeKendaraan ,
		e.nama ,
		c.namaGarage ,
		d.namaArea 
	FROM
		parkir a
	LEFT JOIN kendaraan b ON a.idKendaraan = b.idKendaraan 
	LEFT JOIN garage c ON a.idGarage = c.idGarage 
	LEFT JOIN area d ON c.idArea = d.idArea 
	LEFT JOIN pengguna e ON b.idPemilik = e.idPengguna
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

-- Dump completed on 2020-10-30 15:22:24
