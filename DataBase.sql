CREATE DATABASE  IF NOT EXISTS `metalesdb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `metalesdb`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: metalesdb
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
-- Table structure for table `caja_menor`
--

DROP TABLE IF EXISTS `caja_menor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caja_menor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_movimiento_id` int DEFAULT NULL,
  `monto` decimal(10,2) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `es_gasto` tinyint NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_movimiento_id` (`tipo_movimiento_id`),
  CONSTRAINT `caja_menor_ibfk_1` FOREIGN KEY (`tipo_movimiento_id`) REFERENCES `tipos_movimientos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja_menor`
--

LOCK TABLES `caja_menor` WRITE;
/*!40000 ALTER TABLE `caja_menor` DISABLE KEYS */;
INSERT INTO `caja_menor` VALUES (1,1,10000.00,'pago pedro',0,'2023-05-21'),(2,2,10000.00,'Donacion del gobierno',1,'2023-05-22'),(3,4,4000.00,'se vendieron 4kg',1,'2023-05-22'),(4,4,2000.00,'2k',1,'2023-05-22'),(5,7,10000.00,'rueda de carretilla',1,'2023-05-22');
/*!40000 ALTER TABLE `caja_menor` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (117,'2023-05-04 01:15:31',95070,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.3 X 900.0 = 270.0\nid=2,  chatarra  52.0 X 900.0 = 46800.0\nid=3,  cobre  1.3 X 30000.0 = 39000.0\n\nel valor total= 95070.0',1,41,0),(119,'2023-05-04 01:45:05',71100,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  chatarra  0.6 X 1000.0 = 600.0\nid=2,  chatarra  0.5 X 1000.0 = 500.0\nid=3,  chatarra  60.0 X 1000.0 = 60000.0\n\nel valor total= 71100.0',1,44,0),(121,'2023-05-04 01:51:38',11250,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.5 X 900.0 = 450.0\nid=2,  chatarra  2.0 X 900.0 = 1800.0\n\nel valor total= 11250.0',1,41,0),(122,'2023-05-04 01:54:08',1647450,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.5 X 900.0 = 450.0\nid=2,  chatarra  20.0 X 900.0 = 18000.0\nid=3,  cobre  10.0 X 30000.0 = 300000.0\nid=4,  cobre  44.0 X 30000.0 = 1320000.0\n\nel valor total= 1647450.0',1,41,0),(123,'2023-05-04 01:59:12',38970,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  3.3 X 900.0 = 2970.0\nid=2,  chatarra  30.0 X 900.0 = 27000.0\n\nel valor total= 38970.0',1,41,0),(124,'2023-05-04 02:00:54',10314,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.5 X 900.0 = 450.0\nid=2,  chatarra  0.96 X 900.0 = 864.0\n\nel valor total= 10314.0',1,41,0),(125,'2023-05-04 02:01:48',1350,1,'id=3,  chatarra  0.5 X 900.0 = 450.0\nid=4,  chatarra  0.5 X 900.0 = 450.0\nid=5,  chatarra  0.5 X 900.0 = 450.0\n\nel valor total= 1350.0',1,41,0),(126,'2023-05-04 02:08:01',9810,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.3 X 900.0 = 270.0\nid=2,  chatarra  0.6 X 900.0 = 540.0\n\nel valor total= 9810.0',1,41,0),(127,'2023-05-23 03:21:35',200,1,'id=3,  chatarra  0.3 X 900.0 = 270.0\n\nel valor total= 270.0',1,41,0),(128,'2023-05-04 02:11:34',9702,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.63 X 900.0 = 567.0\nid=2,  chatarra  0.15 X 900.0 = 135.0\n\nel valor total= 9702.0',1,41,0),(129,'2023-05-04 02:14:16',9180,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.2 X 900.0 = 180.0\nid=2,  chatarra  0.0 X 900.0 = 0.0\n\nel valor total= 9180.0',1,41,0),(130,'2023-05-04 02:15:17',10260,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.5 X 900.0 = 450.0\nid=2,  chatarra  0.9 X 900.0 = 810.0\n\nel valor total= 10260.0',1,41,0),(131,'2023-05-04 02:16:12',1890,1,'id=0,  chatarra  0.5 X 900.0 = 450.0\nid=1,  chatarra  0.6 X 900.0 = 540.0\nid=2,  chatarra  0.8 X 900.0 = 720.0\nid=3,  chatarra  0.2 X 900.0 = 180.0\n\nel valor total= 1890.0',1,41,0),(132,'2023-05-04 02:22:05',1440,1,'id=0,  chatarra  0.2 X 900.0 = 180.0\nid=1,  chatarra  0.65 X 900.0 = 585.0\nid=2,  chatarra  0.75 X 900.0 = 675.0\n\nel valor total= 1440.0',1,41,0),(133,'2023-05-04 02:22:37',1440,1,'id=3,  chatarra  0.2 X 900.0 = 180.0\nid=4,  chatarra  0.65 X 900.0 = 585.0\nid=5,  chatarra  0.75 X 900.0 = 675.0\n\nel valor total= 1440.0',1,41,0),(134,'2023-05-23 05:37:14',197000,1,'id=0,  chatarra  10.0 X 900.0 = 9000.0\nid=1,  chatarra  0.3 X 900.0 = 270.0\nid=2,  chatarra  0.8 X 900.0 = 720.0\nid=3,  chatarra  50.0 X 900.0 = 45000.0\nid=4,  plastico  10.0 X 300.0 = 3000.0\nid=5,  cobre  0.9 X 30000.0 = 27000.0\nid=6,  carton  40.0 X 150.0 = 6000.0\nid=7,  carton  25.0 X 150.0 = 3750.0\nid=8,  plastico  10.0 X 300.0 = 3000.0\nid=9,  bronce  10.0 X 20000.0 = 200000.0\n\nel valor total= 297740.0-id=-1,  Abono  740 X 1 = 197000.0',1,41,0),(135,'2023-05-04 03:40:51',450150,0,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  chatarra  5.0 X 1000.0 = 5000.0\nid=2,  chatarra  14.0 X 1000.0 = 14000.0\nid=3,  carton  10.0 X 150.0 = 1500.0\nid=4,  carton  15.0 X 150.0 = 2250.0\nid=5,  carton  16.0 X 150.0 = 2400.0\nid=6,  cobre  10.0 X 30000.0 = 300000.0\nid=7,  papel  5.0 X 10000.0 = 50000.0\nid=8,  bronce  2.0 X 20000.0 = 40000.0\nid=9,  aluminio  5.0 X 5000.0 = 25000.0\nabona 0 a la deuda de 200000.0\nel valor total= 450150.0',1,42,0),(136,'2023-05-09 05:34:49',132060,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  chatarra  111.0 X 1000.0 = 111000.0\nid=2,  plastico  0.2 X 300.0 = 60.0\nid=3,  chatarra  0.3 X 1000.0 = 300.0\nid=4,  chatarra  -0.3 X 1000.0 = -300.0\nid=5,  chatarra  11.0 X 1000.0 = 11000.0\n\nel valor total= 132060.0',1,41,0),(137,'2023-05-23 02:17:25',664000,0,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  aluminio  10.0 X 5000.0 = 50000.0\nid=2,  bronce  10.0 X 20000.0 = 200000.0\nid=3,  papel  10.0 X 10000.0 = 100000.0\nid=4,  carton  10.0 X 150.0 = 1500.0\nid=5,  plastico  10.0 X 300.0 = 3000.0\nid=6,  cobre  10.0 X 30000.0 = 300000.0\nid=7,  vidrio  10.0 X 100.0 = 1000.0\n\nel valor total= 665500.0',2,41,0),(138,'2023-05-16 16:13:55',11000,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  chatarra  0.5 X 1000.0 = 500.0\nid=2,  chatarra  0.5 X 1000.0 = 500.0\n\nel valor total= 11000.0',1,41,0),(139,'2023-05-17 02:26:50',15013,1,'id=0,  chatarra  0.01 X 1000.0 = 10.0\nid=1,  vidrio  0.03 X 100.0 = 3.0\nid=2,  cobre  0.5 X 30000.0 = 15000.0\n\nel valor total= 15013.0',1,41,0),(140,'2023-05-17 02:44:50',30500,1,'id=0,  chatarra  0.5 X 1000.0 = 500.0\nid=1,  chatarra  1.0 X 1000.0 = 1000.0\nid=2,  chatarra  20.0 X 1000.0 = 20000.0\nid=3,  cobre  0.3 X 30000.0 = 9000.0\n\nel valor total= 30500.0',1,41,0),(141,'2023-05-17 02:48:37',665500,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  aluminio  10.0 X 5000.0 = 50000.0\nid=2,  bronce  10.0 X 20000.0 = 200000.0\nid=3,  papel  10.0 X 10000.0 = 100000.0\nid=4,  carton  10.0 X 150.0 = 1500.0\nid=5,  plastico  10.0 X 300.0 = 3000.0\nid=6,  cobre  10.0 X 30000.0 = 300000.0\nid=7,  vidrio  10.0 X 100.0 = 1000.0\n\nel valor total= 665500.0',1,41,0),(142,'2023-05-17 02:50:32',665500,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  aluminio  10.0 X 5000.0 = 50000.0\nid=2,  bronce  10.0 X 20000.0 = 200000.0\nid=3,  papel  10.0 X 10000.0 = 100000.0\nid=4,  carton  10.0 X 150.0 = 1500.0\nid=5,  plastico  10.0 X 300.0 = 3000.0\nid=6,  cobre  10.0 X 30000.0 = 300000.0\nid=7,  vidrio  10.0 X 100.0 = 1000.0\n\nel valor total= 665500.0',2,41,0),(143,'2023-05-17 22:54:38',10000,1,'id=0,  chatarra  0.3 X 1000.0 = 300.0\nid=1,  chatarra  10.0 X 1000.0 = 10000.0\nid=2,  chatarra  -0.3 X 1000.0 = -300.0\n\nel valor total= 10000.0',1,41,0),(144,'2023-05-23 17:42:28',269000,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  chatarra  10.0 X 1000.0 = 10000.0\nid=2,  aluminio  10.0 X 5000.0 = 50000.0\nid=3,  bronce  10.0 X 20000.0 = 200000.0\n\nel valor total= 270000.0_id=-1,  Abono  1000 X 1 = 269000.0',2,41,0),(145,'2023-05-23 21:03:22',310000,0,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  chatarra  0.5 X 1000.0 = 500.0\nid=2,  chatarra  10.0 X 1000.0 = 10000.0\nid=3,  cobre  10.0 X 30000.0 = 300000.0\nid=4,  carton  10.0 X 150.0 = 1500.0\n\nel valor total= 322000.0_id=-1,  Abono  1000 X 1 = 321000.0_id=-1,  Abono  1000 X 1 = 320000.0_id=-1,  Abono  10000 X 1 = -310000.0',2,41,0),(146,'2023-05-26 05:35:38',514500,1,'id=0,  chatarra  10.0 X 1000.0 = 10000.0\nid=1,  plastico  10.0 X 300.0 = 3000.0\nid=2,  carton  10.0 X 150.0 = 1500.0\nid=3,  cobre  10.0 X 30000.0 = 300000.0\nid=4,  bronce  10.0 X 20000.0 = 200000.0\n\nel valor total= 514500.0',2,41,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=469 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (341,0.01,'139',1,1000,'2023-05-17 02:26:52'),(342,0.03,'139',8,100,'2023-05-17 02:26:52'),(343,0.5,'139',7,30000,'2023-05-17 02:26:52'),(344,-0.5,'140',1,500,'2023-05-17 02:44:51'),(345,-1,'140',1,1000,'2023-05-17 02:44:51'),(346,-20,'140',1,20000,'2023-05-17 02:44:51'),(347,-0.3,'140',7,9000,'2023-05-17 02:44:51'),(348,-10,'141',1,10000,'2023-05-17 02:48:38'),(349,-10,'141',2,50000,'2023-05-17 02:48:38'),(350,-10,'141',3,200000,'2023-05-17 02:48:38'),(351,-10,'141',4,100000,'2023-05-17 02:48:38'),(352,-10,'141',5,1500,'2023-05-17 02:48:38'),(353,-10,'141',6,3000,'2023-05-17 02:48:38'),(354,-10,'141',7,300000,'2023-05-17 02:48:38'),(355,-10,'141',8,1000,'2023-05-17 02:48:38'),(356,10,'142',1,-10000,'2023-05-17 02:50:33'),(357,10,'142',2,-50000,'2023-05-17 02:50:33'),(358,10,'142',3,-200000,'2023-05-17 02:50:33'),(359,10,'142',4,-100000,'2023-05-17 02:50:33'),(360,10,'142',5,-1500,'2023-05-17 02:50:33'),(361,10,'142',6,-3000,'2023-05-17 02:50:33'),(362,10,'142',7,-300000,'2023-05-17 02:50:33'),(363,10,'142',8,-1000,'2023-05-17 02:50:33'),(364,-0.3,'143',1,300,'2023-05-17 22:54:40'),(365,-10,'143',1,10000,'2023-05-17 22:54:40'),(366,0.3,'143',1,-300,'2023-05-17 22:54:40'),(367,-10,'137',1,10000,'2023-05-23 01:50:53'),(368,-10,'137',2,50000,'2023-05-23 01:50:53'),(369,-10,'137',3,200000,'2023-05-23 01:50:53'),(370,-10,'137',4,100000,'2023-05-23 01:50:53'),(371,-10,'137',5,1500,'2023-05-23 01:50:53'),(372,-10,'137',6,3000,'2023-05-23 01:50:53'),(373,-10,'137',7,300000,'2023-05-23 01:50:53'),(374,-10,'137',8,1000,'2023-05-23 01:50:53'),(375,-10,'118',1,9000,'2023-05-23 02:18:42'),(376,-0.5,'118',1,450,'2023-05-23 02:18:42'),(377,-55,'118',1,49500,'2023-05-23 02:18:42'),(378,-10,'118',1,9000,'2023-05-23 03:50:34'),(379,-10,'118',1,9000,'2023-05-23 04:18:43'),(380,-0.5,'118',1,450,'2023-05-23 04:18:43'),(381,-55,'118',1,49500,'2023-05-23 04:18:43'),(382,-10,'118',1,9000,'2023-05-23 04:18:48'),(383,-0.5,'118',1,450,'2023-05-23 04:18:48'),(384,-55,'118',1,49500,'2023-05-23 04:18:48'),(385,-10,'118',1,9000,'2023-05-23 04:21:18'),(386,-0.5,'118',1,450,'2023-05-23 04:21:18'),(387,-55,'118',1,49500,'2023-05-23 04:21:18'),(388,-10,'118',1,9000,'2023-05-23 04:42:29'),(389,-10,'118',1,9000,'2023-05-23 04:50:44'),(390,-10,'118',1,9000,'2023-05-23 04:58:19'),(391,-10,'120',1,9000,'2023-05-23 04:59:23'),(392,-0.5,'120',1,450,'2023-05-23 04:59:23'),(393,-55,'120',1,49500,'2023-05-23 04:59:23'),(394,-10,'118',1,9000,'2023-05-23 05:00:31'),(395,-10,'118',1,9000,'2023-05-23 05:03:01'),(396,-10,'118',1,9000,'2023-05-23 05:05:28'),(397,-10,'120',1,9000,'2023-05-23 05:08:52'),(398,-0.5,'120',1,450,'2023-05-23 05:08:52'),(399,-55,'120',1,49500,'2023-05-23 05:08:52'),(400,-10,'120',1,9000,'2023-05-23 05:20:17'),(401,-0.5,'120',1,450,'2023-05-23 05:20:17'),(402,-55,'120',1,49500,'2023-05-23 05:20:17'),(403,-10,'120',1,9000,'2023-05-23 05:26:56'),(404,-10,'120',1,9000,'2023-05-23 05:28:28'),(405,-10,'120',1,9000,'2023-05-23 05:29:56'),(406,-10,'134',1,9000,'2023-05-23 05:31:31'),(407,-0.3,'134',1,270,'2023-05-23 05:31:31'),(408,-0.8,'134',1,720,'2023-05-23 05:31:31'),(409,-50,'134',1,45000,'2023-05-23 05:31:31'),(410,-10,'134',6,3000,'2023-05-23 05:31:31'),(411,-0.9,'134',7,27000,'2023-05-23 05:31:31'),(412,-40,'134',5,6000,'2023-05-23 05:31:31'),(413,-25,'134',5,3750,'2023-05-23 05:31:31'),(414,-10,'134',6,3000,'2023-05-23 05:31:31'),(415,-10,'134',3,200000,'2023-05-23 05:31:31'),(416,10,'144',1,-10000,'2023-05-23 05:38:04'),(417,10,'144',1,-10000,'2023-05-23 05:38:04'),(418,10,'144',2,-50000,'2023-05-23 05:38:04'),(419,10,'144',3,-200000,'2023-05-23 05:38:04'),(420,-10,'137',1,10000,'2023-05-23 05:45:29'),(421,-10,'137',2,50000,'2023-05-23 05:45:29'),(422,-10,'137',3,200000,'2023-05-23 05:45:29'),(423,-10,'137',4,100000,'2023-05-23 05:45:29'),(424,-10,'137',5,1500,'2023-05-23 05:45:29'),(425,-10,'137',6,3000,'2023-05-23 05:45:29'),(426,-10,'137',7,300000,'2023-05-23 05:45:29'),(427,-10,'137',8,1000,'2023-05-23 05:45:29'),(428,-10,'144',1,10000,'2023-05-23 05:46:09'),(429,-10,'144',1,10000,'2023-05-23 05:46:09'),(430,-10,'144',2,50000,'2023-05-23 05:46:09'),(431,-10,'144',3,200000,'2023-05-23 05:46:09'),(432,-10,'144',1,10000,'2023-05-23 17:41:59'),(433,10,'145',1,-10000,'2023-05-23 17:43:18'),(434,0.5,'145',1,-500,'2023-05-23 17:43:18'),(435,10,'145',1,-10000,'2023-05-23 17:43:18'),(436,10,'145',7,-300000,'2023-05-23 17:43:18'),(437,10,'145',5,-1500,'2023-05-23 17:43:18'),(438,-10,'145',1,10000,'2023-05-23 17:44:01'),(439,-0.5,'145',1,500,'2023-05-23 17:44:01'),(440,-10,'145',1,10000,'2023-05-23 17:44:01'),(441,-10,'145',7,300000,'2023-05-23 17:44:01'),(442,-10,'145',5,1500,'2023-05-23 17:44:01'),(443,-10,'145',1,10000,'2023-05-23 17:45:53'),(444,-0.5,'145',1,500,'2023-05-23 17:45:53'),(445,-10,'145',1,10000,'2023-05-23 17:45:53'),(446,-10,'145',7,300000,'2023-05-23 17:45:53'),(447,-10,'145',5,1500,'2023-05-23 17:45:53'),(448,-10,'145',1,10000,'2023-05-23 17:48:06'),(449,-0.5,'145',1,500,'2023-05-23 17:48:06'),(450,-10,'145',1,10000,'2023-05-23 17:48:06'),(451,-10,'145',7,300000,'2023-05-23 17:48:06'),(452,-10,'145',5,1500,'2023-05-23 17:48:06'),(453,-10,'145',1,10000,'2023-05-23 18:16:39'),(454,-0.5,'145',1,500,'2023-05-23 18:16:39'),(455,-10,'145',1,10000,'2023-05-23 18:16:39'),(456,-10,'145',7,300000,'2023-05-23 18:16:39'),(457,-10,'145',5,1500,'2023-05-23 18:16:39'),(458,-10,'145',1,10000,'2023-05-23 18:18:04'),(459,-0.5,'145',1,500,'2023-05-23 18:18:04'),(460,-10,'145',1,10000,'2023-05-23 18:18:04'),(461,-10,'145',7,300000,'2023-05-23 18:18:04'),(462,-10,'145',5,1500,'2023-05-23 18:18:04'),(464,10,'146',1,-10000,'2023-05-26 05:35:39'),(465,10,'146',6,-3000,'2023-05-26 05:35:39'),(466,10,'146',5,-1500,'2023-05-26 05:35:39'),(467,10,'146',7,-300000,'2023-05-26 05:35:39'),(468,10,'146',3,-200000,'2023-05-26 05:35:39');
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
INSERT INTO `materiales` VALUES (1,'chatarra',NULL,1000),(2,'aluminio',NULL,5500),(3,'bronce',NULL,20000),(4,'papel',NULL,10000),(5,'carton',NULL,150),(6,'plastico',NULL,300),(7,'cobre',NULL,25000),(8,'vidrio',NULL,50),(9,'pvc',NULL,500),(10,'motor de arranque',NULL,150000);
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (37,3,'juanU','3152154826','1102386633','cc','','hijo Usuario','2546'),(41,1,'Persona Anonima','000000000','0000000000','cc','chatarra,1000 aluminio,5000.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,100','persona sin nombre del comun',''),(42,2,'La mona','0000000','0000000','cc','chatarra,1000.0 aluminio,5000.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0','cliente principal de los materiales ',''),(43,2,'alberto','3333333333','11111111111','cc','chatarra,1000.0\r aluminio,5000.0\r bronce,20000.0\r papel,10000.0\r carton,150.0\r plastico,300.0\r cobre,30000.0\r vidrio,50.0\r','cliente de bucaramanga',''),(44,1,'jhonatan baquero','5546346345','1111111','cc','chatarra,1000 aluminio,5500.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0 pvc,500.0','upb',''),(45,1,'asdasd','12312','1231','cc','chatarra,1000.0 aluminio,5500.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,30000.0 vidrio,50.0 pvc,500.0 MotorDeArranque,150000.0','asdas',''),(46,2,'marta','','','cc','chatarra,1000.0 aluminio,5500.0 bronce,20000.0 papel,10000.0 carton,150.0 plastico,300.0 cobre,25000.0 vidrio,50.0 pvc,500.0 motor_de_arranque,150000.0','indigente ','');
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (31,42,2,'2023-05-04 03:40:42',200000,'el prestamo con ultima actividad el dia : 2023-03-11 23:58:19 era de 200000.0 se abono un total de =0',NULL),(32,42,2,'2023-05-09 05:44:36',1000000,'antisipo	',NULL),(33,42,2,'2023-05-16 16:15:07',200000,'prueba',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipocuenta`
--

LOCK TABLES `tipocuenta` WRITE;
/*!40000 ALTER TABLE `tipocuenta` DISABLE KEYS */;
INSERT INTO `tipocuenta` VALUES (1,'Compra','Cuenta de Compra'),(2,'Venta','Cuenta de Venta'),(3,'Cuenta Usuario','estas cuentas son de usuario ');
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

--
-- Table structure for table `tipos_movimientos`
--

DROP TABLE IF EXISTS `tipos_movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_movimientos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  `es_gasto` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Descripcion_UNIQUE` (`Descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_movimientos`
--

LOCK TABLES `tipos_movimientos` WRITE;
/*!40000 ALTER TABLE `tipos_movimientos` DISABLE KEYS */;
INSERT INTO `tipos_movimientos` VALUES (1,'Pago de nomina',0),(2,'Donacion',1),(4,'Venta de barilla                            ',1),(6,'Pago de arriendo',0),(7,'venta de rueda                 ',1),(8,'Pago de impuestos                     ',0),(9,'venta de puerta                             ',1),(10,'Gasolina                            ',0),(11,'Compra de Cafe                                   ',0),(12,'pago cuenta Compra',0),(13,'ingreso cuenta venta',1);
/*!40000 ALTER TABLE `tipos_movimientos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-26 11:24:18
