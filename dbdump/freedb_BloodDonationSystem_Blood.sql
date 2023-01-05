-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_BloodDonationSystem
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `Blood`
--

DROP TABLE IF EXISTS `Blood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Blood` (
  `Blood_id` int NOT NULL,
  `BloodGroup` varchar(3) NOT NULL,
  `BloodBagNumber` varchar(10) NOT NULL,
  `DonateDate` datetime NOT NULL,
  `BloodAmount` int NOT NULL,
  `fk_hospital_id` int NOT NULL,
  PRIMARY KEY (`Blood_id`),
  UNIQUE KEY `BloodType_UNIQUE` (`Blood_id`),
  UNIQUE KEY `BloodBagNumber_UNIQUE` (`BloodBagNumber`),
  KEY `fk_hospital_constraint_idx` (`fk_hospital_id`),
  CONSTRAINT `Hospital_id_constraint` FOREIGN KEY (`fk_hospital_id`) REFERENCES `Hospital` (`Hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Blood`
--

LOCK TABLES `Blood` WRITE;
/*!40000 ALTER TABLE `Blood` DISABLE KEYS */;
INSERT INTO `Blood` VALUES (1,'','QRE-7-GH','2004-12-20 22:00:00',350,3),(2,'','BNJ-7-JH','2030-11-20 22:00:00',450,2),(3,'','BDF-7-GH','2014-11-20 22:00:00',450,1),(4,'','GRF-8-JH','2015-11-20 22:00:00',455,1),(5,'','RTE-8-JH','2009-06-20 22:00:00',450,1),(6,'','FDR-7-JH','2030-11-20 22:00:00',350,3),(7,'','KZT-7-JH','2007-10-20 22:00:00',450,1),(8,'','HTZ-8-GH','2007-10-20 22:00:00',460,2);
/*!40000 ALTER TABLE `Blood` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-27 21:37:57
