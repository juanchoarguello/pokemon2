-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: metalesdb
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `idcuentas` int NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Valor` double NOT NULL,
  `Estado` int NOT NULL,
  `Cuenta` longtext NOT NULL,
  `Id_TipoCuenta` int NOT NULL,
  `Id_Persona` int NOT NULL,
  `abono` double DEFAULT NULL,
  PRIMARY KEY (`idcuentas`),
  KEY `fk_Id_TipoCuenta` (`Id_TipoCuenta`),
  KEY `fk_Id_TipoPersona` (`Id_Persona`),
  CONSTRAINT `fk_Id_TipoCuenta` FOREIGN KEY (`Id_TipoCuenta`) REFERENCES `tipocuenta` (`idTipoCuenta`),
  CONSTRAINT `fk_Id_TipoPersona` FOREIGN KEY (`Id_Persona`) REFERENCES `personas` (`idpersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (54,'2023-02-05 03:00:14',1012000,1,'42-La mona\ncobre : 54.0 kg x 28000 = 1512000.0 $\n1A : 10.0 kg x 30000 = 300000.0 $\nabona 800000.0 a la deuda de 1200000.0\nel valor total= 1012000.0',2,42,0),(55,'2023-02-05 03:09:11',1000000,1,'42-La mona\ncobre : 10.0 kg x 28000 = 280000.0 $\n1A : 12.0 kg x 30000 = 360000.0 $\nmixto : 25.0 kg x 28000 = 700000.0 $\nabona 340000.0 a la deuda de 1200000.0\nel valor total= 1000000.0',2,42,0),(56,'2023-02-05 03:19:56',1000000,1,'42-La mona\ncobre : 2.0 kg x 28000 = 56000.0 $\ncobre : 20.0 kg x 28000 = 560000.0 $\nmixto : 10.0 kg x 28000 = 280000.0 $\n1A : 15.0 kg x 30000 = 450000.0 $\nabona 346000.0 a la deuda de 1200000.0\nel valor total= 1000000.0',2,42,0),(57,'2023-02-05 03:21:30',1000000,1,'42-La mona\ncobre : 12.0 kg x 28000 = 336000.0 $\nmixto : 15.0 kg x 28000 = 420000.0 $\n1A : 25.0 kg x 30000 = 750000.0 $\ncobre : 10.0 kg x 28000 = 280000.0 $\nabona 786000.0 a la deuda de 1200000.0\nel valor total= 1000000.0',2,42,0),(58,'2023-02-05 03:34:01',2000000,1,'42-La mona\ncobre : 15.0 kg x 28000 = 420000.0 $\ncobre : 26.0 kg x 28000 = 728000.0 $\ncobre : 45.0 kg x 28000 = 1260000.0 $\nabona 408000.0 a la deuda de 1200000.0\nel valor total= 2000000.0',2,42,0),(59,'2023-02-05 03:49:11',1000000,1,'42-La mona\ncobre : 20.0 kg x 28000 = 560000.0 $\ncobre : 26.0 kg x 28000 = 728000.0 $\ncobre : 15.0 kg x 28000 = 420000.0 $\nabona 708000.0 a la deuda de 1200000.0\nel valor total= 1000000.0',2,42,0),(60,'2023-02-06 21:34:10',114636000,1,'42-La mona\ncobre : 1.0 kg x 28000 = 28000.0 $\ncobre : 2000.0 kg x 28000 = 5.6E7 $\ncobre : 1111.0 kg x 28000 = 3.1108E7 $\ncobre : 1000.0 kg x 28000 = 2.8E7 $\nabona 500000.0 a la deuda de 4400000.0\nel valor total= 1.14636E8',2,42,0),(61,'2023-02-06 21:43:09',200000,1,'42-La mona\nmixto : 100.0 kg x 28000 = 2800000.0 $\n1A : 40.0 kg x 30000 = 1200000.0 $\n1A : 10.0 kg x 30000 = 300000.0 $\n1A : 10.0 kg x 30000 = 300000.0 $\nabona 4400000.0 a la deuda de 4400000.0\nel valor total= 200000.0',2,42,0),(62,'2023-02-07 03:48:33',600000,1,'42-La mona\ncobre : 100.0 kg x 28000 = 2800000.0 $\ncobre : 100.0 kg x 28000 = 2800000.0 $\nabona 5000000.0 a la deuda de 4000000.0\nel valor total= 600000.0',2,42,0),(63,'2023-02-07 03:49:31',1400000,1,'42-La mona\ncobre : 100.0 kg x 28000 = 2800000.0 $\ncobre : 100.0 kg x 28000 = 2800000.0 $\nabona 4200000.0 a la deuda de 4000000.0\nel valor total= 1400000.0',2,42,0),(64,'2023-02-07 03:51:26',1400000,1,'42-La mona\ncobre : 100.0 kg x 28000 = 2800000.0 $\ncobre : 100.0 kg x 28000 = 2800000.0 $\nabona 4200000.0 a la deuda de 4400000.0\nel valor total= 1400000.0',2,42,0),(65,'2023-02-07 04:39:00',1400000,1,'42-La mona\ncobre : 100.0 kg x 28000 = 2800000.0 $\ncobre : 100.0 kg x 28000 = 2800000.0 $\nabona 4200000 a la deuda de 4400000.0\nel valor total= 1400000.0',2,42,0),(66,'2023-02-07 05:02:43',1000000,1,'41-Persona Anonima\nchatarra : 1000.0 kg x 1000 = 1000000.0 $\n\nel valor total= 1000000.0',1,41,0),(67,'2023-02-22 00:00:20',2800000,1,'42-La mona\ncobre : 100.0 kg x 28000 = 2800000.0 $\nabona 0 a la deuda de 200000.0\nel valor total= 2800000.0',2,42,0),(68,'2023-02-17 23:30:01',52000,1,'41-Persona Anonima\nchatarra : 52.0 kg x 1000 = 52000.0 $\n\nel valor total= 52000.0',1,41,0),(69,'2023-02-17 23:31:37',1124000,1,'42-La mona\ncobre : 58.0 kg x 28000 = 1624000.0 $\nabona 500000 a la deuda de 200000.0\nel valor total= 1124000.0',2,42,0),(70,'2023-03-12 04:59:23',1287900,0,'42-La mona\ncobre : 10.0 kg x 28000 = 280000.0 $\ncobre : 10.0 kg x 28000 = 280000.0 $\ncobre : 26.0 kg x 28000 = 728000.0 $\nabona 0 a la deuda de 200000.0\nel valor total= 1288000.0',2,42,0),(71,'2023-03-28 00:47:55',109900,1,'41-Persona Anonima\nchatarra : 100.0 kg x 1000 = 100000.0 $\naluminio : 2.0 kg x 5000 = 10000.0 $\n\nel valor total= 110000.0',1,41,0),(72,'2023-03-12 04:59:44',5599800,0,'42-La mona\ncobre : 200.0 kg x 28000 = 5600000.0 $\nabona 0 a la deuda de 200000.0\nel valor total= 5600000.0',2,42,0),(73,'2023-03-28 00:47:46',9106000,1,'41-Persona Anonima\ncobre : 300.0 kg x 30000.0 = 9000000.0 $\nbronce : 2.3 kg x 20000.0 = 46000.0 $\ncarton : 400.0 kg x 150.0 = 60000.0 $\n\nel valor total= 9106000.0',1,41,0),(74,'2023-03-28 01:55:19',9649000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 300.0 kg x 30000.0 = 9000000.0 $\ncobre : 20.0 kg x 30000.0 = 600000.0 $\ncobre : 1.3 kg x 30000.0 = 39000.0 $\n\nel valor total= 9649000.0',1,41,0),(75,'2023-03-28 04:13:56',940000,1,'41-Persona Anonima\n\ncarton : 10.0 kg x 150.0 = 1500.0 $\ncarton : 40.0 kg x 150.0 = 6000.0 $\nplastico : 25.0 kg x 300.0 = 7500.0 $\npapel : 50.0 kg x 10000.0 = 500000.0 $\npapel : 41.0 kg x 10000.0 = 410000.0 $\naluminio : 2.0 kg x 5000.0 = 10000.0 $\naluminio : 1.0 kg x 5000.0 = 5000.0 $\n\nel valor total= 940000.0',1,41,0),(76,'2023-03-28 04:57:42',343250,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 15.0 kg x 1000.0 = 15000.0 $\nchatarra : 20.0 kg x 1000.0 = 20000.0 $\ncobre : 4.0 kg x 30000.0 = 120000.0 $\npapel : 2.0 kg x 10000.0 = 20000.0 $\npapel : 15.0 kg x 10000.0 = 150000.0 $\ncarton : 55.0 kg x 150.0 = 8250.0 $\n{carton=55.0, chatarra=45.0, cobre=4.0, papel=17.0}\nel valor total= 343250.0',1,41,0),(77,'2023-03-28 12:58:07',399983,1,'41-Persona Anonima\n\nbronce : 20.0 kg x 20000.0 = 400000.0 $\nvidrio : 0.56 kg x 50.0 = 28.000000000000004 $\ncarton : -0.3 kg x 150.0 = -45.0 $\n{carton=-0.3, bronce=20.0, vidrio=0.56}\nel valor total= 399983.0',1,41,0),(78,'2023-03-28 14:49:00',1721000,1,'43-alberto\n\nchatarra : 10.0 kg x 1000.0\r = 10000.0 $\nchatarra : 21.0 kg x 1000.0\r = 21000.0 $\nchatarra : 15.0 kg x 1000.0\r = 15000.0 $\n{chatarra=46.0}\nel valor total= 1721000.0',2,43,0),(79,'2023-03-28 22:24:43',30000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 20.0 kg x 1000.0 = 20000.0 $\n{chatarra=30.0}\nel valor total= 30000.0',1,41,0),(80,'2023-03-28 23:18:51',100000,1,'41-Persona Anonima\n\nchatarra : 100.0 kg x 1000.0 = 100000.0 $\n{chatarra=100.0}\nel valor total= 100000.0',1,41,0),(81,'2023-03-29 10:50:51',10000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n{chatarra=10.0}\nel valor total= 10000.0',1,41,0),(82,'2023-03-29 10:52:48',10000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n{chatarra=10.0}\nel valor total= 10000.0',1,41,0),(83,'2023-03-29 10:57:39',10000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n{chatarra=10.0}\nel valor total= 10000.0',1,41,0),(84,'2023-03-29 11:47:08',40250,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 20.0 kg x 1000.0 = 20000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\ncarton : 45.0 kg x 150.0 = 6750.0 $\n\nel valor total= 40250.0',1,41,0),(85,'2023-03-29 11:49:08',581250,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 15.0 kg x 1000.0 = 15000.0 $\nchatarra : 25.0 kg x 1000.0 = 25000.0 $\npapel : 10.0 kg x 10000.0 = 100000.0 $\npapel : 12.0 kg x 10000.0 = 120000.0 $\ncarton : 45.0 kg x 150.0 = 6750.0 $\nplastico : 15.0 kg x 300.0 = 4500.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 581250.0',1,41,0),(86,'2023-03-29 11:52:27',282800,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 2.0 kg x 1000.0 = 2000.0 $\nchatarra : 14.0 kg x 1000.0 = 14000.0 $\nbronce : 2.0 kg x 20000.0 = 40000.0 $\ncobre : 4.0 kg x 30000.0 = 120000.0 $\ncarton : 12.0 kg x 150.0 = 1800.0 $\ncarton : 5.0 kg x 150.0 = 750.0 $\ncarton : 15.0 kg x 150.0 = 2250.0 $\npapel : 5.0 kg x 10000.0 = 50000.0 $\npapel : 3.0 kg x 10000.0 = 30000.0 $\nplastico : 15.0 kg x 300.0 = 4500.0 $\nplastico : 25.0 kg x 300.0 = 7500.0 $\n\nel valor total= 282800.0',1,41,0),(87,'2023-03-30 11:34:37',1558650,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 21.0 kg x 1000.0 = 21000.0 $\nchatarra : 15.0 kg x 1000.0 = 15000.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\ncarton : 45.0 kg x 150.0 = 6750.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nplastico : 4.0 kg x 300.0 = 1200.0 $\nvidrio : 4.0 kg x 50.0 = 200.0 $\ncobre : 40.0 kg x 30000.0 = 1200000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 1558650.0',1,41,0),(88,'2023-03-30 11:36:10',1009000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\ncobre : 12.0 kg x 30000.0 = 360000.0 $\ncobre : 14.0 kg x 30000.0 = 420000.0 $\npapel : 10.0 kg x 10000.0 = 100000.0 $\npapel : 10.0 kg x 10000.0 = 100000.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\n\nel valor total= 1009000.0',1,41,0),(89,'2023-03-30 11:40:12',82000,1,'chatarra : 1.0 kg x 1000.0 = 1000.0 $\nchatarra : 1.0 kg x 1000.0 = 1000.0 $\npapel : 1.0 kg x 10000.0 = 10000.0 $\npapel : 1.0 kg x 10000.0 = 10000.0 $\ncobre : 1.0 kg x 30000.0 = 30000.0 $\ncobre : 1.0 kg x 30000.0 = 30000.0 $\n\nel valor total= 82000.0',1,41,0),(90,'2023-03-30 11:46:38',490500,1,'chatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\naluminio : 5.0 kg x 5000.0 = 25000.0 $\naluminio : 5.0 kg x 5000.0 = 25000.0 $\npapel : 15.0 kg x 10000.0 = 150000.0 $\npapel : 12.0 kg x 10000.0 = 120000.0 $\ncobre : 3.0 kg x 30000.0 = 90000.0 $\ncobre : 2.0 kg x 30000.0 = 60000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\n\nel valor total= 490500.0',1,41,0),(91,'2023-03-30 12:04:36',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 1020000.0',1,41,0),(92,'2023-03-30 12:25:38',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 1020000.0',1,41,0),(93,'2023-03-30 12:35:03',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 1020000.0',1,41,0),(94,'2023-03-30 12:44:44',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 1020000.0',1,41,0),(95,'2023-03-30 14:00:45',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 1020000.0',1,41,0),(96,'2023-03-30 14:03:45',730000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\npapel : 10.0 kg x 10000.0 = 100000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 730000.0',1,41,0),(97,'2023-03-30 14:06:31',633500,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 633500.0',1,41,0),(98,'2023-03-30 14:11:45',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\n\nel valor total= 1020000.0',1,41,0),(99,'2023-03-30 14:14:40',1020000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\n\nel valor total= 1020000.0',1,41,0),(100,'2023-03-30 14:17:48',10000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 10000.0',1,41,0),(101,'2023-03-30 14:20:29',20000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 20000.0',1,41,0),(102,'2023-03-30 15:35:07',518200,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nplastico : 14.0 kg x 300.0 = 4200.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\n\nel valor total= 518200.0',1,41,0),(103,'2023-03-30 21:01:31',681800,1,'44-jhonatan baquero\n\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 2.0 kg x 20000.0 = 40000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nplastico : 5.0 kg x 300.0 = 1500.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\ncarton : 52.0 kg x 150.0 = 7800.0 $\npapel : 10.0 kg x 10000.0 = 100000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\naluminio : 5.0 kg x 5500.0 = 27500.0 $\n\nel valor total= 681800.0',1,44,0),(104,'2023-03-31 21:12:32',150000,1,'45-asdasd\n\narranque : 1.0 kg x 150000.0 = 150000.0 $\n\nel valor total= 150000.0',1,45,0),(105,'2023-04-01 22:25:27',20000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 20000.0',1,41,0),(106,'2023-04-01 22:38:10',20000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 20000.0',1,41,0),(107,'2023-04-01 22:42:32',20000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 20000.0',1,41,0),(108,'2023-04-01 22:43:31',20000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\n\nel valor total= 20000.0',1,41,0),(109,'2023-04-01 22:46:24',1010000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\n\nel valor total= 1010000.0',1,41,0),(110,'2023-04-02 13:09:42',645500,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncarton : 100.0 kg x 150.0 = 15000.0 $\n\nel valor total= 645500.0',1,41,0),(111,'2023-04-02 13:13:10',623800,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nplastico : 1.0 kg x 300.0 = 300.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\n\nel valor total= 623800.0',1,41,0),(112,'2023-04-03 00:07:58',1021000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\nbronce : 10.0 kg x 20000.0 = 200000.0 $\n\nel valor total= 1021000.0',1,41,0),(113,'2023-04-03 01:54:42',1103000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 15.0 kg x 1000.0 = 15000.0 $\nchatarra : 25.0 kg x 1000.0 = 25000.0 $\ncobre : 25.0 kg x 30000.0 = 750000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\n\nel valor total= 1103000.0',1,41,0),(114,'2023-04-03 01:57:11',30000,1,'41-Persona Anonima\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\nchatarra : 20.0 kg x 1000.0 = 20000.0 $\n\nel valor total= 30000.0',1,41,0),(115,'2023-04-05 16:36:10',310500,1,'41-Persona Anonima\n\ncarton : 10.0 kg x 150.0 = 1500.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\n\nel valor total= 310500.0',1,41,0),(116,'2023-04-05 22:11:57',315000,1,'44-jhonatan baquero\n\nchatarra : 10.0 kg x 1000.0 = 10000.0 $\ncarton : 10.0 kg x 150.0 = 1500.0 $\ncobre : 10.0 kg x 30000.0 = 300000.0 $\nvidrio : 10.0 kg x 50.0 = 500.0 $\nplastico : 10.0 kg x 300.0 = 3000.0 $\n\nel valor total= 315000.0',1,44,0);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `idInventario` int NOT NULL AUTO_INCREMENT,
  `peso` double DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `idMaterial` int NOT NULL,
  `Valor` double DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idInventario`),
  KEY `material_fk` (`idMaterial`),
  CONSTRAINT `material_fk` FOREIGN KEY (`idMaterial`) REFERENCES `materiales` (`idMateriales`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (1,10,'motor asdas',10,150000,'2023-03-06 05:00:00'),(2,10,'111',8,500,'2023-04-02 13:13:11'),(3,20,'111',1,20000,'2023-04-02 13:13:11'),(4,11,'111',6,3300,'2023-04-02 13:13:11'),(5,20,'111',7,600000,'2023-04-02 13:13:11'),(6,20,'112',3,400000,'2023-04-03 00:07:59'),(7,20,'112',8,1000,'2023-04-03 00:07:59'),(8,20,'112',1,20000,'2023-04-03 00:07:59'),(9,20,'112',7,600000,'2023-04-03 00:07:59'),(10,20,'113',5,3000,'2023-04-03 01:54:43'),(11,50,'113',1,50000,'2023-04-03 01:54:43'),(12,35,'113',7,1050000,'2023-04-03 01:54:43'),(13,30,'114',1,30000,'2023-04-03 01:57:11'),(14,10,'115',5,1500,'2023-04-05 16:36:12'),(15,30,'115',6,9000,'2023-04-05 16:36:12'),(16,10,'115',7,300000,'2023-04-05 16:36:12'),(17,10,'116',5,1500,'2023-04-05 22:11:58'),(18,10,'116',8,500,'2023-04-05 22:11:58'),(19,10,'116',1,10000,'2023-04-05 22:11:58'),(20,10,'116',6,3000,'2023-04-05 22:11:58'),(21,10,'116',7,300000,'2023-04-05 22:11:59');
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales` (
  `idMateriales` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `Valor` double NOT NULL,
  PRIMARY KEY (`idMateriales`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (1,'chatarra',NULL,1000),(2,'aluminio',NULL,5500),(3,'bronce',NULL,20000),(4,'papel',NULL,10000),(5,'carton',NULL,150),(6,'plastico',NULL,300),(7,'cobre',NULL,30000),(8,'vidrio',NULL,50),(9,'pvc',NULL,500),(10,'motor de arranque',NULL,150000);
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personas` (
  `idpersonas` int NOT NULL AUTO_INCREMENT,
  `IdTP` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `Celular` varchar(45) DEFAULT NULL,
  `identificacion` varchar(45) DEFAULT NULL,
  `TipoDocumento_nombre` varchar(45) DEFAULT NULL,
  `Archivo` longtext NOT NULL,
  `Descripcion` longtext,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersonas`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `IdTipoDePersona_idx` (`IdTP`),
  CONSTRAINT `IdTipoDePersona` FOREIGN KEY (`IdTP`) REFERENCES `tipopersona` (`idtipopersona`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (37,3,'juanU','3152154826','1102386633','cc','','hijo Usuario','2546'),(41,1,'Persona Anonima','000000000','0000000000','cc','chatarra,1000.0 aluminio,5000.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0','persona sin nombre del comun',''),(42,2,'La mona','0000000','0000000','cc','chatarra,1000.0 aluminio,5000.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0','cliente principal de los materiales ',''),(43,2,'alberto','3333333333','11111111111','cc','chatarra,1000.0\r aluminio,5000.0\r bronce,20000.0\r papel,10000.0\r carton,150.0\r plastico,300.0\r cobre,30000.0\r vidrio,50.0\r','cliente de bucaramanga',''),(44,1,'jhonatan baquero','5546346345','1111111','cc','chatarra,1000.0 aluminio,5500.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0 pvc,500.0','upb',''),(45,1,'asdasd','12312','1231','cc','chatarra,1000.0 aluminio,5500.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0 pvc,500.0 MotorDeArranque,150000.0','asdas','');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `idPrestamos` int NOT NULL AUTO_INCREMENT,
  `Id_Persona` int NOT NULL,
  `Id_Tipo_Persona` int NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Valor` double NOT NULL,
  `descripcion` varchar(2000) DEFAULT NULL,
  `Abono` double DEFAULT NULL,
  PRIMARY KEY (`idPrestamos`),
  KEY `fk_Id_Persona` (`Id_Persona`),
  KEY `fk_Id_Tipo_Persona` (`Id_Tipo_Persona`),
  CONSTRAINT `fk_Id_Persona` FOREIGN KEY (`Id_Persona`) REFERENCES `personas` (`idpersonas`),
  CONSTRAINT `fk_Id_Tipo_Persona` FOREIGN KEY (`Id_Tipo_Persona`) REFERENCES `personas` (`IdTP`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (31,42,2,'2023-03-12 04:58:19',200000,'el prestamo con ultima actividad el dia : 2023-02-22 17:55:29 era de 200000.0 se abono un total de =0',NULL);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimientoinventario`
--

DROP TABLE IF EXISTS `seguimientoinventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seguimientoinventario` (
  `idInventario` int NOT NULL,
  `pesoNuevo` double NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Material` varchar(45) DEFAULT NULL,
  `ValorAproximado` double DEFAULT NULL,
  `FechaDelCambio` date NOT NULL,
  PRIMARY KEY (`idInventario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimientoinventario`
--

LOCK TABLES `seguimientoinventario` WRITE;
/*!40000 ALTER TABLE `seguimientoinventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimientoinventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipocuenta`
--

DROP TABLE IF EXISTS `tipocuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipocuenta` (
  `idTipoCuenta` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idTipoCuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipocuenta`
--

LOCK TABLES `tipocuenta` WRITE;
/*!40000 ALTER TABLE `tipocuenta` DISABLE KEYS */;
INSERT INTO `tipocuenta` VALUES (1,'Cuenta Provedor','es las cuentas q tienen proveedor'),(2,'cuenta Cliente','estas cuentas son de clientes '),(3,'Cuenta Usuario','estas cuentas son de usuario ');
/*!40000 ALTER TABLE `tipocuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipodocumento` (
  `descripcion` varchar(45) DEFAULT NULL,
  `nombre` varchar(16) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumento`
--

LOCK TABLES `tipodocumento` WRITE;
/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
INSERT INTO `tipodocumento` VALUES ('cedula','cc'),('Permiso de permanencia','PDP'),('visa','V');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopersona`
--

DROP TABLE IF EXISTS `tipopersona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipopersona` (
  `idtipopersona` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idtipopersona`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopersona`
--

LOCK TABLES `tipopersona` WRITE;
/*!40000 ALTER TABLE `tipopersona` DISABLE KEYS */;
INSERT INTO `tipopersona` VALUES (1,'Proveedor','Personas que venden material a Metales de santander'),(2,'Cliente','Personas a las que metales de santander les vende'),(3,'Usuario','persona que reguistra en el software');
/*!40000 ALTER TABLE `tipopersona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-17  7:58:54
