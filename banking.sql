CREATE DATABASE  IF NOT EXISTS `banking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `banking`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: banking
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `idbankaccount` int NOT NULL AUTO_INCREMENT,
  `account_no` int DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `fk_user_id` int NOT NULL,
  PRIMARY KEY (`idbankaccount`),
  UNIQUE KEY `idbank_account_UNIQUE` (`idbankaccount`),
  KEY `fk_user_id_idx` (`fk_user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,155,1311,6),(3,16638,503,16),(4,1000,684,16),(5,155453676,290,15),(6,1395231641,96,15),(7,1214154805,10,15),(8,355555555,45,7),(9,565655577,46,8),(10,647777777,777,9),(11,767575767,77,10),(12,435556666,66,11),(13,666666663,77,12),(14,333346677,0,13),(15,111253645,6,14),(16,555555097,5,17),(17,556444444,12333,5),(18,1274922903,0,19),(19,1132467587,0,20),(20,1108031301,0,21),(21,1286936019,0,6),(22,1584669172,0,22),(23,1233337958,0,22);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `uname` varchar(20) DEFAULT NULL,
  `umail` varchar(30) DEFAULT NULL,
  `upass` varchar(20) DEFAULT NULL,
  `ucountry` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES ('sandeep','sandy05.1991@gmail.com','welcome','India'),('rahul','rahul@gmail.com','123','India'),('sandeep','sandy05.1991@gmail.com','welcome','India'),('rahul','rahul@gmail.com','123','India');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `idtransaction` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `amount` double NOT NULL,
  `fr_account_no` varchar(20) DEFAULT NULL,
  `to_account_no` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idtransaction`),
  UNIQUE KEY `idtransaction_UNIQUE` (`idtransaction`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2023-01-15',NULL,50,'1000','16638'),(2,'2023-01-15',NULL,12,'1000','155453676'),(3,'2023-01-15',NULL,56,'155453676','1000'),(4,'2023-01-15',NULL,56,'155','1000'),(5,'2023-01-15','Credit',96,'155453676','155'),(6,'2023-01-15','Credit',96,'1000','155'),(7,'2023-01-15',NULL,96,'155453676','1000'),(8,'2023-01-15',NULL,96,'155453676','16638'),(9,'2023-01-15',NULL,96,'155','16638'),(10,'2023-01-15',NULL,96,'155','16638'),(11,'2023-01-15',NULL,96,'155','16638'),(12,'2023-01-20','Credit',45,'Officer','155'),(13,'2023-01-20','Credit',5,'155453676','1214154805'),(14,'2023-01-20','Debit',5,'1214154805','155453676'),(15,'2023-01-31','Credit',36,'155453676','1395231641'),(16,'2023-01-31','Debit',36,'1395231641','155453676'),(17,'2023-02-01','Credit',20,'155453676','1395231641'),(18,'2023-02-01','Debit',20,'1395231641','155453676');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `idtype` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtype`),
  UNIQUE KEY `idtype_UNIQUE` (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'client'),(2,'frontdesk'),(3,'manager');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` int NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` int DEFAULT '1',
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`),
  KEY `fk_id_type_idx` (`type`),
  CONSTRAINT `fk_id_type` FOREIGN KEY (`type`) REFERENCES `type` (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'wassim','ahlsomething','10 maryse bestie','wassimahlb@gmail.fr',758300195,'cliyon',1),(6,'nidhi','acharaa','34 rue celestine','nidhigurl@gmail.fr',698044996,'cliyon',1),(7,'krish','something','rue night party','krish@goog.fr',987665433,'14twuwdiw',1),(8,'krishdfg','rrrr','rue night party','krish@goog.fr',987665433,'14hjuwdiw',1),(9,'charan','reedy','rue tabac','charanreedy@sonita.fr',447473381,'fdgfhgjghjg',1),(10,'charan','reedy','10 rue tabacc','fbdi@ocd.com',447473381,'fdffdfdf',1),(11,'charan','reedy','10 rue tabacc','fbdi@ocd.com',447473381,'fdffdfdf',1),(12,'joy ','maria','fqdeyi','joygdfg',765566789,'fgsdggg',1),(13,'fhfhdfh','hfddfhh','vhdvhd','ghfhsfhth',254879862,'gdhsdgyhh',1),(14,'gkfkdk','kydkk','hkffk','gmhdj',332558865,'djjrjs',1),(15,'aa','aa','aseki nonompa','aa@ff.fr',844843332,'uche',1),(16,'fdfgsf','bfdbfb','saint server ','kk',983706202,'1234',1),(17,'admin','admin','italy','admin@vivrebank',304095709,'123',2),(18,'manager ','manager','venice','manager@vivrebank',99987377,'123',3),(19,'neds','newt','france','ned@icloud.com',910393444,'123',1),(20,'obi','peter','naija','peter@lbp',888,'123',1),(21,'obi','pete','pet','peter',888,'123',1),(22,'pradeeb','Devani','paris','eeb@esig.fr',73562227,'123',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-01 16:20:29
