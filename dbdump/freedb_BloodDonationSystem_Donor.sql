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
-- Table structure for table `Donor`
--

DROP TABLE IF EXISTS `Donor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Donor` (
  `Donor_id` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `DateOfBirth` datetime DEFAULT NULL,
  `Gender` varchar(2) NOT NULL,
  `PhoneNumber` int DEFAULT NULL,
  `BloodType_id_fk` int NOT NULL,
  `fk_Hospital` int NOT NULL,
  `AlreadyDonated` varchar(3) NOT NULL,
  PRIMARY KEY (`Donor_id`),
  UNIQUE KEY `FullName_UNIQUE` (`FullName`),
  KEY `Blood_type_idx` (`BloodType_id_fk`),
  KEY `BloodType_id_idx` (`BloodType_id_fk`),
  KEY `fk_Hospital_id_idx` (`fk_Hospital`),
  CONSTRAINT `BloodType_id_fk` FOREIGN KEY (`BloodType_id_fk`) REFERENCES `Blood` (`Blood_id`),
  CONSTRAINT `fk_Hospital` FOREIGN KEY (`fk_Hospital`) REFERENCES `Hospital` (`Hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Donor`
--

LOCK TABLES `Donor` WRITE;
/*!40000 ALTER TABLE `Donor` DISABLE KEYS */;
INSERT INTO `Donor` VALUES (1,'Azra Žunic','azraz','2002-01-25 02:00:00','F',61056103,7,1,'YES'),(2,'Adna Herak','adnah','2001-12-22 01:00:00','F',61203769,8,2,'NO'),(3,'Amina Hajric','aminah','2003-06-21 03:00:00','F',62410606,7,3,'NO'),(4,'Anida Mulaomerovic','anidam','2000-11-14 02:00:00','F',62361351,4,1,'NO'),(6,'Faris Žunic','farisz','2004-02-13 04:00:00','M',603577424,6,3,'YES'),(7,'Benjamin Kablar','benjamink','2000-08-06 02:00:00','M',603013380,3,1,'NO'),(8,'Muhamed Gazija','muhamedg','2001-09-11 02:00:00','M',61985862,7,2,'YES'),(9,'Saida Kurtanovic','saidak','2001-10-14 01:00:00','F',62551007,4,3,'NO'),(10,'Lejla Jakupovic','lejlaj','2002-03-14 02:00:00','F',62830333,2,2,'YES');
/*!40000 ALTER TABLE `Donor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-31 10:32:48
