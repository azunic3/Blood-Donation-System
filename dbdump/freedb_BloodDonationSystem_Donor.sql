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
  `Gender` varchar(2) DEFAULT NULL,
  `Adress` varchar(45) DEFAULT NULL,
  `PhoneNumber` int DEFAULT NULL,
  `BloodType_id` varchar(3) DEFAULT NULL,
  `fk_Hospital_id` int DEFAULT NULL,
  `AlreadyDonated` varchar(3) NOT NULL,
  PRIMARY KEY (`Donor_id`),
  KEY `Blood_type_idx` (`BloodType_id`),
  KEY `BloodType_id_idx` (`BloodType_id`),
  KEY `fk_Hospital_id_idx` (`fk_Hospital_id`),
  CONSTRAINT `BloodType_id` FOREIGN KEY (`BloodType_id`) REFERENCES `Blood` (`BloodType`),
  CONSTRAINT `fk_Hospital_constraint` FOREIGN KEY (`fk_Hospital_id`) REFERENCES `Hospital` (`Hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Donor`
--

LOCK TABLES `Donor` WRITE;
/*!40000 ALTER TABLE `Donor` DISABLE KEYS */;
INSERT INTO `Donor` VALUES (1,'Azra Žunic','azraz','2025-01-20 02:00:00','F','Sarajevo',61056103,'B+',1,'Y'),(2,'Adna Herak','adnah','2022-12-20 01:00:00','F','Sarajevo',61203769,'A-',2,'N'),(3,'Amina Hajric','aminah','2021-06-20 03:00:00','F','Sarajevo',62410606,'A+',3,'N'),(4,'Anida Mulaomerovic','anidam','2014-11-20 02:00:00','F','Sarajevo',62361351,'AB+',1,'N'),(5,'Muhamed Oruc','muhamedo','2004-05-20 00:00:00','M','Sarajevo',62868688,'AB-',2,'Y'),(6,'Faris Žunic','farisz','2013-02-20 04:00:00','M','Sarajevo',603577424,'B+',3,'Y'),(7,'Benjamin Kablar','benjamink','2006-08-20 02:00:00','M','Sarajevo',603013380,'0-',1,'N'),(8,'Muhamed Gazija','muhamedg','2011-09-20 02:00:00','M','Sarajevo',61985862,'0+',2,'Y'),(9,'Saida Kurtanovic','saidak','2014-10-20 01:00:00','F','Sarajevo',62551007,'A+',3,'N'),(10,'Lejla Jakupovic','lejlaj','2016-03-20 02:00:00','F','Sarajevo',62830333,'0+',2,'Y');
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

-- Dump completed on 2022-12-24 12:26:34
