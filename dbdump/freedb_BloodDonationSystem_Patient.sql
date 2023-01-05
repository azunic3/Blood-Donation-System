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
-- Table structure for table `Patient`
--

DROP TABLE IF EXISTS `Patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Patient` (
  `Patient_id` int NOT NULL AUTO_INCREMENT,
  `Full_Name` varchar(45) NOT NULL,
  `Gender` varchar(2) NOT NULL,
  `DateOfBirth` datetime NOT NULL,
  `Adress` varchar(45) DEFAULT NULL,
  `PhoneNumber` decimal(10,0) DEFAULT NULL,
  `fk_BloodType` int NOT NULL,
  `fk_Hospital_id` int NOT NULL,
  PRIMARY KEY (`Patient_id`),
  UNIQUE KEY `FullName_UNIQUE` (`Full_Name`),
  KEY `BloodType_idx` (`fk_BloodType`),
  KEY `fk_Hospital_id` (`fk_Hospital_id`),
  CONSTRAINT `fk_BloodType` FOREIGN KEY (`fk_BloodType`) REFERENCES `Blood` (`Blood_id`),
  CONSTRAINT `fk_Hospital_id` FOREIGN KEY (`fk_Hospital_id`) REFERENCES `Hospital` (`Hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Patient`
--

LOCK TABLES `Patient` WRITE;
/*!40000 ALTER TABLE `Patient` DISABLE KEYS */;
INSERT INTO `Patient` VALUES (1,'Feriha Babic','F','2014-03-20 02:00:00','Sarajevo',62123541,1,2),(2,'Aisa Focak','F','2004-02-20 03:00:00','Sarajevo',61214563,2,1),(3,'Safija Tadefi','F','2014-05-20 02:00:00','Sarajevo',61256203,2,2),(4,'Farah Cosic','F','2006-03-20 02:00:00','Sarajevo',62143526,3,3),(5,'Lejla Kahriman','F','2018-07-20 02:00:00','Sarajevo',602589963,4,1),(6,'Amina Hromic ','F','2015-07-20 02:00:00','Sarajevo',605239867,4,2),(7,'Nerma Kadric','F','2029-07-20 02:00:00','Sarajevo',62147852,5,3),(8,'Dzejlan Zdralovic','F','2017-02-20 02:00:00','Sarajevo',62335412,7,3),(9,'Adla Halilovic','F','2010-01-20 02:00:00','Sarajevo',61052631,8,1);
/*!40000 ALTER TABLE `Patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-27 21:37:59
